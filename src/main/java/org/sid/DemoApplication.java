package org.sid;

import org.sid.dao.UserRepository;
import org.sid.entities.AppRole;
import org.sid.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CommandLineRunner start(AccountService accountService, UserRepository userRepository) {
        return args -> {
            accountService.addRole(new AppRole("USER"));
            accountService.addRole(new AppRole("ADMIN"));

            accountService.addUser("admin","admin","admin");
            accountService.addUser("user","user","user");

            accountService.addRoleToUser("ADMIN","admin");

            userRepository.findAll().forEach(System.out::println);
        };
    }
}
