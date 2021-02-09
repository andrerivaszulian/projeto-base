package br.com.cerberusit.service.interfaces;

import java.util.List;

import br.com.cerberusit.dto.UsuarioDTO;
import br.com.cerberusit.service.form.AlterarUsuarioForm;
import br.com.cerberusit.service.form.UsuarioForm;

public interface IUsuarioService {

	UsuarioDTO criarUsuario(UsuarioForm form);
	List<UsuarioDTO> buscarTodos();
	UsuarioDTO addPerfil(Long idUsuario, Long idPerfil);
	UsuarioDTO buscarPorId(Long id);
	UsuarioDTO bloquearUsuario(Long id);
	UsuarioDTO alterarUsuario(AlterarUsuarioForm form);
	void deletar(Long id);
}
