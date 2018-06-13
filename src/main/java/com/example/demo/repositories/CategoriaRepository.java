package com.example.demo.repositories;

import org.springframework.stereotype.Repository;
import com.example.demo.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>
{		
	
}
