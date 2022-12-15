package br.com.fabricio.cooperativismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.cooperativismo.repository.entity.AssociadoEntity;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, Long>{
	
	AssociadoEntity findByCpf(String cpf);

}
