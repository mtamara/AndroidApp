package rest;

import java.util.List;

import model.Twit;
import model.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Tamara on 7/28/2015.
 */
public interface TwitService {

    @GET("/tweets")
    public void getUserTweets(@Query("user") String userId, @Query("page") String page, Callback<List<Twit>> response);

    @POST("/tweets")
    public void postTweet(@Query("user") String userId, @Body Twit twit, Callback<Twit> response);

    @GET("/tweet")
    public void getTweetById(@Query("tweetId") String tweetId, Callback<Twit> response);
}
