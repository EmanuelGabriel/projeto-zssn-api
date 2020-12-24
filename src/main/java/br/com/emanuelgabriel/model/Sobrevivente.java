package br.com.emanuelgabriel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.emanuelgabriel.model.enums.TipoSexo;

@Entity
@Table(name = "sobrevivente")
public class Sobrevivente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;

	private Integer idade;

	@Enumerated(EnumType.STRING)
	private TipoSexo sexo;

	private String latitude;

	private String longitude;

	private Boolean infectado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sobrevivente_itens", joinColumns = @JoinColumn(name = "sobrevivente_codigo"), inverseJoinColumns = @JoinColumn(name = "item_codigo"))
	private List<Item> itens = new ArrayList<>();

	public Sobrevivente() {
	}

	public Sobrevivente(Long codigo, String nome, Integer idade, TipoSexo sexo, String latitude, String longitude,
			Boolean infectado) {
		this.codigo = codigo;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.latitude = latitude;
		this.longitude = longitude;
		this.infectado = infectado;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public TipoSexo getSexo() {
		return sexo;
	}

	public void setSexo(TipoSexo sexo) {
		this.sexo = sexo;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Boolean getInfectado() {
		return infectado;
	}

	public void setInfectado(Boolean infectado) {
		this.infectado = infectado;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
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
		Sobrevivente other = (Sobrevivente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
