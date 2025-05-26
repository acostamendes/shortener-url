package samples.job.shortenerurl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
   @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/api/**")
                        .allowedOrigins("http://xpto:3000","http://localhost:3000") // Frontend React
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(false); //Get, Post, Delete etc
            }
        };
    }
}
