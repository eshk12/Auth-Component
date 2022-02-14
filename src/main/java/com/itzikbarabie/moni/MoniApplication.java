package com.itzikbarabie.moni;

import com.itzikbarabie.moni.Handlers.RestErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class MoniApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoniApplication.class, args);
	}

}
