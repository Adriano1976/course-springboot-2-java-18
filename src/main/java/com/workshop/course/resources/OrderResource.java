package com.workshop.course.resources;

import com.workshop.course.entities.Order;
import com.workshop.course.services.OrderService;
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
 * Classe responsável por controlar e validar a manipulação dos dados da ordem de compra do produto.
 */
@Resource
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    /**
     * Metodo responsavel por listar as ordens para mostrar na view.
     * <p>
     * A anotação {@code @GetMapping} informa que respondera para a requisição HTTP do tipo GET deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna a lista das ordens.
     */
    @ApiOperation(value = "Retorna uma lista das ordens de serviço da base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que as ordens foram encontradas com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Order>> findAll() {
        List<Order> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Metodo responsavel por listar as informações de uma ordem da base de dados.
     * <p>
     * A anotação {@code @GetMapping} informa que respondera para a requisição HTTP do tipo GET deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna uma lista com os dados de uma ordem.
     */
    @ApiOperation(value = "Retorna uma ordem específica de acordo com o id na base de dados")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que uma ordem foi encontrado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findByInd(id);
        return ResponseEntity.ok().body(obj);
    }

    /**
     * Metodo responsavel por salvar as informacoes de uma ordem na base de dados.
     * <p>
     * O atributo {@code produces} esta sendo responsavel em indicar o tipo do conteúdo que ele produz.
     * O atributo {@code consumes} esta sendo responsável em especificar o tipo de conteúdo que ele consume.
     *
     * @param objeto Objeto responsavel por receber as informações de uma ordem e manipular esses dados.
     * @return Retorna ao formulario inicial após salvar os dados e validado corretamente.
     */

    @ApiOperation(value = "Faz a inserção de uma nova ordem na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que uma nova ordem foi salvo com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Order> insert(@RequestBody Order objeto) {
        objeto = service.insert(objeto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objeto.getId()).toUri();
        return ResponseEntity.created(uri).body(objeto);
    }

    /**
     * Metodo responsavel por Deletar a ordem escolhido pelo ‘id’.
     * <p>
     * A anotacao {@code @DeleteMapping} informa que respondera para a requisição HTTP do tipo DELETE deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna um status de confirmação ou não da remoção de uma ordem.
     */
    @ApiOperation(value = "Apaga uma ordem específica de acordo com o id na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que a ordem específico foi apagado com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Metodo responsavel por Alterar os dados de uma ordem escolhida pelo ‘id’.
     * <p>
     * A anotação {@code @PutMapping} informa que responderar para a requisição HTTP do tipo UPDATE deve cair nesse método.
     * Com isso, o Spring MVC sabe que a view deve ser renderizada para o cliente.
     * As anotações {@code @ApiResponses} e {@code @ApiResponse} são responsaveis por especificar os ccdigos e as mensagens
     * de retorno diretamente no controller.
     *
     * @return Retorna um status de confirmação ou não da alteração dos dados da ordem.
     */
    @ApiOperation(value = "Altera uma ordem específico de acordo com o id na base de dados.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma confirmação de que a ordem foi alterada com sucesso."),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        order = service.update(id, order);
        return ResponseEntity.ok().body(order);
    }
}
