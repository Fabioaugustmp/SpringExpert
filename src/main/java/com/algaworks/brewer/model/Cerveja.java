package com.algaworks.brewer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.algaworks.brewer.validation.SKU;


@Entity
@Table(name = "cerveja")
public class Cerveja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@SKU
	@NotBlank(message = "SKU e Obrigatorio")
	private String sku;
	
	@NotBlank(message = "Nome e Obrigatorio")
	private String nome;
	
	@NotBlank(message = "A descricao e Obrigatoria!")
	@Size(min = 1, max = 50, message  = "Descricao deve estar entre 1 e 50!")
	private String descricao;
	
	@NotNull(message = "Valor e Obrigatorio!")
	@DecimalMin("0.01")
	@DecimalMax(value = "999999.99", message = "O valor da Cerveja deve ser menor que R$ 9.999.999,99.")
	private BigDecimal valor;
	
	@NotNull(message = "O Teor Alcoolico e Obrigatorio!")
	@DecimalMax(value = "100.0", message = "O valor do Teor Alcoolico, deve ser menor do que 100.")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;
		
	@NotNull(message = "Comissao e Obrigatoria!")
	@DecimalMax(value = "100", message = "O valor da Comissao, deve ser menor que 100.")
	private BigDecimal comissao;
	
	@NotNull(message = "A quantidade em Estoque e Obrigatoria!")
	@Max(value = 9999, message = "A quantidade de Estoque deve ser menor que 9.999")
	@Column(name = "quantidade_estoque")
	private Long quantidadeEstoque;
	
	@NotNull(message = "A Origem e obrigatoria!")
	@Enumerated(EnumType.STRING)
	private Origem origem;
	
	@NotNull(message = "O Sabor e obrigatorio!")
	@Enumerated(EnumType.STRING)
	private Sabor sabor;
	
	@NotNull(message = "O Estilo e obrigatorio!")
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;
	
	//Callbacks JPA
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
	
	public Cerveja() {
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	public String toString() {
		return "sku: " + sku + " - nome: " + nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
