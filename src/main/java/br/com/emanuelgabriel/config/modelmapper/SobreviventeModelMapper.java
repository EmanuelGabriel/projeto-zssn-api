package br.com.emanuelgabriel.config.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.emanuelgabriel.config.dtos.request.SobreviventeModelInputRequest;
import br.com.emanuelgabriel.config.dtos.request.SobreviventeModelLocalizacaoRequest;
import br.com.emanuelgabriel.config.dtos.response.SobreviventeModelResponse;
import br.com.emanuelgabriel.model.Sobrevivente;

@Component
public class SobreviventeModelMapper {

	private ModelMapper modelMapper;

	public SobreviventeModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public SobreviventeModelResponse toModel(Sobrevivente sobrevivente) {
		return this.modelMapper.map(sobrevivente, SobreviventeModelResponse.class);
	}

	public Sobrevivente toDto(SobreviventeModelInputRequest sobreviventeModelInputRequest) {
		return this.modelMapper.map(sobreviventeModelInputRequest, Sobrevivente.class);
	}

	public Sobrevivente toDto(SobreviventeModelLocalizacaoRequest sobreviventeModelLocalizacaoRequest) {
		return this.modelMapper.map(sobreviventeModelLocalizacaoRequest, Sobrevivente.class);
	}

	public List<SobreviventeModelResponse> toCollectionModel(List<Sobrevivente> sobreviventes) {
		return sobreviventes.stream().map(this::toModel).collect(Collectors.toList());
	}

}
