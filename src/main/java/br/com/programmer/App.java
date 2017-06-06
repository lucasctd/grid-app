package br.com.programmer;

import br.com.programmer.grid.GridProvider;
import br.com.programmer.relational.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lucas on 6/3/2017.
 */

@SpringBootApplication
public class App implements CommandLineRunner{

    @Autowired
    private GridProvider grid;

    @Autowired
    private EntityRepository entityRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws IllegalAccessException {
        System.out.println("************** INICIANDO APP ******************");
        grid.setList(entityRepository.findAll());
        grid.setBlockSize(50);
        grid.run();
    }

}
