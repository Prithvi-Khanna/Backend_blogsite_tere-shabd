package Back.Blog.repository;

import Back.Blog.model.Blog;
import Back.Blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BlogRepository  extends JpaRepository<Blog,Long> {

    List<Blog> findAllByDescriptionContainingOrderByDate1Desc(String description);

    List<Blog> findAllByUser(Users user);

    List<Blog> findAllByTitleContainingOrderByDate1Desc(String description1);

    List<Blog> findAllByPrivOrderByDate1Desc(Boolean priv);


    List<Blog> findAllByUserAndPrivOrderByDate1Desc(Users user, boolean b);

    List<Blog> findAllByUserOrderByDate1Desc(Users user);
}
