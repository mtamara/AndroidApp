package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tamara on 7/28/2015.
 */
public class Twit {

    private String id;

    private String content;

    private String imageLocation;

   // private DateTime dateAndTime;

    private int favNo = 0;

    private String ownerId;

    private String ownerName;

    private String ownerPhotoLocation;

 //   private Map<String, Boolean> userFavs = new HashMap<>();

    private String retwittedContent; // sadrzaj retwita

    private String retwittedUserId; // id usera ciji je originalni tvit

    private String retwittedUserName;

    private String retwittedUserPhotoLocation;

 //   private DateTime originalTwittTime; // vreme postovanja twitta koji
    // retwittujemo

    private Boolean isRetwitted = false; // da li je twitt zapravo retwitt

    private List<Comment> comments = new ArrayList<>();

    public Twit() {
        super();
    }

    /*
    public Twit(String content, String imageLocation, DateTime dateAndTime,
                int favNo, String ownerId, String ownerName,
                String ownerPhotoLocation, Map<String, Boolean> userFavs,
                String retwittedContent, String retwittedUserId,
                String retwittedUserName, String retwittedUserPhotoLocation,
                DateTime originalTwittTime, Boolean isRetwitted,
                List<Comment> comments) {
        super();
        this.content = content;
        this.imageLocation = imageLocation;
        this.dateAndTime = dateAndTime;
        this.favNo = favNo;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.ownerPhotoLocation = ownerPhotoLocation;
        this.userFavs = userFavs;
        this.retwittedContent = retwittedContent;
        this.retwittedUserId = retwittedUserId;
        this.retwittedUserName = retwittedUserName;
        this.retwittedUserPhotoLocation = retwittedUserPhotoLocation;
        this.originalTwittTime = originalTwittTime;
        this.isRetwitted = isRetwitted;
        this.comments = comments;
    }
*/
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhotoLocation() {
        return ownerPhotoLocation;
    }

    public void setOwnerPhotoLocation(String ownerPhotoLocation) {
        this.ownerPhotoLocation = ownerPhotoLocation;
    }

    public String getRetwittedUserName() {
        return retwittedUserName;
    }

    public void setRetwittedUserName(String retwittedUserName) {
        this.retwittedUserName = retwittedUserName;
    }

    public String getRetwittedUserPhotoLocation() {
        return retwittedUserPhotoLocation;
    }

    public void setRetwittedUserPhotoLocation(String retwittedUserPhotoLocation) {
        this.retwittedUserPhotoLocation = retwittedUserPhotoLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageName) {
        this.imageLocation = imageName;
    }

    public int getFavNo() {
        return favNo;
    }

    public void setFavNo(int favNo) {
        this.favNo = favNo;
    }
/*
    public DateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(DateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
*/
    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
/*
    public Map<String, Boolean> getUserFavs() {
        return userFavs;
    }

    public void setUserFavs(Map<String, Boolean> userFavs) {
        this.userFavs = userFavs;
    }

    public DateTime getOriginalTwittTime() {
        return originalTwittTime;
    }

    public void setOriginalTwittTime(DateTime originalTwittTime) {
        this.originalTwittTime = originalTwittTime;
    }
*/
    public Boolean getIsRetwitted() {
        return isRetwitted;
    }

    public void setIsRetwitted(Boolean isRetwitted) {
        this.isRetwitted = isRetwitted;
    }

    public String getRetwittedContent() {
        return retwittedContent;
    }

    public void setRetwittedContent(String retwittedContent) {
        this.retwittedContent = retwittedContent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Twit))
            return false;
        Twit other = (Twit) obj;
        return id == null ? false : id.equals(other.id);// Compare Id if null
        // falseF
    }

    public String getRetwittedUserId() {
        return retwittedUserId;
    }

    public void setRetwittedUserId(String retwittedUserId) {
        this.retwittedUserId = retwittedUserId;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
