package br.com.fecaf.biblioteca.controller;

import br.com.fecaf.biblioteca.model.Locatario;
import br.com.fecaf.biblioteca.repository.LocatarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/locatarios")
public class LocatarioController {

    private final LocatarioRepository locatarioRepository;

    public LocatarioController(LocatarioRepository locatarioRepository) {
        this.locatarioRepository = locatarioRepository;
    }

    // READ
    @GetMapping
    public String listarLocatarios(Model model) {
        model.addAttribute("locatarios", locatarioRepository.findAll());
        return "locatarios";
    }

    // CREATE (Formulário vazio)
    @GetMapping("/novo")
    public String novoLocatario(Model model) {
        model.addAttribute("locatario", new Locatario());
        return "formLocatario";
    }

    // SAVE (Create ou Update)
    @PostMapping("/salvar")
    public String salvarLocatario(@ModelAttribute Locatario locatario) {
        locatarioRepository.save(locatario);
        return "redirect:/locatarios";
    }

    // UPDATE (Formulário preenchido)
    @GetMapping("/editar/{id}")
    public String editarLocatario(@PathVariable Integer id, Model model) {
        Locatario locatario = locatarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("locatario", locatario);
        return "formLocatario";
    }

    // DELETE
    @GetMapping("/excluir/{id}")
    public String excluirLocatario(@PathVariable Integer id) {
        locatarioRepository.deleteById(id);
        return "redirect:/locatarios";
    }
}
