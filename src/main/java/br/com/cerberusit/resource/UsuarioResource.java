package br.com.cerberusit.resource;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cerberusit.service.form.AlterarUsuarioForm;
import br.com.cerberusit.service.form.UsuarioForm;
import br.com.cerberusit.service.interfaces.IUsuarioService;
import lombok.RequiredArgsConstructor;
import br.com.cerberusit.dto.UsuarioDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioResource {

	private final IUsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioForm form) {
		UsuarioDTO usuario = this.usuarioService.criarUsuario(form);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios(){
		List<UsuarioDTO> usuarios = this.usuarioService.buscarTodos();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Long id) {
		UsuarioDTO usuario = this.usuarioService.buscarPorId(id);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@PostMapping("/{id}/add-perfil")
	public ResponseEntity<UsuarioDTO> adicionarPerfil(@RequestBody Long idPerfil, @PathVariable Long id) {
		UsuarioDTO usuario = this.usuarioService.addPerfil(id, idPerfil);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@PostMapping("/{id}/bloquear")
	public ResponseEntity<UsuarioDTO> bloquear(@PathVariable Long id) {
		UsuarioDTO usuario = this.usuarioService.bloquearUsuario(id);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		this.usuarioService.deletar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping
	public ResponseEntity<UsuarioDTO> alterarUsuario(@RequestBody AlterarUsuarioForm form){
		UsuarioDTO usuario = this.usuarioService.alterarUsuario(form);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
}
