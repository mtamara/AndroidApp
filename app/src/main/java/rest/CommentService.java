package rest;

import model.Comment;
import model.Twit;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.PUT;
import retrofit.http.Query;

/**
 * Created by Tamara on 7/29/2015.
 */
public interface CommentService {

    @PUT("/comments")
    public void addComment(@Query("user") String userId,
                         @Query("tweet") String tweetId,
                           @Body Comment comment,
                         Callback<Twit> response);
}
