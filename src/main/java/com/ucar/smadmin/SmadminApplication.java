package com.ucar.smadmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**  
 * 测试消费者启动器
 *     
 * @author wubc
 * @date 2018年12月13日 下午2:01:05   
 * @version V1.0 
 */  
@EnableFeignClients
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SmadminApplication {
	private static final Logger logger = LoggerFactory.getLogger(SmadminApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SmadminApplication.class, args);
		logger.info("==================== micro-smadmin 启动成功!====================");
	}
}
