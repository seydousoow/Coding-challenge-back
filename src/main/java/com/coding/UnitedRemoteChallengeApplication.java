package com.coding;

import com.coding.entities.Shop;
import com.coding.entities.User;
import com.coding.repositories.ShopRepository;
import com.coding.repositories.UserRepository;
import org.hibernate.validator.constraints.EAN;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class UnitedRemoteChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitedRemoteChallengeApplication.class, args);
    }

    /**
     * The Bean passwordEncoder will be used to encode the user's password with BCrypt encryption
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Create some users and shops to fill the database since we are using h2, an embedded database
     */
    @Bean
    @Transactional
    CommandLineRunner runner(UserRepository userRepository, ShopRepository shopRepository) {
        return args -> {

            User user1 = new User();
            user1.setFirstName("Paul");
            user1.setLastName("Smith");
            user1.setEmail("paulsmith@gmail.com");
            user1.setPassword(passwordEncoder().encode("paulsmith"));
            user1.setActive(true);

            User user2 = new User();
            user2.setFirstName("David");
            user2.setLastName("Hernandez");
            user2.setEmail("david@gmail.com");
            user2.setPassword(passwordEncoder().encode("hernandez"));
            user2.setActive(true);

            Random random = new Random();
            Arrays.asList("P&J", "Dolce Gabbana", "Luis Vuitton", "Calvin Klein", " Gucci", "Dior", "Hugo Boss",
                    "Channel", "H&M", "Adidas", "Nike")
                    .forEach(name -> {
                        shopRepository.save(new Shop(null, name, Math.floor(random.nextDouble() * 99)));
                    });

            Arrays.asList(user1, user2).forEach(userRepository::save);

        };
    }

}
