package me.sam.poetry.data.model;

/**
 * desc : model of poetry
 * date : 2018/8/12
 *
 * @author : dongSen
 */
public class Poetry {

    private String title;
    private String author;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
