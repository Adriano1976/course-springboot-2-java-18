package com.workshop.course.resources;

import com.workshop.course.entities.Category;
import com.workshop.course.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * Classe responsável por controlar e validar a manipulação dos dados da categoria do produto.
 */
@Resource
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    /**
     * Metodo responsavel por listar as categorias para mostrar na view.
     * <p>
     * A anotação {@code @GetMapping} informa que responderar para a requisição HTTP do tipo GET deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os codigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna a lista de categorias da base de dados.
     */
    @ApiOperation(value = "Retorna uma lista as categorias da base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que as categorias foram encontrados com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Metodo responsavel por listar os dados de uma categoria para mostrar na view.
     * <p>
     * A anotação {@code @GetMapping} informa que respondera para a requisição HTTP do tipo GET deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os códigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna a lista de dados de uma categoria.
     */
    @ApiOperation(value = "Retorna uma categoria específico de acordo com o id na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que uma categoria foi encontrado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findByInd(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Metodo responsavel por salvar as informações de uma categoria na base de dados.
     * <p>
     * O atributo {@code produces} esta sendo responsavel em indicar o tipo do conteúdo que ele produz.
     * O atributo {@code consumes} esta sendo responsável em especificar o tipo de conteudo que ele consume.
     *
     * @param objeto Objeto responsavel por receber as informações da categoria e manipular esses dados.
     * @return Retorna ao formulario inicial apo salvar os dados e validado corretamente.
     */

    @ApiOperation(value = "Faz a inserção de uma nova categoria na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que uma nova categoria foi salvo com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Category> insert(@RequestBody Category objeto) {
        objeto = service.insert(objeto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    /**
     * Metodo responsavel por Deletar a categoria escolhido pelo ‘id’.
     * <p>
     * A anotação {@code @DeleteMapping} informa que respondera para a requisição HTTP do tipo DELETE deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna um status de confirmação ou não da remoção da categoria.
     */
    @ApiOperation(value = "Apaga uma categoria específica de acordo com o id na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que a categoria específica foi apagada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Metodo responsavel por Alterar os dados de uma categoria escolhida pelo ‘id’.
     * <p>
     * A anotação {@code @PutMapping} informa que respondera para a requisição HTTP do tipo UPDATE deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os códigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna um status de confirmação ou não da alteração de uma categoria.
     */
    @ApiOperation(value = "Altera uma categoria específica de acordo com o id na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que a categoria foi alterada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        category = service.update(id, category);
        return ResponseEntity.ok().body(category);
    }
}
