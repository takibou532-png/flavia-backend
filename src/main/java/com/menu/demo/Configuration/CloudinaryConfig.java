package com.menu.demo.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzogezyhb",
                "api_key", "882365381946991",
                "api_secret", "Vo_sYHc0kFZu5JtpmLeji6bC8yQ"
        ));
    }
}
