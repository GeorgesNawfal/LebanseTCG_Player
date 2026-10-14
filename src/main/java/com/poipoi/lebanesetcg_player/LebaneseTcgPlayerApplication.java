package com.poipoi.lebanesetcg_player;

import com.poipoi.lebanesetcg_player.configs.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Import(SecurityConfig.class)
@SpringBootApplication
public class LebaneseTcgPlayerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LebaneseTcgPlayerApplication.class, args);
    }
}