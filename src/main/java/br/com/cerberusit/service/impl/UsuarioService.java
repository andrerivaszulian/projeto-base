package br.com.cerberusit.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.model.Usuario;
import br.com.cerberusit.repository.interfaces.IPerfilRepository;
import br.com.cerberusit.repository.interfaces.IUsuarioRepository;
import br.com.cerberusit.dto.UsuarioDTO;
import br.com.cerberusit.service.form.AlterarUsuarioForm;
import br.com.cerberusit.service.form.UsuarioForm;
import br.com.cerberusit.service.interfaces.IPerfilService;
import br.com.cerberusit.service.interfaces.IUsuarioService;
import br.com.cerberusit.service.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

	private final PasswordEncoder pe;
	private final ModelMapper modelMapper;
	private final IPerfilService perfilService;
	private final IPerfilRepository perfilRepository;
	private final IUsuarioRepository usuarioRepository;

	@Override
	public UsuarioDTO criarUsuario(UsuarioForm form) {
		log.info("CRIANDO NOVO USUÁRIO: {} - {}", form.getNome(), form.getEmail());
		Perfil perfil = this.perfilService.buscarPorId(form.getPerfil());
		Usuario usuario = new Usuario(form, pe.encode("123mudar"), perfil);
		this.usuarioRepository.save(usuario);
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public List<UsuarioDTO> buscarTodos() {
		log.info("BUSCANDO TODOS OS USUÁRIOS");
		List<Usuario> entidades = this.usuarioRepository.findAll();
		return Mapper.mapEntidadeParaDtoList(entidades, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO buscarPorId(Long id) {
		log.info("BUSCANDO USUÁRIO DE ID {}", id);
		Usuario usuario = this.usuarioRepository.findById(id).get();
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO addPerfil(Long idUsuario, Long idPerfil) {
		Usuario u = this.usuarioRepository.findById(idUsuario).get();
		Perfil perfil = this.perfilRepository.findById(idPerfil).get();
		u.setPerfil(perfil);
		Usuario usuario = this.usuarioRepository.save(u);
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO bloquearUsuario(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).get();
		usuario.setAtivo(!usuario.getAtivo());
		this.usuarioRepository.save(usuario);
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	@Override
	public void deletar(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).get();
		if (usuario != null) {
			this.usuarioRepository.delete(usuario);
		}
	}

	@Override
	public UsuarioDTO alterarUsuario(AlterarUsuarioForm form) {
		Usuario entidade = this.usuarioRepository.getOne(form.getId());
		Perfil perfil = this.perfilRepository.findById(form.getPerfil()).get();
		BeanUtils.copyProperties(form, entidade);
		entidade.setPerfil(perfil);
		this.usuarioRepository.save(entidade);
		return modelMapper.map(entidade, UsuarioDTO.class);
	}
}
