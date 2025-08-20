package com.njaimed.literalura.challenge;

import com.njaimed.literalura.challenge.catalog.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraChallengeApplication  implements CommandLineRunner {

    @Autowired
    private final Menu menu;
    public LiteraluraChallengeApplication(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menu.showMenu();
    }

}
