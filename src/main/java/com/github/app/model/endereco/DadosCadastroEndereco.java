package com.github.app.model.endereco;



public record DadosCadastroEndereco(
    String lougradouro, 
    String bairro,
    String cep,
    String complemento,
    String cidade,
    String uf
) {
    
}
