package br.com.cerberusit.service.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioForm {

	private String email;
	private String senha;
	private Long perfil;
}
