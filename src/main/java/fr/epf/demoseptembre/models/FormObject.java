package fr.epf.demoseptembre.models;

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
