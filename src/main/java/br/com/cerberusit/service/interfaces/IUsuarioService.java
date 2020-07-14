package br.com.cerberusit.service.interfaces;

import java.util.List;

import br.com.cerberusit.model.Usuario;
import br.com.cerberusit.service.form.UsuarioForm;

public interface IUsuarioService {

	Usuario criarUsuario(UsuarioForm form);
	List<Usuario> buscarTodos();
	Usuario addPerfil(Long idUsuario, Long idPerfil);
	Usuario buscarPorId(Long id);
}
