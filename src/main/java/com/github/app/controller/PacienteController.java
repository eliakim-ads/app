package com.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.app.model.paciente.DadosAtualizacaoPaciente;
import com.github.app.model.paciente.DadosCadastroPaciente;
import com.github.app.model.paciente.DadosListagemPaciente;
import com.github.app.model.paciente.Paciente;
import com.github.app.model.paciente.PacienteRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    
    @Autowired // Sobrescrevendo algo. É um padrão utilizado na injeção de depêndecia.
    private PacienteRepository repository;
    
    @PostMapping // SPRING WEB - Informa que o método abaixo é do tipo POST (cadastrar).
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping("todos") // Spring Web - Informa que o método abaixo é do tipo GET (Buscar/ler)
    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("listar")
    public List<DadosListagemPaciente> listar() {
        return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
        // findAll() -> Médoto que retorna uma lista de objetos do tipo
        // DadosListagemPaciente;

        // stream()-> Método utilizado para tranformar a lista em um fluxo de dados,
        // permitindo aplicar operações de tranformação.

        // map()-> Método utilizado par a converter cada objeto do tipo médio em um json
        // DadosListagemPaciente, ultizando o constructor que criamos em
        // DadosListagemPaciente.

        // tolist()-> Método utlizado para coletar os resultados em uma nova lista do
        // tipo DadosListagemPaciente, que é o formato que queremo retornar para API.
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPorPagina(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);

        // return repository.findAll().stream().map(DadosListagemPaciente::new).toList();

    }

    @PutMapping 
   @Transactional // SPRING DATA JPA - Informa ao spring boot que o metodo irá alterar o BD
    public void atualizar(@RequestBody DadosAtualizacaoPaciente dados) {
       var paciente = repository.getReferenceById(dados.id());
        // var é uma palavra reservada em Java que permite declarar uma variável sem
        // especificar seu tipo. O tipo da variável é inferido pelo compilador com base
        // no valor que foi atribuído a ela.
      paciente.atualizarInformacoes(dados);
   }

    //Exclusão - AQUI ESTOU EXCLUINDO MESMO
    @DeleteMapping("/{id}") // precisa incluir / para um pathVariable
    @Transactional // SPRING DATA JPA - Informa ao spring boot que o metodo irá alterar o BD.
    public void excluir(@PathVariable Integer id){ //@PathVariable - Informa que o springboot precisa pegar o caminho variavel (id) e enteder que é um campo chamado id do Paciente.
        repository.deleteById(id);
        
    }
}