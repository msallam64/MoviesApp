package com.example.ecom_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecom_app.activities.LogInActivity;
import com.example.ecom_app.activities.SignUpActivity;
import com.example.ecom_app.activities.TopCategoryItem;
import com.example.ecom_app.activities.TopSeeMore;
import com.example.ecom_app.adapters.BestSellerAdapter;
import com.example.ecom_app.adapters.NewArrivalAdapter;
import com.example.ecom_app.adapters.SideMenuAdapter;
import com.example.ecom_app.adapters.TopAdapter;
import com.example.ecom_app.fragments.DealsFragment;
import com.example.ecom_app.fragments.LikesFragment;
import com.example.ecom_app.fragments.OrdersFragment;
import com.example.ecom_app.fragments.ProfileFragment;
import com.example.ecom_app.models.home.home.BestSeller;
import com.example.ecom_app.models.home.home.Home;
import com.example.ecom_app.models.home.home.NewArrival;
import com.example.ecom_app.models.home.home.SideMenuCategory;
import com.example.ecom_app.models.home.home.TopCategory;
import com.example.ecom_app.models.home.usermodel.UserLog;
import com.example.ecom_app.networking.MyRequests;
import com.example.ecom_app.networking.MyRetrofit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TopAdapter.RecyclerViewClickListener {
    Home home;
    List<BestSeller> bestSellers;
    BestSellerAdapter bestSellerAdapter;
    List<SideMenuCategory> sideMenuCategories;
    List<TopCategory> topCategories;
    List<NewArrival> newArrivals;
    RecyclerView recyclerViewSideMenu, recyclerViewtopgategory, recyclerViewNewArrival, recyclerViewBestseller;
    SideMenuAdapter sideMenuAdapter;
    TopAdapter topCategoryAdapter;
    NewArrivalAdapter newArrivalAdapter;
    Button signIn, signUp;
    UserLog userLog;
    TextView textViewname;
    LinearLayout linearLayout;
    ImageView imageViewuser;
    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    private static final String IMAGE_URL = "https://e-commerce-dev.intcore.net/";


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        bottomNavigationView = findViewById(R.id.bottom_app_bar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        textViewname = findViewById(R.id.textViewname);
        linearLayout = findViewById(R.id.linearLayoutlog);
        setSupportActionBar(toolbar);
        signIn = findViewById(R.id.btn_signin);
        signUp = findViewById(R.id.btn_signup);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        recyclerViewSideMenu = findViewById(R.id.side_menu_recycler);
        LinearLayoutManager layoutManagersidemenue = new LinearLayoutManager(this);
        recyclerViewSideMenu.setLayoutManager(layoutManagersidemenue);
        recyclerViewtopgategory = findViewById(R.id.rv_Top_Catageroy);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerViewtopgategory.setLayoutManager(linearLayoutManager);
        recyclerViewNewArrival = findViewById(R.id.rv_New_Arrival);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerViewNewArrival.setLayoutManager(manager);
        recyclerViewBestseller = findViewById(R.id.rv_Best_sell);
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        recyclerViewBestseller.setLayoutManager(manager1);
        prepareSideMenuData();
        imageViewuser = findViewById(R.id.imageViewuser);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        final Intent i = getIntent();
        if (userLog != null) {
            userLog = (UserLog) i.getExtras().getSerializable("user");
            linearLayout.setVisibility(View.GONE);
            textViewname.setVisibility(View.VISIBLE);
            textViewname.setText(userLog.getName());
            imageViewuser.setVisibility(View.VISIBLE);
            String imgUrl = userLog.getImage();
            Glide.with(getApplicationContext()).load(IMAGE_URL + imgUrl)
                    .into(imageViewuser);

        }
        LinearLayout linearLayout = findViewById(R.id.top_category_seemore);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TopSeeMore.class);
                startActivity(i);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                ScrollView scrollView = findViewById(R.id.scrolmain);
                switch (menuItem.getItemId()) {
                    case R.id.home_icon:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.deal_icon:
                        scrollView.setVisibility(View.GONE);
                        fragment = new DealsFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.like_icon:
                        scrollView.setVisibility(View.GONE);
                        fragment = new LikesFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.buy_icon:
                        scrollView.setVisibility(View.GONE);
                        fragment = new OrdersFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.profile_icon:
                        scrollView.setVisibility(View.GONE);
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fram_container, ProfileFragment.newInstance(userLog));
                        transaction.commit();
                        return true;

                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareSideMenuData() {
        MyRequests dataInterface = MyRetrofit.getRetrofitInstance().create(MyRequests.class);
        Call<Home> dataModelCall = dataInterface.getData();
        dataModelCall.enqueue(new Callback<Home>() {
            @Override
            public void onResponse(Call<Home> call, Response<Home> response) {
                home = response.body();
                sideMenuCategories = home.getSideMenuCategories();
                sideMenuAdapter = new SideMenuAdapter(sideMenuCategories);
                recyclerViewSideMenu.setAdapter(sideMenuAdapter);
                topCategories = home.getTopCategories();
                topCategories.add(new TopCategory(0));
                topCategoryAdapter = new TopAdapter(topCategories, getApplicationContext(), new TopAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, TopCategoryItem.class);
                        int id = topCategories.get(position).getId();
                        intent.putExtra("id", id);
                        String categoryimage=topCategories.get(position).getImage();
                        intent.putExtra("topimage",categoryimage);
                        startActivity(intent);

                    }
                });
                recyclerViewtopgategory.setAdapter(topCategoryAdapter);
                newArrivals = home.getNewArrival();
                newArrivalAdapter = new NewArrivalAdapter(newArrivals);
                recyclerViewNewArrival.setAdapter(newArrivalAdapter);
                bestSellers = home.getBestSeller();
                bestSellerAdapter = new BestSellerAdapter(bestSellers);
                recyclerViewBestseller.setAdapter(bestSellerAdapter);
//
            }

            @Override
            public void onFailure(Call<Home> call, Throwable t) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fram_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(MainActivity.this, TopCategoryItem.class);
        Toast.makeText(this, "Pos " + position, Toast.LENGTH_LONG).show();

    }
}
