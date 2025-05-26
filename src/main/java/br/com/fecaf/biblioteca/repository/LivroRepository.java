package br.com.fecaf.biblioteca.repository;

import br.com.fecaf.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
