package br.com.cerberusit.dto;

import br.com.cerberusit.model.Endereco;
import lombok.Data;

@Data
public class UsuarioDTO {
    
    private Long id;
    private String email;
    private Boolean ativo;
    private String nome;
    private Endereco endereco;
    private PerfilDTO perfil;
}
