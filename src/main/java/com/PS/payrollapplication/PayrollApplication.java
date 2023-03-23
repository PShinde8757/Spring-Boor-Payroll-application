package com.PS.payrollapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j

//@SpringBootConfiguration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class PayrollApplication {
	public static void main(String... args) {
		SpringApplication.run(PayrollApplication.class,args);
		log.info("PayRollApplication is Started... ");

		System.out.println("This is Cinema THeater App");

	}


}
