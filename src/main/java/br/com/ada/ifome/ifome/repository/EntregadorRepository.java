package br.com.ada.ifome.ifome.repository;

import br.com.ada.ifome.ifome.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
}
