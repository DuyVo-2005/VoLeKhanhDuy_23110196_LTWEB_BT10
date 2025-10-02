package vn.khanhduy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "vn.khanhduy.entities")
public class Bt269Application {

	public static void main(String[] args) {
		SpringApplication.run(Bt269Application.class, args);
	}

}
