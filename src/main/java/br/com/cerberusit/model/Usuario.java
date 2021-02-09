package br.com.cerberusit.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.cerberusit.service.form.UsuarioForm;
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
	private String nome;

	@Embedded
	private Endereco endereco;
	private String senha;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Perfil perfil;

	private LocalDateTime dtInclusao;
	private LocalDateTime dtAlteracao;

	public Usuario(UsuarioForm form, String senha, Perfil perfil){
		this.email = form.getEmail();
		this.nome = form.getNome();
		this.ativo = Boolean.TRUE;
		this.endereco = form.getEndereco();
		this.senha = senha;
		this.perfil = perfil;
	}

	@PreUpdate
	public void update(){
		this.setDtAlteracao(LocalDateTime.now());
	}
}