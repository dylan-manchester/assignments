package com.hcl.JacksonStreamer;

import java.net.URL;
import java.util.Date;

public class Headline {
    private int id;
    private String category;
    private Date datetime;
    private String headline;
    private String summary;
    private URL url;
    private URL image;
    private String source;
    private String related;

    public Headline(int id, String category, Date datetime, String headline, String summary, URL url, URL image, String source, String related) {
        this.id = id;
        this.category = category;
        this.datetime = datetime;
        this.headline = headline;
        this.summary = summary;
        this.url = url;
        this.image = image;
        this.source = source;
        this.related = related;
    }
    public Headline() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    @Override
    public String toString() {
        return '\n'+this.getHeadline()+
                "\nLink: "+this.getUrl()+
                "\n";
    }
}
