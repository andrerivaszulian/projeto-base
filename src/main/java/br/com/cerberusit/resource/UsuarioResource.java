package br.com.cerberusit.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<Usuario> criarUsuario(@RequestBody UsuarioForm form) {
		Usuario usuario = this.usuarioService.criarUsuario(form);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Page<Usuario>> buscarUsuarios(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
		Page<Usuario> usuarios = this.usuarioService.buscarTodos(pageable);
		return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.buscarPorId(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping("/{id}/add-perfil")
	public ResponseEntity<Usuario> adicionarPerfil(@RequestBody AddPerfilForm idPerfil, @PathVariable Long id) {
		Usuario usuario = this.usuarioService.addPerfil(id, idPerfil.getIdPerfil());
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@PostMapping("/{id}/bloquear")
	public ResponseEntity<Usuario> bloquear(@PathVariable Long id) {
		Usuario usuario = this.usuarioService.bloquearUsuario(id);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		this.usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
