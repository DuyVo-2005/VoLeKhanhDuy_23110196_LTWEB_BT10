package vn.khanhduy.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import vn.khanhduy.interceptor.RoleInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private RoleInterceptor roleInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(roleInterceptor).addPathPatterns("/admin/**", "/user/**");
	}
}
