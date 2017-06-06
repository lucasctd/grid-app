package br.com.programmer.grid;

import br.com.programmer.norelational.repository.PessoaNRepository;
import br.com.programmer.relational.entities.Pessoa;
import org.jppf.node.protocol.AbstractTask;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lucas on 6/4/2017.
 */
@Service
@Scope(value = "prototype")
public class GridTask <T> extends AbstractTask<String> implements TaskProvider, PessoaNRepository {

    private static final long serialVersionUID = 8552345624416910556L;

    private static final transient String KEY = "pessoa";

    private static final transient String HOST = "192.168.0.9";

    private static final transient int PORT = 24000;

    private List<T> list;

    public GridTask() {
        System.out.println("************* CREATING TASK ********************");
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
                Pessoa p = (Pessoa) list.get(counter);
                this.save(KEY, p.getId().toString(), p);
            }
            System.out.println("************* MIGRAÇÃO FINALIZADA ********************");
            setResult("Dados migrados com sucesso. Foram migradas "+counter+ " tuplas do banco MySQL para o Redis.");
        } catch (Exception e) {
           System.out.println(e.getMessage());
           e.printStackTrace();
        }
    }

    public void save(String key, String id, Object object) throws IllegalAccessException {
        Jedis jedis = new Jedis(HOST, PORT);
        Map<String, String> obj = new HashMap<>();
        Field[] fields = Pessoa.class.getDeclaredFields();
        for(int x = 1; x < fields.length; x++){
            fields[x].setAccessible(true);
            obj.put(fields[x].getName(), fields[x].get(object).toString());
        }
        jedis.hmset(key + ":" + id, obj);
    }
}
