package Back.Blog.repository;

import Back.Blog.model.Blog;
import Back.Blog.model.Details;
import Back.Blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailsRepository extends JpaRepository<Details,Long> {
    Details findByBlog(Blog blog);

    Optional<Details> findByBlogAndUsers(Blog blog,Users user);
}
