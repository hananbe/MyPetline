package com.example.android.mypetline.Model.Rss;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public  class Item {
    @Element(name = "title", required = true)
    String title;
    @Element(name = "link", required = true)
    String link;
    @Element(name = "description", required = true)
    String description;
    @Element(name = "pubDate", required = false)
    String pubDate;

    @Element(name = "content", required = false)
    private Content content;

    @Root(name = "content", strict = false)
    static class Content {

        @Attribute(name = "url")
        private String url;

        public String getUrl(){
            return url;
        }
    }
    public String getContent() {
        if (content==null) return "";
        return content.getUrl();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", thumbnail='" + content.getUrl() + '\'' +
                '}';
    }
}