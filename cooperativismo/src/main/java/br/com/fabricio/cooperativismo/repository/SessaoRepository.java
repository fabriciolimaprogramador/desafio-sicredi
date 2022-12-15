package br.com.fabricio.cooperativismo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fabricio.cooperativismo.repository.entity.SessaoEntity;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long>{
	
	List<SessaoEntity> findByIdPauta(Long idPauta);
	
	SessaoEntity findByIdPautaAndIdAssociado(Long idPauta, Long idAssociado);

}
