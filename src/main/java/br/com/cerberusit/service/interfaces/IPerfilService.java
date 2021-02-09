package br.com.cerberusit.service.interfaces;

import java.util.List;

import br.com.cerberusit.dto.PerfilDTO;
import br.com.cerberusit.service.form.PerfilForm;

public interface IPerfilService {

	List<PerfilDTO> buscarTodos();
	PerfilDTO buscarPorId(Long id);
	PerfilDTO criarPerfil(PerfilForm form);
}
