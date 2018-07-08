package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Pagamento;

/*
 * Comentário Explicação
 * 
 * Faz as funções de gravar no banco de dados do pagamento;
 * 
 */
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>
{		
}
