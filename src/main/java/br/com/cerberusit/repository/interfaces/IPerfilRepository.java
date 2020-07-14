package br.com.cerberusit.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cerberusit.model.Perfil;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long> {

}
