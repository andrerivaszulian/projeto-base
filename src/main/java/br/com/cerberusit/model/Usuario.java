package br.com.cerberusit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_usuario")
public class Usuario {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String email;
	private Boolean ativo;
	@JsonIgnore
	private String senha;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Perfil perfil;

	public static Usuario criarUsuarioPadrao(String email, Perfil perfil, String senha) {
		return Usuario.builder()
			.email(email)
			.senha(senha)
			.perfil(perfil)
			.ativo(Boolean.TRUE)
			.build();
	}
}
