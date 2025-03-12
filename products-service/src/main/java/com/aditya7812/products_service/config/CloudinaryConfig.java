package com.aditya7812.products_service.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class CloudinaryConfig {

    private String cloudName;

 
    private String apiKey;


    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        Dotenv dotEnv = Dotenv.configure().ignoreIfMissing().load();
        cloudName = dotEnv.get("CLOUDINARY_CLOUD_NAME");
        apiKey = dotEnv.get("CLOUDINARY_API_KEY");
        apiSecret = dotEnv.get("CLOUDINARY_API_SECRET");
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret,
                "secure", true
        ));
    }
}
