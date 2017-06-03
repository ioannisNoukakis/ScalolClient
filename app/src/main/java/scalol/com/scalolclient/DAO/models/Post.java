package scalol.com.scalolclient.DAO.models;

/**
 * Created by durza9390 on 02.06.2017.
 */

public class Post {
    private long id;
    private String title;
    private String image_path;
    private long score;
    private boolean nsfw;
    private long owner_id;

    public Post(long id, String title, String image_path, long score, boolean nsfw, long owner_id) {
        this.id = id;
        this.title = title;
        this.image_path = image_path;
        this.score = score;
        this.nsfw = nsfw;
        this.owner_id = owner_id;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public boolean isNsfw() {
        return nsfw;
    }

    public void setNsfw(boolean nsfw) {
        this.nsfw = nsfw;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }
}