package com.sabintarba.sabintarba.models;

import org.json.JSONObject;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String title;
    private String description;

    @CreatedDate
    private Date postedAt;

    @DBRef
    private User postedBy;

    public Post(String id, String title, String description, Date postedAt, User postedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.postedAt = postedAt;
        this.postedBy = postedBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("title", this.title);
        object.put("description", this.description);
        object.put("postedBy", this.postedBy.toJSONObject());
        object.put("postedAt", this.postedAt);

        return object;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", postedAt=" + postedAt +
                ", postedBy=" + postedBy +
                '}';
    }
}
