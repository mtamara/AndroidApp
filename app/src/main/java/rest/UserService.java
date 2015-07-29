package rest;

import java.util.List;

import model.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Tamara on 7/27/2015.
 */
public interface UserService {

    @GET("/user/{userId}")
    public void getUser(@Path("userId") String user, Callback<User> response);

    @GET("/users/{id}/following")
    public void getFollowing(@Path("id") String userId, Callback<List<User>> response);

    @PUT("/users/{id}/following")
    public void addRemoveFollowingUser(@Path("id")String userId,
                                       @Query("action") String action,
                                       @Query("addRemoveFollowingId") String addRemoveFollowingId,
                                       Callback<List<User>> response );

    @POST("/users")
    public void registerUser(@Body User user, Callback<User> response);

    @GET("/user")
    public void getUserByUsernamePassword(@Query("username") String username,
                                          @Query("password") String password,
                                          Callback<User> response);

    @GET("/users/{id}")
    public  void getUsers(@Path("id")String userId, Callback<List<User>> response );

    @PUT("/users/{id}")
    public User editUser(@Path("id")String userId,
                         @Query("firstname") String firstname,
                         @Query("lastname") String lastname,
                         @Query("password") String password,
                         Callback<List<User>> response );

}
