package com.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.app.model.medico.DadosCadastroMedico;
import com.github.app.model.medico.MedicoRepository;
import com.github.app.model.medico.Medico;

@RestController // SPRINGWEB - informa para o Springboot que abaixo é uma classe controladora de requisições (GET-POST_PUT-DELETE).
@RequestMapping("medicos") // SPRINGWEB - Cria um caminho (endpoint) para classe.
public class MedicoController {
    
    @Autowired // Sobrescrevendo algo. É um padrão utilizado na injeção de depêndecia.
    private MedicoRepository repository;
    
    @PostMapping // SPRING WEB - Informa que o método abaixo é do tipo POST (cadastrar).
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }
}
