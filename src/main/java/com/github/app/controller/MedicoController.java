package com.github.app.controller;

import com.github.app.AppApplication;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.github.app.model.medico.DadosAtualizacaoMedico;
import com.github.app.model.medico.DadosCadastroMedico;
import com.github.app.model.medico.DadosListagemMedico;
import com.github.app.model.medico.Medico;
import com.github.app.model.medico.MedicoRepository;

import jakarta.transaction.Transactional;

@RestController // SSPRING WEB- Informa para o Springboot que abaixo é uma classe controladora
                // de requisições (GET-POST-PUT-DELETE).
@RequestMapping("medicos") // // SPRING WEB- Cria um caminho(endpoint) para a classe MedicoController.
public class MedicoController {

    private final AppApplication appApplication;
    @Autowired // Sobrescrevendo algo. É um padrão utilizado na injeção de depêndecia.
    private MedicoRepository repository;

    MedicoController(AppApplication appApplication) {
        this.appApplication = appApplication;
    }

    @PostMapping // SPRING WEB - Informa que o método abaixo é do tipo POST (cadastrar).
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping("todos") // Spring Web - Informa que o método abaixo é do tipo GET (Buscar/ler)
    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("listar")
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
        // findAll() -> Médoto que retorna uma lista de objetos do tipo
        // DadosListagemMedico;

        // stream()-> Método utilizado para tranformar a lista em um fluxo de dados,
        // permitindo aplicar operações de tranformação.

        // map()-> Método utilizado par a converter cada objeto do tipo médio em um json
        // DadoslistagemMedico, ultizando o constructor que criamos em
        // DadosListagemMedico.

        // tolist()-> Método utlizado para coletar os resultados em uma nova lista do
        // tipo DadosListagemMedico, que é o formato que queremo retornar para API.
    }

    @GetMapping
    public Page<DadosListagemMedico> listarPorPagina(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);

        // return repository.findAll().stream().map(DadosListagemMedico::new).toList();

    }

    @PutMapping
    @Transactional // SPRING DATA JPA - Informa ao spring boot que o metodo irá alterar o BD
    public void atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        // var é uma palavra reservada em Java que permite declarar uma variável sem
        // especificar seu tipo. O tipo da variável é inferido pelo compilador com base
        // no valor que foi atribuído a ela.
        medico.atualizarInformacoes(dados);
    }


    // Exclusão - AQUI ESTOU EXCLUINDO MESMO
    @DeleteMapping("/{id}") // precisa incluir / para um pathVariable
    @Transactional // SPRING DATA JPA - Informa ao spring boot que o metodo irá alterar o BD.
    public void excluir(@PathVariable Integer id){ //@PathVariable - Informa que o springboot precisa pegar o caminho variavel (id) e enteder que é um campo chamado id do Médico.
        repository.getReferenceById(id);
        
    }


    // Exclusão Lógica - Uma regra de negocio que permite que um registro seja "excluído" sem ser apagado do Banco de dados.
    @DeleteMapping("/{id}")
    @Transactional // SPRING DATA JPA - Informa ao spring boot que o metodo irá alterar o BD
    public void alterarStatus(@PathVariable Integer id){
         var medico = repository.getReferenceById(id);
         medico.exclusaoLogica();
        

    }

}