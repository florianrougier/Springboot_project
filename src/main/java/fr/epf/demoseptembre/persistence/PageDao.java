package fr.epf.demoseptembre.persistence;

import fr.epf.demoseptembre.models.Page;
import fr.epf.demoseptembre.models.Story;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO class details.
 *
 * Repository of the Page table in the DB
 *
 * @author ROUSSIN ROUGIER DANTY
 *
 */

@Repository
public interface PageDao extends CrudRepository<Page, Integer> {

    List<Page> findByKnot(String knot);

    List<Page> findByStory(Story story);

    List<Page> findByStoryAndKnot(Story story, String knot);
}