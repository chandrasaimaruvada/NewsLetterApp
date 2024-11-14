package com.cododita.NewsLetterApp.service;

import com.cododita.NewsLetterApp.bean.EmailData;
import com.cododita.NewsLetterApp.bean.Email_template_config;
import com.cododita.NewsLetterApp.repository.LoadMailTemplate;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private LoadMailTemplate loadMailTemplate;

    @Value("${spring.mail.username}")
    private String sender;



    @Override
    public String sendSimpleMail(EmailData emailData) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(emailData.getReceipient());
            simpleMailMessage.setText(emailData.getMailBody());
            simpleMailMessage.setSubject(emailData.getMailSubject());

            javaMailSender.send(simpleMailMessage);
            return "Successfully sent Mail";
        } catch (Exception e) {
            e.printStackTrace();  // log the stack trace to the console or a file
            return "Error in sendingSimpleMail: " + e.getMessage();
        }
    }

    @Override
    public String sendMailWithAttachment(EmailData emailData) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailData.getReceipient());
            mimeMessageHelper.setText(emailData.getMailBody());
            mimeMessageHelper.setSubject(emailData.getMailSubject());

            FileSystemResource fileSystemResource;

            if(emailData.getAttachments() != null && emailData.getAttachments().size() > 0){
                for(String filePath : emailData.getAttachments()){
                    fileSystemResource = new FileSystemResource(new File(filePath));
                    mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
                }

            }
            javaMailSender.send(mimeMessage);
            return "Successfully sent sendMailWithAttachment";
        }
        catch (Exception e){
            return "Error in sendMailWithAttachment";
        }

    }

    @Override
    public String sendMailWithHTMLBody(String receipient, String mailSubject, String mailBody, MultipartFile htmlFile) {
        try {
            // Create MIME message
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            // Set email properties
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receipient);
            mimeMessageHelper.setSubject(mailSubject);

            // If HTML file is provided, read its content
            if (htmlFile != null && !htmlFile.isEmpty()) {
                String htmlContent = new String(htmlFile.getBytes(), StandardCharsets.UTF_8);
                mimeMessageHelper.setText(htmlContent, true);  // true indicates HTML content
            } else {
                // If no HTML file is provided, use the inline mail body
                mimeMessageHelper.setText(mailBody != null ? mailBody : "", true);
            }

            // Send the email
            javaMailSender.send(mimeMessage);
            return "Successfully sent with HTML Body from file";
        } catch (IOException e) {
            return "Error in reading the HTML file: " + e.getMessage();
        } catch (Exception e) {
            return "Error in sendMailWithHTMLBody: " + e.getMessage();
        }
    }



    @Override
    public String sendMailWithDynamicHTMlBody(EmailData emailData) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper;

            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailData.getReceipient());
            mimeMessageHelper.setSubject(emailData.getMailSubject());

            List<Email_template_config> email_template_configList = loadMailTemplate.findByApplicationNameAndTemplateName(
                    emailData.getApplicationName(), emailData.getTemplateName()
            );

            Email_template_config emailTemplateConfig;
            String mailTemplateBody = "";
            String keyPrefix="#$";
            String keyPostfix="$#";

            if(email_template_configList != null && email_template_configList.size() > 0){
                emailTemplateConfig = email_template_configList.get(0);
                mailTemplateBody = emailTemplateConfig.getDynamic_Mail_Body();
                String keyToMatchTemplateProperty;

                for (String key : emailData.getDynamicValues().keySet()){
                    keyToMatchTemplateProperty = keyPrefix+key+keyPostfix;
                    if(mailTemplateBody.contains(keyToMatchTemplateProperty)){
                        mailTemplateBody = mailTemplateBody.replace(keyToMatchTemplateProperty,emailData.getDynamicValues().get(key));
                    }
                }
            }

            mimeMessageHelper.setText(mailTemplateBody,true);

            FileSystemResource fileSystemResource;

            if(emailData.getAttachments() != null && emailData.getAttachments().size() > 0){
                for(String filePath : emailData.getAttachments()){
                    fileSystemResource = new FileSystemResource(new File(filePath));
                    mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
                }

            }
            javaMailSender.send(mimeMessage);
            return "Successfully sent with Dynamic_HTML_Body";
        }
        catch (Exception e){
            return "Error in sendMailWithDynamic_HTML_Body";
        }
    }
}
