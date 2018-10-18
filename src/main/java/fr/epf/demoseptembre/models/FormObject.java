package fr.epf.demoseptembre.models;

/**
 * TODO class details.
 *
 * This model is used during the creation of a new Story. It is composed of a story, to collect the name and the summary of the story,
 * as well as a Page, to collect the first chapter of the story.
 * @author ROUSSIN ROUGIER DANTY
 *
 */

public class FormObject {

    private Story story;
    private Page page;

    public FormObject() {
        this.story = new Story();
        this.page = new Page();
    }

    public FormObject(Story story, Page page) {
        this.story = story;
        this.page = page;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}