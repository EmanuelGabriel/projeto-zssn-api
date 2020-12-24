package br.com.emanuelgabriel.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emanuelgabriel.config.dtos.response.ItemModelResponse;
import br.com.emanuelgabriel.config.modelmapper.ItemModelMapper;
import br.com.emanuelgabriel.model.Item;
import br.com.emanuelgabriel.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Itens", description = "Gerencia os Itens de um sobrevivente")
@RestController
@RequestMapping(value = "/v1/itens", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemResource {

	@Autowired
	private ItemService itensService;

	@Autowired
	private ItemModelMapper itensModelMapper;

	@Operation(description = "Lista os itens/recursos de um sobrevivente", summary = "Lista os itens/recursos de um sobrevivente")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK."),
			@ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)"),
			@ApiResponse(responseCode = "404", description = "Item não encontrado"),
			@ApiResponse(responseCode = "406", description = "Recurso não possui representação que poderia ser aceita pelo consumidor"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@GetMapping
	public ResponseEntity<List<ItemModelResponse>> listar() {
		return ResponseEntity.ok(this.itensModelMapper.toCollectionModel(this.itensService.findAll()));
	}

	@Operation(description = "Busca um item por ID", summary = "Busca um item por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "ID do item inválido"),
			@ApiResponse(responseCode = "404", description = "Item não encontrado"),
			@ApiResponse(responseCode = "406", description = "Recurso não possui representação que poderia ser aceita pelo consumidor"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@GetMapping("{codigoItem}")
	public ResponseEntity<ItemModelResponse> buscarPorCodigo(
			@Parameter(description = "ID de um item", example = "1", required = true) @PathVariable Long codigoItem) {
		Item item = this.itensService.buscarPorCodigo(codigoItem);
		return item != null ? ResponseEntity.ok(this.itensModelMapper.toModel(item))
				: ResponseEntity.notFound().build();
	}

}
