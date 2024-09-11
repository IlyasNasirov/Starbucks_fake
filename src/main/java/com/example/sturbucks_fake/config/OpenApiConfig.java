package com.example.sturbucks_fake.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() throws UnknownHostException {

        //Get hostname for foreign connection to swagger
        String host = InetAddress.getLocalHost().getHostName();

        return new OpenAPI()
                .servers(
                        List.of(new Server().description("rinnov34's server").url("http://" + host + ":8080")
                        )
                )
                .info(new Info()
                        .title("Starbucks API")
                        .description("API documentation for the Starbucks application.")
                        .version("1.0.0")
                        .contact(new Contact().name("Ilyas Nasirov").email("ilyasnasirov9@gmail.com"))
                );
    }

}
