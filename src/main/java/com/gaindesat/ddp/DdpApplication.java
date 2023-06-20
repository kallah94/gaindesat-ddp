package com.gaindesat.ddp;

import com.gaindesat.ddp.runner.SetupRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DdpApplication {

	@Bean
	public SetupRunner setupRunner() {
		return new SetupRunner();
	}
	public static void main(String[] args) {
    SpringApplication.run(DdpApplication.class, args);
	}

}
