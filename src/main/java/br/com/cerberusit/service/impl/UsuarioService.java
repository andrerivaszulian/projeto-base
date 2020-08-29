package br.com.cerberusit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.model.Usuario;
import br.com.cerberusit.repository.interfaces.IUsuarioRepository;
import br.com.cerberusit.service.form.UsuarioForm;
import br.com.cerberusit.service.interfaces.IPerfilService;
import br.com.cerberusit.service.interfaces.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{
	
	@Autowired
	private PasswordEncoder pe;
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	private IPerfilService perfilService;

	@Override
	public Usuario criarUsuario(UsuarioForm form) {
		Perfil perfil = this.perfilService.buscarPorId(form.getPerfil());
		Usuario usuario = Usuario.criarUsuarioPadrao(form.getEmail(), perfil, pe.encode(form.getSenha()));
		this.usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public Page<Usuario> buscarTodos(Pageable pageable) {
		Page<Usuario> usuarios = this.usuarioRepository.findAll(pageable);
		return usuarios;
	}

	@Override
	public Usuario buscarPorId(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).get();
		return usuario;
	}

	@Override
	public Usuario addPerfil(Long idUsuario, Long idPerfil) {
		Usuario u = this.usuarioRepository.findById(idUsuario).get();
		Perfil perfil = this.perfilService.buscarPorId(idPerfil);
		u.setPerfil(perfil);
		Usuario usuario = this.usuarioRepository.save(u);
		return usuario;
	}

	@Override
	public Usuario bloquearUsuario(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).get();
		usuario.setAtivo(!usuario.getAtivo());
		this.usuarioRepository.save(usuario);
		return usuario;
	}

	@Override
	public void deletar(Long id) {
		Usuario usuario = this.usuarioRepository.findById(id).get();
		if(usuario != null) {
			this.usuarioRepository.delete(usuario);
		}
	}
}
