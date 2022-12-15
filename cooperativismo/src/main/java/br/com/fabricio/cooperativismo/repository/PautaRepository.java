package br.com.fabricio.cooperativismo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.cooperativismo.repository.entity.PautaEntity;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long>{

}
