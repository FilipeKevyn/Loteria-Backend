package com.project.loteria.config;


import com.project.loteria.entities.Pool;
import com.project.loteria.entities.User;
import com.project.loteria.repositories.PoolRepository;
import com.project.loteria.repositories.UserRepository;
import com.project.loteria.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PoolRepository poolRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Filipe", "@jj");
        User friend = new User("João", "@jj");

        repository.save(user);

        Pool pool = new Pool("title");
        poolRepository.save(pool);

        user.getPools().add(pool);

        repository.save(user);
    }
}
