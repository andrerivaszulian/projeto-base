package br.com.cerberusit.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_usuario")
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String senha;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_usuario_perfil"
		, joinColumns = @JoinColumn(name = "id_usuario")
		, inverseJoinColumns = @JoinColumn(name = "id_perfil"))
	@Setter(value = AccessLevel.NONE)
	private List<Perfil> perfis;

	public Usuario(String email, List<Perfil> perfis, String senha) {
		this.email = email;
		this.perfis = perfis;
		this.senha = senha;
	}
}
