package br.com.programmer.config;

import br.com.programmer.grid.Grid;
import br.com.programmer.grid.GridProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lucas on 6/3/2017.
 */
@Configuration
public class AppConfig {

    @Bean
    public GridProvider createRedisConn(){
        return new Grid();
    }

}
