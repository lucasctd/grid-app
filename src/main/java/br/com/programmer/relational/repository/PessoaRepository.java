package br.com.programmer.relational.repository;

import br.com.programmer.relational.entities.Pessoa;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by lucas on 6/3/2017.
 */
public interface PessoaRepository extends CrudRepository<Pessoa, Integer>{

}
