package fr.epf.demoseptembre.models;

public class FormInscription {

    private User user;
    private String password2;

    public FormInscription() {
        user = new User();
        password2="";
    }

    public FormInscription(User user, String password2) {
        this.user = user;
        this.password2 = password2;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
