package br.com.ada.ifome.ifome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Entregador {

    @Id
    private Long id;
    private String nome;

    @Column(unique = true, nullable = false)

    private String cpf;
    private String email;
    private String tamanhoCamisa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
}
