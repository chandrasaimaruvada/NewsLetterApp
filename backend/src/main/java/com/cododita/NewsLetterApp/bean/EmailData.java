package com.cododita.NewsLetterApp.bean;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class EmailData {

    private String recipient;
    private String mailBody;
    private String mailSubject;
    private List<String> attachments;
    private HashMap<String, String> dynamicValues;
    private String applicationName;
    private String templateName;

    // New field for the HTML file path
    private String htmlFilePath;

    public String getReceipient() {
        return recipient;
    }

    public void setReceipient(String receipient) {
        this.recipient = receipient;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public HashMap<String, String> getDynamicValues() {
        return dynamicValues;
    }

    public void setDynamicValues(HashMap<String, String> dynamicValues) {
        this.dynamicValues = dynamicValues;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    // Getter and Setter for the HTML file path
    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    public void setHtmlFilePath(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }

    @Override
    public String toString() {
        return "EmailData{" +
                "receipient='" + recipient + '\'' +
                ", mailBody='" + mailBody + '\'' +
                ", mailSubject='" + mailSubject + '\'' +
                ", attachments=" + attachments +
                ", dynamicValues=" + dynamicValues +
                ", applicationName='" + applicationName + '\'' +
                ", templateName='" + templateName + '\'' +
                ", htmlFilePath='" + htmlFilePath + '\'' +
                '}';
    }
}
