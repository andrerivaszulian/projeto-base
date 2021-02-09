package br.com.cerberusit.service.form;

import br.com.cerberusit.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioForm {

	private String email;
	private String nome;
	private Long perfil;
	private Endereco endereco;
}
