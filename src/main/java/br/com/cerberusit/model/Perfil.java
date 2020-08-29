package br.com.cerberusit.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_perfis", indexes = @Index(name = "idx_t_perfil", columnList = "id", unique = true))
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String role;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_perfil_permissao"
		, joinColumns = @JoinColumn(name = "id_perfil", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_t_perfil_permissao_perfil"))
	    , inverseJoinColumns = @JoinColumn(name = "id_permissao", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_t_perfil_permissao_permissao"))
	)
	private List<Permissao> permissoes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "perfil", fetch = FetchType.LAZY)
	private List<Usuario> usuarios;

	public Perfil(String nome, String role) {
		this.nome = nome;
		this.role = role;
	}
}
