package com.odcchina.cafoserver;

import com.odcchina.cafoserver.config.GracefulShutdownTomcat;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@MapperScan({"com.odcchina.cafoserver.mapper"})
public class CarServerApplication {

    @Autowired
    private GracefulShutdownTomcat gracefulShutdownTomcat;

    public static void main(String[] args) {
        SpringApplication.run(CarServerApplication.class, args);
        log.info("Cafo Server 启动完成");
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(gracefulShutdownTomcat);
        return tomcat;
    }
}