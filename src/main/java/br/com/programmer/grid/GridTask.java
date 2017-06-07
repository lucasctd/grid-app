package br.com.programmer.grid;

import br.com.programmer.norelational.repository.EntityNRepository;
import br.com.programmer.relational.entities.contract.IEntity;
import org.jppf.node.protocol.AbstractTask;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by lucas on 6/4/2017.
 */
@Service
@Scope(value = "prototype")
public class GridTask <T> extends AbstractTask<String> implements TaskProvider, EntityNRepository {

    private static final long serialVersionUID = 8552345624416910556L;

    private String key;

    private String host;

    private int port;

    private List<T> list;

    private Properties properties;

    public GridTask() throws IOException {
        System.out.println("************* CREATING TASK ********************");
        loadEnvVars();;
    }

    private void loadEnvVars() throws IOException {
        final String propFileName = "redis.properties";
        properties = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(propFileName);
        properties.load(is);
        key = properties.getProperty("key", "entity");
        host = properties.getProperty("host", "127.0.0.1");
        port = Integer.parseInt(properties.getProperty("port", "6379"));
    }

    @Override
    public void setList(List list){
        this.list = list;
    }

    @Override
    public void run() {
        int counter = 0;
        try {
            System.out.println("************* TASK - "+ this.getId() +" - MIGRANDO PARA O BANCO NAO RELACIONAL (REDIS) ********************");
            for (counter = 0; counter < list.size(); counter++){
                IEntity p = (IEntity) list.get(counter);
                this.save(key, p.getId().toString(), p);
            }
            System.out.println("************* MIGRAÇÃO FINALIZADA ********************");
            setResult("Dados migrados com sucesso. Foram migradas "+counter+ " tuplas do banco MySQL para o Redis.");
        } catch (Exception e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }

    public void save(String key, String id, IEntity entity) throws IllegalAccessException {
        Jedis jedis = new Jedis(host, port);
        Map<String, String> obj = new HashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for(int x = 1; x < fields.length; x++){
            fields[x].setAccessible(true);
            obj.put(fields[x].getName(), fields[x].get(entity).toString());
        }
        jedis.hmset(key + ":" + id, obj);
    }
}
