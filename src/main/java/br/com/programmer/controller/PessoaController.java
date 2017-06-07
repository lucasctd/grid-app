package br.com.programmer.controller;

import br.com.programmer.relational.entities.Pessoa;
import br.com.programmer.relational.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lucas on 6/3/2017.
 */
@Controller
public class PessoaController {

    @Autowired
    private EntityRepository entityRepository;

    @GetMapping(path = "/Buscar")
    public @ResponseBody Iterable<Pessoa> buscarPessoas(){
        return entityRepository.findAll();
    }

}
