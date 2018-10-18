package fr.epf.demoseptembre.persistence;

import fr.epf.demoseptembre.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO class details.
 *
 * Repository of the User table in the DB
 *
 * @author ROUSSIN ROUGIER DANTY
 *
 */
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    List<User> findByLogin(String pseudo);
    List<User> findByPassword(String password);
    List<User> findByLoginAndPassword(String login, String password);
}
