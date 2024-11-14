package com.cododita.NewsLetterApp.bean;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Email_template_config {

    @Id
    @Column
    private String id;
    @Column
    private String applicationName;
    @Column
    private String templateName;
    @Column
    private String dynamic_Mail_Body;

    public Email_template_config() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDynamic_Mail_Body() {

        return dynamic_Mail_Body;
    }

    public void setDynamic_Mail_Body(String dynamic_Mail_Body) {
        this.dynamic_Mail_Body = dynamic_Mail_Body;
    }

    @Override
    public String toString() {
        return "Email_template_config{" +
                "id='" + id + '\'' +
                ", applicationName='" + applicationName + '\'' +
                ", templateName='" + templateName + '\'' +
                ", dynamic_Mail_Body='" + dynamic_Mail_Body + '\'' +
                '}';
    }
}
