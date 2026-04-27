package com.github.app.model.medico;

public record DadosListagemMedico(
    Integer id,
    String nome,
    String email,
    String crm,
    Especialidade especialidade

) {
    // Método construtor recebendo o objeto médico e convertendo para json DadosListagemMedico
    public DadosListagemMedico(Medico medico){
        this( medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

    }
    // O this é para chamar o constructor do record, passando do medico para preencher os atributor da classe.
    // O constructor acima é utilizado para converter um objeto tipo medico em um json tipo DadosListagemMedico, que é o formato que queremo deveolver para nossa API.

    
}



