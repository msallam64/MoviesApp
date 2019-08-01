package com.example.ecom_app.networking;

import com.example.ecom_app.models.home.home.Home;
import com.example.ecom_app.models.home.topcategorymodels.ProductsModel;
import com.example.ecom_app.models.home.usermodel.UserModel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyRequests {
    @GET("api/v1/user/app/home")
    Call<Home> getData();

    @POST("api/v1/user/auth/signin")
    Call<UserModel> signin(@Query("name") String email, @Query("password") String password,
                           @Query("mobile_token") String mobiltoken, @Query("os") String os);

    @POST("api/v1/user/auth/signup")
    Call<UserModel> signup(@Query("name") String email, @Query("email") String mail,
                           @Query("phone") String phone, @Query("password") String password);

    @GET("api/v1/user/auth/products")
    Call<ProductsModel> getcategory(@Path("category_id") String id);


}
