package main.java.com.qcm.model;

public class Option {
    private int id;
    private Question question;
    private String option;
    private boolean is_correct;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public boolean isIs_correct() {
        return is_correct;
    }

    public void setIs_correct(boolean is_correct) {
        this.is_correct = is_correct;
    }
    public Option() {}

    public Option(int id, Question question, String option, boolean is_correct) {
        this.id = id;
        this.question = question;
        this.option = option;
        this.is_correct = is_correct;
    }
}
