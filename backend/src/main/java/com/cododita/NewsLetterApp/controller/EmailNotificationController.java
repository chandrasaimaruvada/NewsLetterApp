package com.cododita.NewsLetterApp.controller;

import com.cododita.NewsLetterApp.bean.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cododita.NewsLetterApp.service.NotificationService;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


@RestController
public class EmailNotificationController {

   @Autowired
   public NotificationService notificationService;


    @PostMapping("/sendSimpleMail")
    public String sendSimpleMail(@RequestBody EmailData emailData) {
        return notificationService.sendSimpleMail(emailData);
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailData emailData) {
        return notificationService.sendMailWithAttachment(emailData);
    }

    @PostMapping("/sendMailWithHTML_Body")
    public Map<String, String> sendMailWithHTMLBody(
            @RequestParam String receipient,
            @RequestParam String mailSubject,
            @RequestParam(required = false) String mailBody,
            @RequestParam(required = false) MultipartFile htmlFile) {

        Map<String, String> responseMap = new HashMap<>();
        try {
            // Pass the form data and file to the service
            String result = notificationService.sendMailWithHTMLBody(receipient, mailSubject, mailBody, htmlFile);
            responseMap.put("success", result);
        } catch (Exception e) {
            responseMap.put("error", "Error sending email: " + e.getMessage());
        }
        return responseMap;
    }

    @PostMapping("/sendMailWithDynamic_HTML_Body")
    public String sendMailWithDynamic_HTML_Body(@RequestBody EmailData emailData) {
        return notificationService.sendMailWithDynamicHTMlBody(emailData);
    }
}