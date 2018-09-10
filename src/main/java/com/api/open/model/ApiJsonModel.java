package com.api.open.model;

/**
 * Created by 003 on 2018/9/10.
 */
public class ApiJsonModel {
    private volatile int id;
    private String url;
    private String json;
    private String updatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "ApiJsonModel{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", json='" + json + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
