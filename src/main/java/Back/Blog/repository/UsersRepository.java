package Back.Blog.repository;


import Back.Blog.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>
{


    Optional<Users> findByUsername(String name);

    List<Users> findAllByUsernameContaining(String name);
}
