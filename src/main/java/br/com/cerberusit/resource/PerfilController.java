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

import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.service.form.PerfilForm;
import br.com.cerberusit.service.interfaces.IPerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private IPerfilService perfilService;
	
	@PostMapping
	public ResponseEntity<Perfil> criarPerfil(@RequestBody PerfilForm form){
		Perfil perfil = this.perfilService.criarPerfil(form);
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Perfil>> buscarTodos(){
		List<Perfil> perfis = this.perfilService.buscarTodos();
		return new ResponseEntity<List<Perfil>>(perfis, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id){
		Perfil perfil = this.perfilService.buscarPorId(id);
		return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
	}
}
