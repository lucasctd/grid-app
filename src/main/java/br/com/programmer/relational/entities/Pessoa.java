package br.com.programmer.relational.entities;

import br.com.programmer.relational.entities.contract.IEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by lucas on 6/3/2017.
 */
@Entity
public class Pessoa implements IEntity{

    private static final long serialVersionUID = -4914210871035056504L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
