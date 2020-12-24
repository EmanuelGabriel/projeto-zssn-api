package br.com.emanuelgabriel.config.dtos.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemModelInputRequest {

	@NotNull(message = "Código do item não pode ser nulo")
	@Positive(message = "O código do item deve ser maior que zero")
	private Long codigo;

}
