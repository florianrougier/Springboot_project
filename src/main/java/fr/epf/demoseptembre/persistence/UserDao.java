package fr.epf.demoseptembre.persistence;

import fr.epf.demoseptembre.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    List<User> findByLogin(String pseudo);

    List<User> findByPassword(String password);
}
