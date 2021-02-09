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

import br.com.cerberusit.dto.PerfilDTO;
import br.com.cerberusit.model.Perfil;
import br.com.cerberusit.service.form.PerfilForm;
import br.com.cerberusit.service.interfaces.IPerfilService;

@RestController
@RequestMapping("/perfis")
public class PerfilResource {

	@Autowired
	private IPerfilService perfilService;
	
	@PostMapping
	public ResponseEntity<PerfilDTO> criarPerfil(@RequestBody PerfilForm form){
		PerfilDTO perfil = this.perfilService.criarPerfil(form);
		return new ResponseEntity<>(perfil, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PerfilDTO>> buscarTodos(){
		List<PerfilDTO> perfis = this.perfilService.buscarTodos();
		return new ResponseEntity<>(perfis, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PerfilDTO> buscarPorId(@PathVariable Long id){
		PerfilDTO perfil = this.perfilService.buscarPorId(id);
		return new ResponseEntity<>(perfil, HttpStatus.OK);
	}
}
