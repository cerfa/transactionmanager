package fenn.bank.transaction.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Profile("LOCAL")
public class WebSecurityConfig {
	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Bean("customFilterRegistration")
	@SuppressWarnings("rawtypes")
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration config = new CorsConfiguration();
	    config.addAllowedOrigin("http://localhost:4203");	
		config.addAllowedHeader("*");
		config.addAllowedHeader( "'Cache-Control':'no-cache, no-store, must-revalidate, post-check=0, pre-check=0',"
				+" 'Pragma': 'no-cache',"+
				"'Expires': '0'");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);

		FilterRegistrationBean filterRegistrationBean =
				new FilterRegistrationBean<>(new CorsFilter(source));
		filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);

		return filterRegistrationBean;
	}
 @Bean("mainRestTemplate")
 public RestTemplate restTemplateCreation() {
	 return new RestTemplate(); 
 }
}