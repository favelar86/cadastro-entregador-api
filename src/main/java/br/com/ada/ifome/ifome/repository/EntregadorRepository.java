package br.com.ada.ifome.ifome.repository;

import br.com.ada.ifome.ifome.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {
    Optional<Entregador> findByCpf(String cpf);
}
