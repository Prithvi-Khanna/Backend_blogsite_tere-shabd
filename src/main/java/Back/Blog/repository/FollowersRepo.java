package Back.Blog.repository;

import Back.Blog.model.Followers;
import Back.Blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FollowersRepo extends JpaRepository<Followers,Long> {

        List<Followers> findAllByFollower(Users follower);

    Followers findByFollowerAndFser1(Users follower, Users following);
}
