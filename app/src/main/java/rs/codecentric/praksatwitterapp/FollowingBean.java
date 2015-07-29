package rs.codecentric.praksatwitterapp;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tamara on 7/28/2015.
 */
public class FollowingBean {

    int followingImage;
    String followingFirstName;
    String followingLastName;
    String followingId;

    public int getFollowingImage() {
        return followingImage;
    }

    public void setFollowingImage(int followingImage) {
        this.followingImage = followingImage;
    }

    public String getFollowingFirstName() {
        return followingFirstName;
    }

    public void setFollowingFirstName(String followingFirstName) {
        this.followingFirstName = followingFirstName;
    }

    public String getFollowingLastName() {
        return followingLastName;
    }

    public void setFollowingLastName(String followingLastName) {
        this.followingLastName = followingLastName;
    }

    public String getFollowingId() {
        return followingId;
    }

    public void setFollowingId(String followingId) {
        this.followingId = followingId;
    }
}
