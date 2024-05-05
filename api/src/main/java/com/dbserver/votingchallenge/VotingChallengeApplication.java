package com.dbserver.votingchallenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Sistema de votação de pautas",
        version = "1",
        description = "API desenvolvida para permitir o processo de votação de pautas em uma assembléia")
)
public class VotingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingChallengeApplication.class, args);
    }

}
