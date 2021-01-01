package br.com.emanuelgabriel.config.dtos.response;

import br.com.emanuelgabriel.model.enums.TipoSexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SobreviventeModelResponse {

	private String nome;
	private Integer idade;
	private TipoSexo sexo;
	private Boolean infectado;
	private String latitude;
	private String longitude;
	private ItemParcialModelResponse[] itens;

}
