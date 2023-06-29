package cn.wyz.serviceverificationcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangnanxiang
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ServiceVerificationCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVerificationCodeApplication.class, args);
	}

}
