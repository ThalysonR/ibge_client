package br.com.evoluum.ibge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
public class EvoIBGEApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvoIBGEApplication.class, args);
	}
}
