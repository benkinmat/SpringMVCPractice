package springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import springmvc.converter.RoleToEmployeeConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springmvc")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	RoleToEmployeeConverter roleToEmployeeConverter;

	@Override
	public void addFormatters(FormatterRegistry registry) {

		registry.addConverter(roleToEmployeeConverter);

	}

	@Override
	public void configureViewResolvers( ViewResolverRegistry registry) {
		InternalResourceViewResolver interResourceView = new InternalResourceViewResolver();

		interResourceView.setViewClass(JstlView.class);
		interResourceView.setPrefix("/WEB-INF/views/");
		interResourceView.setSuffix(".jsp");
		
		registry.viewResolver(interResourceView);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");

		return messageSource;
	}
	
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver resolver(){
		
		return new StandardServletMultipartResolver();
		
	}
}
