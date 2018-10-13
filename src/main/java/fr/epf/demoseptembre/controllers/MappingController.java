package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.*;
import fr.epf.demoseptembre.persistence.PageDao;
import fr.epf.demoseptembre.persistence.StoryDao;
import fr.epf.demoseptembre.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Controller
public class MappingController {

    private final UserDao userDao;
    private final StoryDao storyDao;
    private final PageDao pageDao;

    private User user;


    public MappingController(UserDao userDao, StoryDao storyDao, PageDao pageDao) {
        this.userDao = userDao;
        this.storyDao = storyDao;
        this.pageDao = pageDao;
        user = new User();
    }

    /**
     * Ceci sera mappé sur l'URL '/users'.
     * C'est le routeur de Spring MVC qui va détecter et appeler directement cette méthode.
     * Il lui fournira un "modèle", auquel on pourra rajouter des attributs.
     * Ce modèle sera ensuite forwardé à une page web (dans resources/templates).
     * Le nom de la template est retourné par la fonction. Ici, elle appelle donc le template "users".
     *
     * @param model le modèle
     * @return
     */


    @GetMapping("/accueil")
    public String getAccueil(Model model) {

        System.out.println(this.user);

        model.addAttribute("story", storyDao.findAll());
        model.addAttribute("user", this.user);
        return "accueil";
    }

    //GET and POST Inscription
    @GetMapping("/inscription")
    public String getInscription(Model model) {

        model.addAttribute("info","");
        model.addAttribute("formInscription", new FormInscription());
        return "inscription";
    }
    @PostMapping("/inscription")
    public String postInscription(Model model, FormInscription formInscription) {

        if (!userDao.findByLogin(formInscription.getUser().getLogin()).isEmpty()){
            model.addAttribute("info","Nom de compte déja pris");
            model.addAttribute("formInscription",formInscription);
            return "inscription";
        }
        if (!formInscription.getUser().getPassword().equals(formInscription.getPassword2())){
            model.addAttribute("info","Mots de passe différents");
            model.addAttribute("formInscription", formInscription);
            return "inscription";
        }

        userDao.save(formInscription.getUser());
        this.user = formInscription.getUser();
        return "redirect:accueil";
    }

    //GET and POST Connexion
    @GetMapping("/connexion")
    public String getConnexion(Model model) {

        model.addAttribute("info","");
        model.addAttribute("user",new User());
        return "connexion";
    }
    @PostMapping("/connexion")
    public String postConnexion(Model model, User user) {

        if (userDao.findByLoginAndPassword(user.getLogin(),user.getPassword()).isEmpty()){
            model.addAttribute("info","Nom de compte ou mot de passe incorrect");
            model.addAttribute("user",user);
            return "connexion";
        }

        this.user = userDao.findByLogin(user.getLogin()).get(0);
        return "redirect:accueil";
    }

    //GET Deconnexion
    @GetMapping("/deconnexion")
    public String getDeconnexion(Model model) {

        this.user = new User();
        return "redirect:accueil";
    }

    @GetMapping("/new-story")
    public String getNewStory(Model model) {

        String[] img = {"/img/seigneur_des_moineaux.jpg", "/img/knight.jpg", "/img/castlevani.jpg",
                "/img/dragon.jpg", "/img/Josef-Anton.jpeg", "/img/madoka.jpg",
                "/img/pirate.jpg", "/img/ship.jpg", "/img/Ziolkowski.jpg"};

        model.addAttribute("formObject", new FormObject());
        model.addAttribute("img", img);
        model.addAttribute("user",this.user);

        return "new-story";
    }

    @PostMapping("/new-story")
    public String postNewStory(FormObject formObject, Model model) {

        formObject.getStory().setUser(this.user);
        formObject.getStory().setImg(formObject.getStory().getImg());
        storyDao.save(formObject.getStory());

        formObject.getPage().setStory(formObject.getStory());
        formObject.getPage().setKnot("1");
        pageDao.save(formObject.getPage());

        return "redirect:accueil";
    }

    @GetMapping("/story")
    public String getPage(Model model, @RequestParam("name") String storyName, @RequestParam("knot") String pageKnot) {


        Story story = storyDao.findByName(storyName).get(0);
        Page page = pageDao.findByStoryAndKnot(story, pageKnot).get(0);
        List<Page> listNextPage = new ArrayList<>();

        for (Page p : pageDao.findByStory(story)) {
            if ((p.getKnot().startsWith(pageKnot + ".")) && (p.getKnot().length() == pageKnot.length() + 2))
                listNextPage.add(p);
        }

        model.addAttribute("story", story);
        model.addAttribute("page", page);
        model.addAttribute("nextPage", listNextPage);
        model.addAttribute("user",this.user);

        return "story";
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {

        model.addAttribute("story", storyDao.findByUser(this.user));
        model.addAttribute("user",this.user);
        return "dashboard";
    }

    @GetMapping("/edit-story")
    public String getEditStory(Model model, @RequestParam("name") String storyName) {

        Comparator<Page> comp = (Page p1, Page p2) -> (p1.getKnot().compareTo(p2.getKnot()));
        List<Page> pageList = pageDao.findByStory(storyDao.findByName(storyName).get(0));
        TreeSet<Page> pageTreeSet = new TreeSet<Page>(comp);

        for (Page p : pageList) {
            pageTreeSet.add(p);
        }

        model.addAttribute("storyEdit", storyDao.findByName(storyName).get(0));
        model.addAttribute("pageEdit", pageTreeSet);
        model.addAttribute("user",this.user);
        return "edit-story";
    }

    @PostMapping("/edit-story")
    public String postEditStory(Story storyEdit, Model model, @RequestParam("name") String storyName) {

        Story story = storyDao.findByName(storyName).get(0);
        story.setSummary(storyEdit.getSummary());
        storyDao.save(story);

        return "redirect:dashboard";
    }

    @GetMapping("/edit-page")
    public String getEditPage(Model model, @RequestParam("name") String storyName, @RequestParam("knot") String pageKnot) {

        model.addAttribute("pageEdit", pageDao.findByStoryAndKnot(storyDao.findByName(storyName).get(0), pageKnot).get(0));
        model.addAttribute("storyName", storyName);
        model.addAttribute("user",this.user);
        return "edit-page";
    }

    @PostMapping("/edit-page")
    public String postEditPage(Page pageEdit, Model model, @RequestParam("name") String storyName, @RequestParam("knot") String pageKnot) {

        Page page = pageDao.findByStoryAndKnot(storyDao.findByName(storyName).get(0), pageKnot).get(0);
        page.setText(pageEdit.getText());
        pageDao.save(page);

        return "redirect:dashboard";
    }

    @GetMapping("/delete-story")
    public String getDeleteStory(Model model, @RequestParam("name") String storyName) {

        while (!pageDao.findByStory(storyDao.findByName(storyName).get(0)).isEmpty()) {
            pageDao.delete(pageDao.findByStory(storyDao.findByName(storyName).get(0)).get(0));
        }

        storyDao.delete(storyDao.findByName(storyName).get(0));

        return "redirect:dashboard";
    }

    @GetMapping("/delete-page")
    public String getDeletePage(Model model, @RequestParam("name") String storyName, @RequestParam("knot") String pageKnot) {

        List<Page> listPage = pageDao.findByStory(storyDao.findByName(storyName).get(0));

        for (Page p : listPage) {
            if (p.getKnot().startsWith(pageKnot)) pageDao.delete(p);
        }

        return "redirect:edit-story?name=" + storyName;
    }

    @GetMapping("/new-page")
    public String getNewPage(Model model, @RequestParam("name") String storyName, @RequestParam("knot") String pageKnot) {

        model.addAttribute("newPage", new Page());
        model.addAttribute("storyName", storyName);
        model.addAttribute("pageKnot", pageKnot);
        model.addAttribute("user",this.user);

        return "new-page";
    }

    @PostMapping("/new-page")
    public String postNewPage(Page newPage, Model model, @RequestParam("name") String storyName, @RequestParam("knot") String pageKnot) {
        newPage.setKnot(null);
        newPage.setStory(storyDao.findByName(storyName).get(0));

        int i = 1;

        do {
            if (pageDao.findByStoryAndKnot(storyDao.findByName(storyName).get(0), pageKnot + "." + i).isEmpty())
                newPage.setKnot(pageKnot + "." + i);
            i++;
        } while (newPage.getKnot() == null);

        pageDao.save(newPage);
        return "redirect:edit-story?name=" + storyName;
    }


}
