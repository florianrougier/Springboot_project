package fr.epf.demoseptembre.persistence;

import fr.epf.demoseptembre.models.Story;
import fr.epf.demoseptembre.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryDao extends CrudRepository<Story, Integer> {

    List<Story> findByName(String name);

    List<Story> findByUser(User user);

}
