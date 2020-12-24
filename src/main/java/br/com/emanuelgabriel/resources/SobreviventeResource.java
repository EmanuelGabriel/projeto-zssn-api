package br.com.emanuelgabriel.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuelgabriel.config.dtos.request.SobreviventeModelInputRequest;
import br.com.emanuelgabriel.config.dtos.request.SobreviventeModelLocalizacaoRequest;
import br.com.emanuelgabriel.config.dtos.response.SobreviventeModelResponse;
import br.com.emanuelgabriel.config.modelmapper.SobreviventeModelMapper;
import br.com.emanuelgabriel.model.Sobrevivente;
import br.com.emanuelgabriel.repository.SobreviventeRepository;
import br.com.emanuelgabriel.service.SobreviventeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sobreviventes", description = "Gerencia os Sobreviventes")
@RestController
@RequestMapping(value = "/v1/sobreviventes", produces = MediaType.APPLICATION_JSON_VALUE)
public class SobreviventeResource {

	@Autowired
	private SobreviventeService sobreviventeService;

	@Autowired
	private SobreviventeRepository sobreviventeRepository;

	@Autowired
	private SobreviventeModelMapper sobreviventeModelMapper;

	@Operation(description = "Cadastra um sobrevivente", summary = "Cadastra um sobrevivente")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Sobrevivente cadastrado"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)"),
			@ApiResponse(responseCode = "406", description = "Recurso não possui representação que poderia ser aceita pelo consumidor"),
			@ApiResponse(responseCode = "415", description = "Requisição recusada porque o corpo está em um formato não suportado"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<SobreviventeModelResponse> criar(
			@Valid @RequestBody SobreviventeModelInputRequest sobreviventeModelInputRequest) {

		Sobrevivente sobrevivente = this.sobreviventeModelMapper.toDto(sobreviventeModelInputRequest);
		this.sobreviventeModelMapper.toModel(sobreviventeService.criar(sobrevivente));
		URI location = getUri(sobrevivente.getCodigo());
		return ResponseEntity.created(location).build();

	}

	@Operation(description = "Lista os sobreviventes", summary = "Lista os sobreviventes")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK."),
			@ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)"),
			@ApiResponse(responseCode = "404", description = "Sobreviventes não encontrado"),
			@ApiResponse(responseCode = "406", description = "Recurso não possui representação que poderia ser aceita pelo consumidor"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@GetMapping
	public ResponseEntity<List<SobreviventeModelResponse>> listar() {
		List<Sobrevivente> sobreviventes = this.sobreviventeService.findAll();
		return ResponseEntity.ok(this.sobreviventeModelMapper.toCollectionModel(sobreviventes));
	}

	// @GetMapping
	public ResponseEntity<List<Sobrevivente>> findAll() {
		List<Sobrevivente> sobreviventes = this.sobreviventeService.findAll();
		return ResponseEntity.ok(sobreviventes);
	}

	@Operation(description = "Busca um sobrevivente por ID", summary = "Busca um sobrevivente por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "400", description = "ID do sobrevivente inválido"),
			@ApiResponse(responseCode = "404", description = "Sobrevivente não encontrado"),
			@ApiResponse(responseCode = "406", description = "Recurso não possui representação que poderia ser aceita pelo consumidor"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@GetMapping("{codigoSobrevivente}")
	public ResponseEntity<SobreviventeModelResponse> buscarPorCodigo(
			@Parameter(description = "ID de um sobrevivente") @PathVariable Long codigoSobrevivente) {
		Sobrevivente sobrevivente = this.sobreviventeService.buscarPorCodigo(codigoSobrevivente);
		return sobrevivente != null ? ResponseEntity.ok(this.sobreviventeModelMapper.toModel(sobrevivente))
				: ResponseEntity.notFound().build();
	}

	@Operation(description = "Atualiza a localização de um sobrevivente por ID", summary = "Atualiza a localização de um sobrevivente por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)"),
			@ApiResponse(responseCode = "404", description = "Sobrevivente não encontrado"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@PatchMapping("{codigoSobrevivente}/localizacao")
	public ResponseEntity<SobreviventeModelResponse> atualizarLocalizacao(
			@Parameter(description = "ID de um sobrevivente", example = "1", required = true) @PathVariable Long codigoSobrevivente,
			@Parameter(name = "Corpo", description = "Representação de um sobrevivente com os novos dados", required = true) @Valid @RequestBody SobreviventeModelLocalizacaoRequest sobreviventeModelLocalizacaoRequest) {

		Sobrevivente sobrevivente = this.sobreviventeModelMapper.toDto(sobreviventeModelLocalizacaoRequest);
		this.sobreviventeModelMapper
				.toModel(sobreviventeService.atualizarLocalizacao(codigoSobrevivente, sobrevivente));
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(description = "Reportar sobrevivente infectado por ID", summary = "Reportar sobrevivente infectado por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "OK"),
			@ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)"),
			@ApiResponse(responseCode = "404", description = "Sobrevivente não encontrado"),
			@ApiResponse(responseCode = "500", description = "O servidor encontrou um erro não previsto") })
	@PatchMapping("{codigoSobrevivente}/infectado")
	public ResponseEntity<Sobrevivente> reportarContaminacaoSobrevivente(
			@Parameter(description = "ID de um sobrevivente", example = "1", required = true) @PathVariable Long codigoSobrevivente) {

		return this.sobreviventeRepository.findById(codigoSobrevivente).map(sobrevivente -> {
			sobrevivente = this.sobreviventeService.reportarContaminacao(sobrevivente);
			return ResponseEntity.ok().body(sobrevivente);
		}).orElse(ResponseEntity.notFound().build());
	}

	private URI getUri(Long codigo) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}").buildAndExpand(codigo).toUri();
	}

}
