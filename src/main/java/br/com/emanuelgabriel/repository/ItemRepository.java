package br.com.emanuelgabriel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuelgabriel.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	
	
}
