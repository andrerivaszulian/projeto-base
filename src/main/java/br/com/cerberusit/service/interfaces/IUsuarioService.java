package br.com.cerberusit.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cerberusit.model.Usuario;
import br.com.cerberusit.service.form.UsuarioForm;

public interface IUsuarioService {

	Usuario criarUsuario(UsuarioForm form);
	Page<Usuario> buscarTodos(Pageable pageable);
	Usuario addPerfil(Long idUsuario, Long idPerfil);
	Usuario buscarPorId(Long id);
	Usuario bloquearUsuario(Long id);
	void deletar(Long id);
}
