package br.com.fabricio.cooperativismo.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_sessao")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SessaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_pauta")
	private Long idPauta;

	@Column(name = "id_associado")
	private Long idAssociado;

	private String voto;

	public SessaoEntity(Long idPauta, Long idAssociado, String voto) {
		this.idPauta = idPauta;
		this.idAssociado = idAssociado;
		this.voto = voto;
	}

}
