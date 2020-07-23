package br.com.cerberusit.service.form;

import br.com.cerberusit.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioForm {

	private String email;
	private String senha;
	private Perfil perfil;
}
