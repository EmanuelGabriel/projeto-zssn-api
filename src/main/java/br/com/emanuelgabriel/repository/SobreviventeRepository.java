package br.com.emanuelgabriel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.emanuelgabriel.model.Sobrevivente;

@Repository
public interface SobreviventeRepository extends JpaRepository<Sobrevivente, Long> {

	Sobrevivente findByNome(String nome);

	@Query("SELECT s FROM Sobrevivente s JOIN FETCH s.itens WHERE s.codigo = ?1")
	Optional<Sobrevivente> buscarPorId(Long codigo);

	@Query("SELECT DISTINCT s FROM Sobrevivente s JOIN FETCH s.itens")
	List<Sobrevivente> buscarTodos();

	@Query("UPDATE Sobrevivente s SET s.latitude = :sobrevivente, s.longitude = :sobrevivente WHERE s.codigo = :codigoSobrevivente")
	Optional<Sobrevivente> updateLocalizacao(@Param("codigoSobrevivente") Long codigoSobrevivente,
			@Param("sobrevivente") Sobrevivente sobrevivente);

}
