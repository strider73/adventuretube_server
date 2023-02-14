package com.adventuretube.data.member;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MemberConfig {


    @Bean
    CommandLineRunner commandLineRunner(MemberRepository repository){
       return args -> {
         Member chris =     new Member(
                 "Chris Lee",
                 "https://www.youtube.com/channel/UCMg4QJXtDH-VeoJvlEpfEYg",
                 "strider.lee@gmail.com"
         );

           Member bella =     new Member(
                   "Bella  Lee",
                   "https://www.youtube.com/channel/UCMg4QJXtDH-VeoJvlEpfEYg",
                   "bella.lee@gmail.com"
           );

           repository.saveAll(List.of(chris,bella));

       };

    }
}
