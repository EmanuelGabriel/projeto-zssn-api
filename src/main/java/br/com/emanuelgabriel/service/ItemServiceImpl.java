package br.com.emanuelgabriel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.model.Item;
import br.com.emanuelgabriel.repository.ItemRepository;
import br.com.emanuelgabriel.service.exception.EntidadeNaoEncontradaException;

@Service
public class ItemServiceImpl implements ItemService {

	private static final String ITEM_COD_NAO_ENCONTRADO = "Item de código não encontrado";

	@Autowired
	private ItemRepository itensRepository;

	@Override
	public List<Item> findAll() {
		return this.itensRepository.findAll();
	}

	@Override
	public Item buscarPorCodigo(Long codigo) {
		return this.itensRepository.findById(codigo)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(ITEM_COD_NAO_ENCONTRADO));
	}

}
