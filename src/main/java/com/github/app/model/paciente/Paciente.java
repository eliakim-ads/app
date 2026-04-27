package com.github.app.model.paciente;

import com.github.app.model.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// CLASSE MODELO RESPOSÁVEL POR CRIAR IMA TABELA E SUAS COLUNAS NO BD.
@Getter //Lombok - cria get para todos os atributos.
@Setter //Lombok - cria set para todos os atributos.
@AllArgsConstructor //Lombok - cria um constructor com todos os atributos.
@NoArgsConstructor //Lombok - cria um constructor com nenhum atributos.
@EqualsAndHashCode(of = "id") // Lombok -cria uma logica de comparação através do campo "id".
@Entity //Spring JPA - Informa que a classe abaixo é uma entidade, ou seja, será uma tabela no BD.
@Table(name = "pacientes")//   Spring JPA - Opcional gera uma tabela com o nome medicos no BD.

public class Paciente {
    @Id // Spring JPA - Informa para o BD que a chave primaria PK, é o Id.
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Spring JPA - Cria o Id único de forma automática.
    private Integer id; // não está vindo do insonia
    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded // Utiliza na classe pai, associa uma entidade a uma tabela auxiliar.
    private Endereco endereco;



        // Constructor com o recebimento dos dados convertendo para objeto
    public Paciente(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();


    }



        // Método para verificar a atualização do médico, recebendo um objeto do tipo DadosAtualizacaopaciente e atualizando os atributos do paciente com os dados recebidos na requisição.
    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        // Verifica se o nome recebido é diferente de null, ou seja, se o nome foi
        // enviado na requisição da atualização (PUT), e se for diferente de null,
        // atualiza o nome do médico com o novo nome recebido.
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
