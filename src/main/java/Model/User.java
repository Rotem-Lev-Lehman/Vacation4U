package Model;

import javafx.scene.image.Image;

//User in the system
public class User {
    private String username;
    private String password;
    private String birthdate;
    private String firstName;
    private String lastName;
    private String city;
    private Image profileImage;

    //Constructor for user
    public User(String username, String password, String birthdate, String firstName, String lastName, String city) {
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
    }

    //Convert user information to string
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    /**
     * Getters and setters for user fields
     * */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }
}
