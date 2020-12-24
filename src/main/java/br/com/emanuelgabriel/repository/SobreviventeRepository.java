package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.model.Sobrevivente;

@Repository
public interface SobreviventeRepository extends JpaRepository<Sobrevivente, Long> {

	Sobrevivente findByNome(String nome);

}
