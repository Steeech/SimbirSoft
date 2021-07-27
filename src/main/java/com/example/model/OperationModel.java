package com.example.model;

import org.jsoup.nodes.Document;

public class OperationModel {

    private String url;
    private String fileHtml;
    private String statistic;
    private Document document;


    public OperationModel() {
    }


    public OperationModel(String statistic) {
        this.statistic = statistic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatistic() {
        return statistic;
    }

    public void setStatistic(String statistic) {
        this.statistic = statistic;
    }

    public String getFileHtml() {
        return fileHtml;
    }

    public void setFileHtml(String fileHtml) {
        this.fileHtml = fileHtml;
    }

    public String toString() {
        String res = url + " " + fileHtml.substring(0, 10);
        return res;
    }


    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
