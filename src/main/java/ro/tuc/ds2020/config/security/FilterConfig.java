package ro.tuc.ds2020.config.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.tuc.ds2020.jwt.JwtRequestFilter;
import ro.tuc.ds2020.jwt.JwtUtil;
import ro.tuc.ds2020.services.security.CustomUserDetailsService;

@Configuration
public class FilterConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public FilterConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public FilterRegistrationBean registerJwtFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(customUserDetailsService, new JwtUtil());
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}