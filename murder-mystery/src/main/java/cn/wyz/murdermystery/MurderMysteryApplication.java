package cn.wyz.murdermystery;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wnx
 */
@EnableFeignClients
@SpringBootApplication
@MapperScan({"cn.wyz.murdermystery.mapper"})
public class MurderMysteryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MurderMysteryApplication.class, args);
	}

}
