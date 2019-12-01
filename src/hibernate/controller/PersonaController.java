package hibernate.controller;

import hibernate.entity.Persona;
import hibernate.service.BookService;
import hibernate.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/personaList")
    public String listPersonas(Model theModel) {
        List<Persona> personaList = personaService.getPersonas();
        theModel.addAttribute("personas", personaList);
        return "list-personas";
    }

    @GetMapping("/addPersonaForm")
    public String addPersonaForm(Model theModel) {
        Persona newPersona = new Persona();
        theModel.addAttribute("persona", newPersona);
        //theModel.addAttribute("books", bookService.getBooks());
        return "add-persona-form";
    }

    @RequestMapping("updatePersonaForm")
    public String updatePersonaForm(
            @RequestParam("personaId") int theId,
            Model theModel) {
        Persona thePersona = personaService.getPersona(theId);
        theModel.addAttribute("persona", thePersona);
        return "add-persona-form";
    }

    @PostMapping("/save")
    public String savePersona(
            @RequestParam(name="personaImage") MultipartFile file,
            @Valid @ModelAttribute(name="persona") Persona thePersona,
            BindingResult bindingResult,
            HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "add-persona-form";
        }

        String applicationPath=request.getServletContext().getRealPath("/");
        personaService.savePersona(thePersona, file, applicationPath);
        return "redirect:/persona/personaList";
    }

    @GetMapping("/delete")
    public String deletePersona(
            @RequestParam("personaId") int theId) {
        personaService.deletePersona(theId);
        return "redirect:/persona/personaList";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("searchTerm") String theSearchTerm,
            Model theModel) {
        List<Persona> matchingPersonas =
                personaService.getPersonasByName(theSearchTerm);
        theModel.addAttribute("personas", matchingPersonas);
        return "list-personas";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor ste = new StringTrimmerEditor(true);

        webDataBinder.registerCustomEditor(String.class, ste);
    }

}
