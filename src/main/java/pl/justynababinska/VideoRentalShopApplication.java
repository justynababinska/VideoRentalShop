package pl.justynababinska;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import pl.justynababinska.app.ApplicationController;

@SpringBootApplication
public class VideoRentalShopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(VideoRentalShopApplication.class, args);
		ApplicationController controller = ctx.getBean(ApplicationController.class);
		controller.mainLoop();
	}

	@Bean
	public Scanner scanner() {
		return new Scanner(System.in);
	}
}