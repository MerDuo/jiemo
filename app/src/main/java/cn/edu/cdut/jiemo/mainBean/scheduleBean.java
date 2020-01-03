package cn.edu.cdut.jiemo.mainBean;

public class scheduleBean {
    private String stitle;
    private String stime;

    public scheduleBean(String stime, String stitle) {
        this.stime=stime;
        this.stitle=stitle;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }
}
