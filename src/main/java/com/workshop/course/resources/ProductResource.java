package com.workshop.course.resources;

import com.workshop.course.entities.Product;
import com.workshop.course.services.ProductService;
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

@Resource
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

    @Autowired
    private ProductService service;

    /**
     * Metodo responsavel por listar os produtos para mostrar na view.
     * <p>
     * A anotação {@code @GetMapping} informa que respondera para a requisição HTTP do tipo GET deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna a lista de produtos.
     */
    @ApiOperation(value = "Retorna uma lista dos produtos da base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que os produtos foram encontrados com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Metodo responsavel por listar os dados de um produto da base de dados.
     * <p>
     * A anotação {@code @GetMapping} informa que respondera para a requisição HTTP do tipo GET deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna uma lista de informações com os dados de um produtos.
     */
    @ApiOperation(value = "Retorna um produtos específico de acordo com o id na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que um produtos foi encontrado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findByInd(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Metodo responsavel por salvar as informacoes do produto na base de dados.
     * <p>
     * O atributo {@code produces} esta sendo responsavel em indicar o tipo do conteúdo que ele produz.
     * O atributo {@code consumes} esta sendo responsável em especificar o tipo de conteudo que ele consume.
     *
     * @param objeto Objeto responsavel por receber as informações do produto e manipular esses dados.
     * @return Retorna ao formulario inicial apo salvar os dados e validado corretamente.
     */

    @ApiOperation(value = "Faz a inserção de um novo produtos na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que um novo produto foi salvo com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Product> insert(@RequestBody Product objeto) {
        objeto = service.insert(objeto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    /**
     * Metodo responsavel por Deletar o produto escolhido pelo ‘id’.
     * <p>
     * A anotação {@code @DeleteMapping} informa que respondera para a requisição HTTP do tipo DELETE deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna um status de confirmação ou não da remossão do produto.
     */
    @ApiOperation(value = "Remove um produto específico de acordo com o id na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que o produto específico foi apagado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Metodo responsavel por Alterar os dados de um produto escolhido pelo ‘id’.
     * <p>
     * A anotação {@code @PutMapping} informa que respondera para a requisição HTTP do tipo UPDATE deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna um status de confirmação ou não da alteração dos dados do produto.
     */
    @ApiOperation(value = "Altera um produto específico de acordo com o id na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que o produto foi alterado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product = service.update(id, product);
        return ResponseEntity.ok().body(product);
    }
}
