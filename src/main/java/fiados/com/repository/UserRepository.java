package fiados.com.repository;

import fiados.com.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "select u from User u where first_name like :term or last_name like :term or role like :term " +
            "or city like :term or adress like :term or company_name like :term or country like :term or dni like :term")
    List<User> search(String term);
}
