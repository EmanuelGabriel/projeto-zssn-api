package br.com.emanuelgabriel.service;

import java.util.List;

import br.com.emanuelgabriel.model.Sobrevivente;

public interface SobreviventeService {

	Sobrevivente criar(Sobrevivente sobrevivente);

	Sobrevivente buscarPorCodigo(Long codigo);

	Sobrevivente atualizarLocalizacao(Long codigoSobrevivente, Sobrevivente sobrevivente);

	List<Sobrevivente> findAll();

	Sobrevivente reportarContaminacao(Sobrevivente sobrevivente);

}
