package com.example.service;

import com.example.model.OperationModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class HtmlLoader {


    private static Logger logger = Logger.getLogger(HtmlLoader.class.getName());

    public String downloadHtml(OperationModel model) throws IOException{

        String inputURL = model.getUrl();
        String result = "";
        try {
            logger.info("Попытка получить данные по адресу: " + inputURL);
            Document document = Jsoup.connect(inputURL).userAgent("Chrome/81.0.4044.138").get();
            model.setDocument(document);
            logger.info("Запись html страницы в файл result.html");
            for (String value : document.html().split("\n")) {
                result += value+"\n";
            }
        } catch (IllegalArgumentException e) {
            logger.log(Level.WARNING, "Некорректно введен адрес страницы", e);
        }
        logger.info("Скачивание html страницы успешно завершено");
        model.setFileHtml(result);
        return result;
    }


    public static List<String> parseHTML(Document document){
        List<String> result = new ArrayList<>();
        for (Element element : document.getElementsByTag("body")) {
            Collections.addAll(result, element.text().toUpperCase().split("[\\s.,!?;:()\\\\\\]\\[\"]+"));
        }
        logger.info("Формирование статистики html страницы успешно завершено");
        return result;
    }
}