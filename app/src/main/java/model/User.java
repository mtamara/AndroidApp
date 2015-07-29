package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tamara on 7/27/2015.
 */
public class User {

    private String id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String profilePhotoLocation;

    private String headerPhotoLocation;

    private int followersNum = 0;

    private List<String> following = new ArrayList<>();

    public User(String firstName, String lastName, String username,
                String password, String email, String profilePhotoLocation,
                String headerPhotoLocation, int followersNum, List<String> following) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePhotoLocation = profilePhotoLocation;
        this.headerPhotoLocation = headerPhotoLocation;
        this.followersNum = followersNum;
        this.following = following;
    }

    public User(String firstName, String lastName, String username,
                String password, String email, int followersNum) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.followersNum = followersNum;
    }

    public User(String firstName, String lastName, String username,
                String password, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePhotoLocation() {
        return profilePhotoLocation;
    }

    public void setProfilePhotoLocation(String profilePhotoLocation) {
        this.profilePhotoLocation = profilePhotoLocation;
    }

    public String getHeaderPhotoLocation() {
        return headerPhotoLocation;
    }

    public void setHeaderPhotoLocation(String headerPhotoLocation) {
        this.headerPhotoLocation = headerPhotoLocation;
    }

    public int getFollowersNum() {
        return followersNum;
    }

    public void setFollowersNum(int followersNum) {
        this.followersNum = followersNum;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        return id == null ? false : id.equals(other.id);// Compare Id if null
        // falseF
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}
