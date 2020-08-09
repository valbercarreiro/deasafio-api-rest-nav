package br.com.desafio.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author valbercarreiro
 *
 */

@Table(name="PATRIMONIO")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(of = { "id" }, callSuper = false )
@ToString(of = { "id" })
public class Patrimonio implements Serializable {
	
	private static final long serialVersionUID = -1359477681631137474L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PATRIMONIO_ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="NUM_TOMBO")
	private String numeroTombamento;
	
	@ManyToOne
	@JoinColumn(name = "MARCA_ID", referencedColumnName = "MARCA_ID")
	private Marca marca;

}
