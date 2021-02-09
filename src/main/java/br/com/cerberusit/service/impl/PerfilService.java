package br.com.cerberusit.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.cerberusit.dto.PerfilDTO;
import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.repository.interfaces.IPerfilRepository;
import br.com.cerberusit.service.form.PerfilForm;
import br.com.cerberusit.service.interfaces.IPerfilService;
import br.com.cerberusit.service.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PerfilService implements IPerfilService{

	private final IPerfilRepository perfilRepository;
	private final ModelMapper modelMapper;

	@Override
	public List<PerfilDTO> buscarTodos() {
		log.info("BUSCANDO PERFIS");
		List<Perfil> perfis = this.perfilRepository.findAll();
		return Mapper.mapEntidadeParaDtoList(perfis, PerfilDTO.class);
	}

	@Override
	public PerfilDTO buscarPorId(Long id) {
		Perfil perfil = this.perfilRepository.findById(id).get();
		return modelMapper.map(perfil, PerfilDTO.class);
	}

	@Override
	public PerfilDTO criarPerfil(PerfilForm form) {
		Perfil perfil = new Perfil(form.getNome(), criarRole(form.getNome()));
		this.perfilRepository.save(perfil);
		return modelMapper.map(perfil, PerfilDTO.class);
	}

	private String criarRole(String r) {
		return StringUtils.stripAccents("ROLE_" + r.replace(" ", "_").toUpperCase());
	}
}
