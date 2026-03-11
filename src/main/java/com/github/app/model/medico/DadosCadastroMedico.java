package com.github.app.model.medico;

import com.github.app.model.endereco.DadosCadastroEndereco;

public record DadosCadastroMedico(
    String nome, 
    String email, 
    String crm,
    Especialidade especialidade,
    DadosCadastroEndereco endereco

) {
    
}
 // Essa classe é responsável por pegar os dados que 
 // estão vindo do simulador de requisição(insonia) e 
 // transformar de em atributos (variáveis) da entidade médico.

 // A classe record já cria todos os 
 // gatters e setters constructors, hashcode e equals.