package br.com.cerberusit.service.form;

import br.com.cerberusit.dto.PerfilDTO;
import br.com.cerberusit.model.Endereco;
import lombok.Data;

@Data
public class AlterarUsuarioForm {
    
    private Long id;
    private String email;
    private String nome;
    private Boolean ativo;
    private Endereco endereco;
    private Long perfil;
}
