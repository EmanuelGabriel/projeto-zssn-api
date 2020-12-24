package br.com.emanuelgabriel.config.modelmapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.emanuelgabriel.config.dtos.response.ItemModelResponse;
import br.com.emanuelgabriel.model.Item;

@Component
public class ItemModelMapper {

	private ModelMapper modelMapper;

	public ItemModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	public ItemModelResponse toModel(Item item) {
		return this.modelMapper.map(item, ItemModelResponse.class);
	}

	public List<ItemModelResponse> toCollectionModel(List<Item> itens) {
		return itens.stream().map(this::toModel).collect(Collectors.toList());
	}

}
