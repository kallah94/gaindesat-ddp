package com.gaindesat.ddp;

import com.gaindesat.ddp.runner.SetupRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DdpApplication {

	@Bean
	public SetupRunner setupRunner() {
		return new SetupRunner();
	}
	public static void main(String[] args) {
    SpringApplication.run(DdpApplication.class, args);
	}

}
