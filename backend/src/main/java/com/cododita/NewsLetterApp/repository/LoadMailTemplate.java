package com.cododita.NewsLetterApp.repository;

import com.cododita.NewsLetterApp.bean.Email_template_config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadMailTemplate extends JpaRepository<Email_template_config, String> {

    public List<Email_template_config> findByApplicationNameAndTemplateName(
            String applicationName, String templateName);
}
