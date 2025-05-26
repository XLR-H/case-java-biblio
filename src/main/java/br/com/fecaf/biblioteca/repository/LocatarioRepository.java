package br.com.fecaf.biblioteca.repository;

import br.com.fecaf.biblioteca.model.Locatario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatarioRepository extends JpaRepository<Locatario, Integer> {
}
