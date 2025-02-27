package Back.Blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table ( name = "Users")
public class Users implements Serializable {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    private String email ;
    @Column( unique = true)
    private String username ;
    private String password ;
    private String role;
    private String phone;
    private String gender;
    @Column( columnDefinition = " int default '0'")
    private int followers ;
    @Column( columnDefinition = " int default '0'")
    private int following ;
    @Column( columnDefinition = " int default '0'")
    private int blogs ;

    public int getBlogs() {
        return blogs;
    }

    public void setBlogs(int blogs) {
        this.blogs = blogs;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users(){}

    public Users(String s){}

    public Users(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getuserid() {
        return id;
    }

    @Column( nullable = false , columnDefinition = " int default '1'")
    private int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Embedded

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

