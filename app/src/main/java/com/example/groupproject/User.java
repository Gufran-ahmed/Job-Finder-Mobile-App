package com.example.groupproject;

public class User {
    private String userName;
    private String admin;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private int rating;

    public User(){}
    public User(String admin, String email, String f, String l, String u, String p, int r ){
        this.admin = admin;
        this.email = email;
        this.firstName = f;
        this.lastName = l;
        this.userName = u;
        this.password = p;
        this.rating = r;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating(){
        return rating;
    }
    public void setRating(int rating){
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "User{" +
                "admin=" + admin +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", rating= '" + rating + '\'' +
                '}';
    }
}
