package com.cododita.NewsLetterApp.service;

import com.cododita.NewsLetterApp.bean.EmailData;
import org.springframework.web.multipart.MultipartFile;

public interface NotificationService {
    public String sendSimpleMail(EmailData emailData);
    public String sendMailWithAttachment(EmailData emailData);
//    public String sendMailWithHTMLBody(EmailData emailData);

    String sendMailWithHTMLBody(String receipient, String mailSubject, String mailBody, MultipartFile htmlFile);

    public String sendMailWithDynamicHTMlBody(EmailData emailData);
}
