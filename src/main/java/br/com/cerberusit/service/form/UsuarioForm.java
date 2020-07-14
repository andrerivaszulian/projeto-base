package br.com.cerberusit.service.form;

import java.util.List;

import br.com.cerberusit.model.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioForm {

	private String email;
	private String senha;
	private List<Perfil> perfis;
}
