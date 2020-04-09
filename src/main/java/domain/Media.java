package domain;

import javax.persistence.*;

@Entity
@Table(name = "Media")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MediaId")
    private int mediaId;
    @Column(name = "Type")
    private MediaType type;
    @Column(name = "Path")
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
