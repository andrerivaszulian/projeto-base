package br.com.cerberusit.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cerberusit.model.Usuario;
import br.com.cerberusit.repository.interfaces.IUsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> obj = this.usuarioRepository.findByEmail(username);
		Usuario usuario = obj.orElseThrow(()-> new UsernameNotFoundException("Usuario e/ou senha inválidos!"));
		return new UserPrincipal(usuario);
	}
}
