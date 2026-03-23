package com.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.github.app.model.paciente.DadosCadastroPaciente;
import com.github.app.model.paciente.Paciente;
import com.github.app.model.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    
    @Autowired // Sobrescrevendo algo. É um padrão utilizado na injeção de depêndecia.
    private PacienteRepository repository;
    
    @PostMapping // SPRING WEB - Informa que o método abaixo é do tipo POST (cadastrar).
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }
}