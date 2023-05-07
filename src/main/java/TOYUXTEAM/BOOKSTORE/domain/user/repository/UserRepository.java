package TOYUXTEAM.BOOKSTORE.domain.user.repository;

import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findById(String name);

}
