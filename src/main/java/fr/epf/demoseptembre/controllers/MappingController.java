package fr.epf.demoseptembre.controllers;

import fr.epf.demoseptembre.models.FormObject;
import fr.epf.demoseptembre.models.Page;
import fr.epf.demoseptembre.models.Story;
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

    public MappingController(UserDao userDao, StoryDao storyDao, PageDao pageDao) {
        this.userDao = userDao;
        this.storyDao = storyDao;
        this.pageDao = pageDao;
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

        model.addAttribute("story", storyDao.findAll());
        return "accueil";
    }

    @GetMapping("/new-story")
    public String getNewStory(Model model) {

        String[] img = {"/img/seigneur_des_moineaux.jpg", "/img/knight.jpg", "/img/castlevani.jpg",
                "/img/dragon.jpg", "/img/Josef-Anton.jpeg", "/img/madoka.jpg",
                "/img/pirate.jpg", "/img/ship.jpg", "/img/Ziolkowski.jpg"};

        model.addAttribute("formObject", new FormObject());
        model.addAttribute("img", img);

        return "new-story";
    }

    @PostMapping("/new-story")
    public String postNewStory(FormObject formObject, Model model) {

        formObject.getStory().setUser(userDao.findByLogin("alex").get(0));
        formObject.getStory().setImg(formObject.getStory().getImg());
        storyDao.save(formObject.getStory());

        formObject.getPage().setStory(formObject.getStory());
        formObject.getPage().setKnot("1");
        pageDao.save(formObject.getPage());

        return "redirect:accueil";
    }

    @GetMapping("/inscription")
    public String getInscription() {
        return "inscription";
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

        return "story";
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model) {

        model.addAttribute("story", storyDao.findByUser(userDao.findByLogin("alex").get(0)));
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
