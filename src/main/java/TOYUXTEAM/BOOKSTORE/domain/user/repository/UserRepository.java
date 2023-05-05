package TOYUXTEAM.BOOKSTORE.domain.user.repository;

import TOYUXTEAM.BOOKSTORE.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    User findByUsername(String name);

}
