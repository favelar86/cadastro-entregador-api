package br.com.ada.ifome.ifome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue
    private Long id;
    private String logradouro;
    private Long numero;
    private String cep;
    private String complemento;
}
