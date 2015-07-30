package rs.codecentric.praksatwitterapp;

/**
 * Created by Tamara on 7/29/2015.
 */
public class CommentBean {

    int ownerImage;
    String commentContent;
    String ownerName;

    public int getOwnerImage() {
        return ownerImage;
    }

    public void setOwnerImage(int ownerImage) {
        this.ownerImage = ownerImage;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
