package Back.Blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Likes" , uniqueConstraints = @UniqueConstraint(columnNames = {"user_id" , "blog_id"}))
public class Likes implements Serializable {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long likeid;
    private Boolean like;
    private Boolean dislike;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Users user;
    @ManyToOne
    private Blog blog;

    public Long getLikeid() {
        return likeid;
    }

    public void setLikeid(Long likeid) {
        this.likeid = likeid;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Boolean getDislike() {
        return dislike;
    }

    public void setDislike(Boolean dislike) {
        this.dislike = dislike;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
