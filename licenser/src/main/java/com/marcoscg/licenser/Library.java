package com.marcoscg.licenser;

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */

public class Library {

    private String title, url;
    private int license;

    public Library(String title, String url, int license) {
        this.title = title;
        this.url = url;
        this.license = license;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }
}
