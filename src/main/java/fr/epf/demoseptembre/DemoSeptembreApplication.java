package fr.epf.demoseptembre;

import fr.epf.demoseptembre.models.Page;
import fr.epf.demoseptembre.models.Story;
import fr.epf.demoseptembre.models.User;
import fr.epf.demoseptembre.persistence.PageDao;
import fr.epf.demoseptembre.persistence.StoryDao;
import fr.epf.demoseptembre.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;


@SpringBootApplication
@EnableJpaRepositories
public class DemoSeptembreApplication {
  
  @Autowired
  private UserDao userDao;
  @Autowired
  private StoryDao storyDao;
  @Autowired
  private PageDao pageDao;


  public static void main(String[] args) {
    // Point d'entrée de l'application.
    // On dit à Spring de s'initialiser
    // Il va aller "regarder" nos classes et détecter les annotations des singletons 
    // (@Controller, @Repository, @Component, @Service, etc...)
    // Ensuite, il va construire l'arbre de dépendances et le résoudre en injectant les données dans les bonnes classes
    SpringApplication.run(DemoSeptembreApplication.class);
  }

  /**
   * On appelle cette méthode lorsque Spring a terminé son initialisation.
   * Ici, on va réinitialiser la DB et insérer 3 entrées.
   */
  @PostConstruct
  public void init() {

    userDao.deleteAll();
    userDao.save(new User(null, "alex", "michel"));
    userDao.save(new User(null, "tibo", "paul"));


    storyDao.deleteAll();
    storyDao.save(new Story(null, "John Wars","La vie de John allait bien jusqu'a que son chimpanzé domestique se fasse tué par un gang de mafieux. Il décide alors de se venger !!!",userDao.findByLogin("tibo").get(0)));
    storyDao.save(new Story(null, "Le seigneur des moineaux","Gollum la mouette se retrouve un jour foudroyé par l'amour, en rencontrant un moineau nommé 'Gisèle'. Arriverez vous à la convaincre de votre amour passionné ???",userDao.findByLogin("alex").get(0)));

    pageDao.deleteAll();
    pageDao.save(new Page(null, "Un synopsis", storyDao.findById(3).get(),"1","Une petite histoire de Wars"));
    pageDao.save(new Page(null, "Gollum se balade",storyDao.findById(4).get(),"1","Gollum se balade sur un chemin de terre turquoise après avoir sniffé un bon gros feutre velleda. 30 bonnes minutes plus tard, il se retrouva devant..."));
    pageDao.save(new Page(null, "Une rivière", storyDao.findById(4).get(),"1.1","Gollum arrive devant une rivière"));
    pageDao.save(new Page(null, "Un pont", storyDao.findById(4).get(),"1.2","Gollum arrive devant un pont"));

  }
}