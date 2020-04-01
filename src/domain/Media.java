package domain;

public class Media {

    private int mediaId;
    private MediaType type;
    private String path;

    public Media(MediaType type, String path) {
        this.type = type;
        this.path = path;
    }

    public Media() {
    }

    public int getMediaId() {
        return mediaId;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
