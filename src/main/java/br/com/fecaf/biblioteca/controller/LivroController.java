package br.com.fecaf.biblioteca.controller;

import br.com.fecaf.biblioteca.model.Livro;
import br.com.fecaf.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // READ
    @GetMapping
    public String listarLivros(Model model) {
        model.addAttribute("livros", livroRepository.findAll());
        return "livros";
    }

    // CREATE (Formulário vazio)
    @GetMapping("/novo")
    public String novoLivro(Model model) {
        model.addAttribute("livro", new Livro());
        return "formLivro";
    }

    // SAVE (Create ou Update)
    @PostMapping("/salvar")
    public String salvarLivro(@ModelAttribute Livro livro) {
        livroRepository.save(livro);
        return "redirect:/livros";
    }

    // UPDATE (Formulário preenchido)
    @GetMapping("/editar/{id}")
    public String editarLivro(@PathVariable Integer id, Model model) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("livro", livro);
        return "formLivro";
    }

    // DELETE
    @GetMapping("/excluir/{id}")
    public String excluirLivro(@PathVariable Integer id) {
        livroRepository.deleteById(id);
        return "redirect:/livros";
    }
}
