package br.com.programmer.norelational.repository;

import br.com.programmer.relational.entities.Pessoa;

import java.io.Serializable;

/**
 * Created by lucas on 6/3/2017.
 */
public interface PessoaNRepository extends Serializable {

    void save(String key, String id, Object object) throws IllegalAccessException;
}
