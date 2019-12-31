package cn.edu.cdut.jiemo.diary;

public class TimeLineBean {

    private String text;
    private String date;
    private String time;
    private int imgId;
    private String category;
    private int id;

    //    public TimeLineBean(String time, int imgId,String text,String category){
    public TimeLineBean(int id, String date, String time,String text,String category){
        this.id = id;
        this.date = date;
        this.time = time;
//        this.imgId = imgId;
        this.text = text;
        this.category = category;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
