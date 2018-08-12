package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ADOBNER on 2018-08-11.
 */
public class NewspaperInfo {
    String fileName;
    String name;
    Integer width;
    Integer height;
    Integer dpi;
    Date date;

    public NewspaperInfo(String fileName, String name, Integer width, Integer height, Integer dpi) {
        this.fileName = fileName;
        this.name = name;
        this.width = width;
        this.height = height;
        this.dpi = dpi;
        Calendar cal = Calendar.getInstance();
        this.date = cal.getTime();
    }

    public NewspaperInfo() {

    }

    @Override
    public String toString() {
        return "NewspaperInfo{" +
                "fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", dpi=" + dpi +
                ", date=" + getTime(date) +
                '}';
    }

    private String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(date);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getDpi() {
        return dpi;
    }

    public Date getDate() {
        return date;
    }
}
