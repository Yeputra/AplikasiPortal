package id.Freaky.aplikasiportalprogramstudi.model;

public class BerkasModel {
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

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String title;
    public String content;
    public String writer;
    public String date;
    public String image;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String url;

    public boolean isBtnShow() {
        return btnShow;
    }

    public void setBtnShow(boolean btnShow) {
        this.btnShow = btnShow;
    }

    public boolean btnShow;
}
