package vn.khanhduy.configs.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import vn.khanhduy.configs.StorageProperties;
import vn.khanhduy.controller.interceptor.AuthInterceptor;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class WebConfig implements WebMvcConfigurer {
	@Autowired
    private StorageProperties storageProperties;
	
	@Autowired
    private AuthInterceptor authInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/admin/**", "/user/**") // chỉ chặn các url cần phân quyền
                .excludePathPatterns("/login", "/logout", "/css/**", "/js/**"); // bỏ qua
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ánh xạ URL /uploads/** đến thư mục vật lý
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + storageProperties.getLocation() + "/");
    }
}
