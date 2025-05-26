package br.com.fecaf.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Locatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String telefone;
}
