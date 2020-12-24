package br.com.emanuelgabriel.config.dtos.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.emanuelgabriel.model.enums.TipoSexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SobreviventeModelInputRequest {

	@NotBlank(message = "O campo nome não pode ser vazio")
	@Size(min = 5, max = 100, message = "O campo nome deve conter entre {min} a {max} caracteres")
	private String nome;

	private Integer idade;

	private TipoSexo sexo;

	@NotBlank(message = "Campo latitude não pode ser vazio")
	private String latitude;

	@NotBlank(message = "Campo longitude não pode ser vazio")
	private String longitude;

	@Valid
	@NotNull(message = "Itens do sobrevivente não pode ser nulo")
	private ItemModelInputRequest[] itens;

}
