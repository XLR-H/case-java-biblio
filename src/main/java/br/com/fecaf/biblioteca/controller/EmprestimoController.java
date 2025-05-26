package br.com.fecaf.biblioteca.controller;

import br.com.fecaf.biblioteca.model.Emprestimo;
import br.com.fecaf.biblioteca.model.Livro;
import br.com.fecaf.biblioteca.model.Locatario;
import br.com.fecaf.biblioteca.repository.EmprestimoRepository;
import br.com.fecaf.biblioteca.repository.LivroRepository;
import br.com.fecaf.biblioteca.repository.LocatarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;
    private final LocatarioRepository locatarioRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository,
                                LivroRepository livroRepository,
                                LocatarioRepository locatarioRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
        this.locatarioRepository = locatarioRepository;
    }

    @GetMapping
    public String listarEmprestimos(Model model) {
        model.addAttribute("emprestimos", emprestimoRepository.findAll());
        return "emprestimos";
    }

    @GetMapping("/novo")
    public String novoEmprestimo(Model model) {
        model.addAttribute("emprestimo", new Emprestimo());
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("locatarios", locatarioRepository.findAll());
        return "formEmprestimo";
    }

    @PostMapping("/salvar")
    public String salvarEmprestimo(@ModelAttribute Emprestimo emprestimo) {
        emprestimoRepository.save(emprestimo);
        return "redirect:/emprestimos";
    }

    @GetMapping("/editar/{id}")
    public String editarEmprestimo(@PathVariable Integer id, Model model) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("emprestimo", emprestimo);
        model.addAttribute("livros", livroRepository.findAll());
        model.addAttribute("locatarios", locatarioRepository.findAll());
        return "formEmprestimo";
    }

    @GetMapping("/excluir/{id}")
    public String excluirEmprestimo(@PathVariable Integer id) {
        emprestimoRepository.deleteById(id);
        return "redirect:/emprestimos";
    }
}
