package com.github.app.model.medico;

import com.github.app.model.endereco.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// CLASSE MODELO RESPOSÁVEL POR CRIAR IMA TABELA E SUAS COLUNAS NO BD. ANOTATION
@Getter //Lombok - cria get para todos os atributos.
@Setter //Lombok - cria set para todos os atributos.
@AllArgsConstructor //Lombok - cria um constructor com todos os atributos.
@NoArgsConstructor //Lombok - cria um constructor com nenhum atributos.
@EqualsAndHashCode(of = "id") // Lombok -cria uma logica de comparação através do campo "id".
@Entity //Spring JPA - Informa que a classe abaixo é uma entidade, ou seja, será uma tabela no BD.
@Table(name = "medicos")//   Spring JPA - Opcional gera uma tabela com o nome medicos no BD.

public class Medico {
    @Id // Spring JPA - Informa para o BD que a chave primaria PK, é o Id.
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Spring JPA - Cria o Id único de forma automática.
    private Integer id; // não está vindo do insonia

    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)// Spring JPA - Informa para o BD que o atributo é do tipo enum.
    private Especialidade especialidade;

    @Embedded // utilizada ba classe pai, associa uma entidade a uma tabela auxiliar.
    private Endereco endereco;


        // Constructor com o recebimento dos dados convertendo para objeto
    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());

    }
}
