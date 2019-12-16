package cn.edu.cdut.jiemo.diary;

public class TimeLineBean {

    private String text;
    private String time;
    private int imgId;

    public TimeLineBean(String time, int imgId,String text){
        this.time = time;
        this.imgId = imgId;
        this.text = text;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

}
