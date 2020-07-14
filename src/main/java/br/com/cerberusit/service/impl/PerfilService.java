package br.com.cerberusit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.repository.interfaces.IPerfilRepository;
import br.com.cerberusit.service.form.PerfilForm;
import br.com.cerberusit.service.interfaces.IPerfilService;

@Service
public class PerfilService implements IPerfilService{

	@Autowired
	private IPerfilRepository perfilRepository;

	@Override
	public List<Perfil> buscarTodos() {
		List<Perfil> perfis = this.perfilRepository.findAll();
		return perfis;
	}

	@Override
	public Perfil buscarPorId(Long id) {
		Perfil perfil = this.perfilRepository.findById(id).get();
		return perfil;
	}

	@Override
	public Perfil criarPerfil(PerfilForm form) {
		Perfil perfil = new Perfil(form.getNome(), criarRole(form.getNome()));
		this.perfilRepository.save(perfil);
		return perfil;
	}

	private String criarRole(String r) {
		String role = "ROLE_" + r.replace(" ", "_").toUpperCase();
		return role;
	}
}
