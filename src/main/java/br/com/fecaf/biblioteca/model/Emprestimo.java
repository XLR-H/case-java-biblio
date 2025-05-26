package br.com.fecaf.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Locatario locatario;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
}
