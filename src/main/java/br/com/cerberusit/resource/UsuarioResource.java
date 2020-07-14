package br.com.cerberusit.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cerberusit.model.Usuario;
import br.com.cerberusit.service.form.AddPerfilForm;
import br.com.cerberusit.service.form.UsuarioForm;
import br.com.cerberusit.service.interfaces.IUsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private IUsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioForm form){
		Usuario usuario = this.usuarioService.criarUsuario(form);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> buscarUsuarios(){
		List<Usuario> usuarios = this.usuarioService.buscarTodos();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
		Usuario usuario = this.usuarioService.buscarPorId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/add-perfil")
	public ResponseEntity<Usuario> adicionarPerfil(@RequestBody AddPerfilForm idPerfil, @PathVariable Long id){
		Usuario usuario = this.usuarioService.addPerfil(id, idPerfil.getIdPerfil());
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
}
