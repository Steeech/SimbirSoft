package com.example.controllers;

import com.example.model.OperationModel;
import com.example.service.HtmlLoader;
import com.example.service.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@Controller
@ControllerAdvice
public class SpringController {

    private static Logger logger = Logger.getLogger(HtmlLoader.class.getName());
    OperationModel operationModel = new OperationModel();
    @Autowired
    private HtmlLoader htmlLoader;
    @Autowired
    private Statistic statistic;

    @RequestMapping("/")
    public String getIndexPage(Model model) {
        model.addAttribute("operationModel", operationModel);
        model.addAttribute("error", "");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "submit")
    public String parse(HttpServletResponse response, @ModelAttribute("operationModel") OperationModel operationModel, Model model) {
        if (!operationModel.getUrl().equals("")) {
            try {
                String fileHtml = htmlLoader.downloadHtml(operationModel);

                if (operationModel.getFileHtml() != null && !operationModel.getFileHtml().equals("")) {
                    model.addAttribute("file", fileHtml);
                    model.addAttribute("statistic", statistic.getStatistic(operationModel));
                    model.addAttribute("error", "");
                } else {
                    model.addAttribute("error", "Введите корректный URL адрес");
                }

            } catch (IOException e) {
                model.addAttribute("error", e.getMessage());
            }
        } else {
            model.addAttribute("error", "Введите корректный URL адрес");
        }
        this.operationModel = operationModel;
        return "index";

    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "saveStatistic")
    public void saveStatistic(HttpServletResponse response) throws IOException {
        downloadFile(response, operationModel.getStatistic(), getName(operationModel.getUrl())+"-statistic.txt");
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, params = "save")
    public void save(HttpServletResponse response) throws IOException {
        downloadFile(response, operationModel.getFileHtml(), getName(operationModel.getUrl())+".html");
    }

    private void downloadFile(HttpServletResponse response, String fileStr, String fileName) throws IOException {
        if (fileStr != null && !fileStr.equals("")) {
            InputStream inputStream = new ByteArrayInputStream(fileStr.getBytes());
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            OutputStream out = response.getOutputStream();
            FileCopyUtils.copy(inputStream, out);
            response.flushBuffer();
            out.flush();
            out.close();
        } else {
            response.sendRedirect("");
        }
    }

    private String getName(String url){
        String[] domain = url.split("/");
        return domain[2];
    }

}