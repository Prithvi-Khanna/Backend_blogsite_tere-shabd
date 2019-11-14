package Back.Blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Followers implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Fid;
    @ManyToOne
    private Users User1;
    @ManyToOne
    private Users Follower;

    public Long getFid() {
        return Fid;
    }

    public void setFid(Long fid) {
        Fid = fid;
    }

    public Users getUser1() {
        return User1;
    }

    public void setUser1(Users user1) {
        User1 = user1;
    }

    public Users getFollower() {
        return Follower;
    }

    public void setFollower(Users follower) {
        Follower = follower;
    }
}
