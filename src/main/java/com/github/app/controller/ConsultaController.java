package com.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.app.model.consulta.Consulta;
import com.github.app.model.consulta.ConsultaRepository;
import com.github.app.model.consulta.DadosAgendamentoConsulta;
import com.github.app.model.medico.MedicoRepository;
import com.github.app.model.paciente.PacienteRepository;



@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //Cadastro de uma nova consulta
    @PostMapping
    public Consulta agendar(@RequestBody DadosAgendamentoConsulta dados){
        //Pegar o id do Médico
        var medico = medicoRepository.getReferenceById(dados.medicoId());
        
        // Pegar o id do Paciente
        var paciente = pacienteRepository.getReferenceById(dados.pacienteId());
        
        // Agendar a consulta com o paciente e o medico buscados
        var consulta = new Consulta(dados);
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        return consultaRepository.save(consulta);

    }
    


    
}
