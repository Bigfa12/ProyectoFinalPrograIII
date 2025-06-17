package com.gimnasio.demo;

import com.gimnasio.demo.View.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		Menu menu = context.getBean(Menu.class); // obtenemos la instancia gestionada por Spring
		menu.menu();
	}
}
