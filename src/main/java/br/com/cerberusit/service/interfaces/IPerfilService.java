package br.com.cerberusit.service.interfaces;

import java.util.List;

import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.service.form.PerfilForm;

public interface IPerfilService {

	List<Perfil> buscarTodos();
	Perfil buscarPorId(Long id);
	Perfil criarPerfil(PerfilForm form);
}
