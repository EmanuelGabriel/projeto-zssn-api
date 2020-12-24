package br.com.emanuelgabriel.service;

import java.util.List;

import br.com.emanuelgabriel.model.Item;

public interface ItemService {

	List<Item> findAll();

	Item buscarPorCodigo(Long codigo);

}
