package model;

public class Boardnew {

    private String id;
    private String title;
    private String content;
    private String image_link;
    private String author;
    private String created;
    private String description;

    public Boardnew() {
        super();
    }

    public Boardnew(String id, String title, String content, String image_link, String author, String created, String description) {
        super();
        this.id = id;
        this.title = title;
        this.content = content;
        this.image_link = image_link;
        this.author = author;
        this.created = created;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Boardnew [id=" + id + ", title=" + title + ", content=" + content + ", image_link=" + image_link
                + ", author=" + author + ", created=" + created + ", description=" + description+ "]";
    }

}
