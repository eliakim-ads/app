package com.github.app.model.paciente;

public record DadosListagemPaciente(
    String nome,
    String email,
    String cpf



) {
    // Método construtor recebendo o objeto médico e convertendo para json DadosListagemPaciente
    public DadosListagemPaciente(Paciente paciente){

        this(paciente.getEmail(), paciente.getCpf(), paciente.getNome());
        

    }
    // O this é para chamar o constructor do record, passando do paciente para preencher os atributor da classe.
    // O constructor acima é utilizado para converter um objeto tipo paciente em um json tipo DadosListagemPaciente, que é o formato que queremo deveolver para nossa API.

    
}