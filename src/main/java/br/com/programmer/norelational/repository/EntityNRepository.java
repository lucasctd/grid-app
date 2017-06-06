package br.com.programmer.norelational.repository;

import br.com.programmer.relational.entities.Pessoa;
import br.com.programmer.relational.entities.contract.IEntity;

import java.io.Serializable;

/**
 * Created by lucas on 6/3/2017.
 */
public interface EntityNRepository extends Serializable {

    void save(String key, String id, IEntity entity) throws IllegalAccessException;
}
