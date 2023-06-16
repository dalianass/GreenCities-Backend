package org.i4di.green.configuration;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "daq9ulbte";
    private final String API_KEY = "789544995539378";
    private final String API_SECRET = "32YgHVE1wUqrLuZ_RrMet5Hp1LE";
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }
}