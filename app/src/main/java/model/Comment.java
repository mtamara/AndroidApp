package model;

/**
 * Created by Tamara on 7/28/2015.
 */
public class Comment {

    private String id;

    private String content;

  //  private DateTime dateAndTime;

    private String ownerId;

    private String ownerName;

    private String ownerPhotoLocation;


    public Comment() {
        // TODO Auto-generated constructor stub
    }
/*
    public Comment(String ownerId, String content, DateTime dateAndTime, String ownerName,
                   String ownerPhotoLocation) {
        super();
        this.content = content;
        this.dateAndTime = dateAndTime;
        this.ownerName = ownerName;
        this.ownerPhotoLocation = ownerPhotoLocation;
        this.ownerId = ownerId;
    }
*/
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
/*
    public DateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(DateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
*/
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
