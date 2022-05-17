package lt.skarb.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
class WebCorsConfig implements WebFluxConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        var corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
//        var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        log.info("configured cors: {}", source);
//        return source;
//    }
}
