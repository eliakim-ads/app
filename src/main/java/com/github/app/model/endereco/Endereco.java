package com.github.app.model.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Endereco {
    private String lougradouro; 
    private String bairro;
    private String cep;
    private String complemento;
    private String cidade;
    private String uf;

    // Constructor recebendo os dados convertidos DTO
    public Endereco (DadosCadastroEndereco dados){
        this.lougradouro = dados.lougradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();

    }

}
