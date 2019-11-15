package Back.Blog.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Followers implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Fid;
    @ManyToOne
    private Users fser1;
    @ManyToOne
    private Users follower;

    public Long getFid() {
        return Fid;
    }

    public void setFid(Long fid) {
        Fid = fid;
    }

    public Users getFser1() {
        return fser1;
    }

    public void setFser1(Users fser1) {
        this.fser1 = fser1;
    }

    public Users getFollower() {
        return follower;
    }

    public void setFollower(Users follower) {
        this.follower = follower;
    }
}
