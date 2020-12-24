package br.com.emanuelgabriel.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emanuelgabriel.model.Sobrevivente;
import br.com.emanuelgabriel.repository.SobreviventeRepository;
import br.com.emanuelgabriel.service.exception.EntidadeNaoEncontradaException;
import br.com.emanuelgabriel.service.exception.RegraNegocioException;

@Service
public class SobreviventeServiceImpl implements SobreviventeService {

	private static final String SOBREVIVENTE_COD_NAO_ENCONTRADO = "Sobrevivente de código não encontrado";
	private static final String SOBREVIVENTE_NOME_EXISTENTE = "Já existe um sobrevivente registrado com este nome";

	@Autowired
	private SobreviventeRepository sobreviventeRepository;

	@Transactional
	@Override
	public Sobrevivente criar(Sobrevivente sobrevivente) {

		Sobrevivente sobreviventeExistente = this.sobreviventeRepository.findByNome(sobrevivente.getNome());
		if (sobreviventeExistente != null && !sobreviventeExistente.equals(sobrevivente)) {
			throw new RegraNegocioException(SOBREVIVENTE_NOME_EXISTENTE);
		}

		sobrevivente.getItens().forEach(it -> {

		});

		sobrevivente.setInfectado(Boolean.FALSE);
		return sobreviventeRepository.save(sobrevivente);
	}

	@Override
	public List<Sobrevivente> findAll() {
		return this.sobreviventeRepository.findAll();
	}

	@Override
	public Sobrevivente buscarPorCodigo(Long codigo) {
		return this.sobreviventeRepository.findById(codigo)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(SOBREVIVENTE_COD_NAO_ENCONTRADO));
	}

	@Transactional
	@Override
	public Sobrevivente atualizarLocalizacao(Long codigoSobrevivente, Sobrevivente sobrevivente) {
		return this.sobreviventeRepository.findById(codigoSobrevivente).map(sobre -> {
			sobre.setLatitude(sobrevivente.getLatitude());
			sobre.setLongitude(sobrevivente.getLongitude());
			return this.sobreviventeRepository.save(sobre);
		}).orElseThrow(() -> new EntidadeNaoEncontradaException(SOBREVIVENTE_COD_NAO_ENCONTRADO));
	}

	@Override
	public Sobrevivente reportarContaminacao(Sobrevivente sobrevivente) {

		// int numeroContaminados = sobrevivente.getNumeroContaminacao() + 1;

//		if (numeroContaminados >= 3) {
//			sobrevivente.setInfectado(true);
//		}

		// sobrevivente.setNumeroContaminacao(numeroContaminados);

		return this.sobreviventeRepository.save(sobrevivente);
	}

}
