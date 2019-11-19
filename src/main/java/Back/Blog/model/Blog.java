package Back.Blog.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Blog implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long BlogId;
    private String title;
    @Column( nullable = false)
    private LocalDate date1;
    private String description;
    @ManyToOne
    private Users user;
    private boolean priv;
    @Column( columnDefinition = " int default '0'")
    private int likes;
    @Column( columnDefinition = " int default '0'")
    private int dislikes;
    @Column( columnDefinition = " int default '0'")
    private int comments;


    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public Long getBlogId() {
        return BlogId;
    }

    public void setBlogId(Long blogId) {
        BlogId = blogId;
    }

    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }

    public boolean getPriv() {
        return priv;
    }

    public void setPriv(boolean priv) {
        this.priv = priv;
    }

    public LocalDate getDate1() {
        return date1;
    }

    public void setdate1()
    {
        this.date1 = LocalDate.now();
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
