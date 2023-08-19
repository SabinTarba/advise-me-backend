package com.sabintarba.sabintarba.models;


import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String password;
    @Indexed(unique = true)
    private String email;
    private int age;
    private String description;

    private String url1;

    private String url2;

    private String profilePicture;

    public User(String id, String name, String password, String email, int age, String description, String url1, String url2, String profilePicture) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.description = description;
        this.url1 = url1;
        this.url2 = url2;
        this.profilePicture = profilePicture;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public JSONObject toJSONObject(){
        JSONObject object = new JSONObject();

        object.put("name", this.name);
        object.put("age", this.age);
        object.put("id", this.id);
        object.put("email", this.email);
        object.put("description", this.description);
        object.put("password", this.password);
        object.put("url1", this.url1);
        object.put("url2", this.url2);
        object.put("profilePicture", this.profilePicture);

        return object;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
