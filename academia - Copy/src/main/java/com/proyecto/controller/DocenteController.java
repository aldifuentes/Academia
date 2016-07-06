package com.proyecto.controller;


import com.proyecto.domain.Curso;
import com.proyecto.domain.Docente;
import com.proyecto.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DocenteController {

    @Autowired
    private DocenteRepository docenteRepository;

    @RequestMapping(value = "/docentes", method = RequestMethod.GET)
    public String docentes(Model model){
        model.addAttribute("docentes", this.docenteRepository.findAll());
        return "docentesView"; // src/main/resources/templates/@#$.html
    }

    @RequestMapping(value="/masInfoDocente/{id}", method = RequestMethod.GET)
    public String infoDocente(@PathVariable Long id, Model model) {
        model.addAttribute("docente", this.docenteRepository.findOne(id));
        return "docenteMasInfoView";
    }

    @RequestMapping(value="/editarDocente/{id}", method = RequestMethod.GET)
    public String editDocente(@PathVariable Long id, Model model) {
        model.addAttribute("docente", this.docenteRepository.findOne(id));
        return "docenteABMView";
    }


    @RequestMapping(value = "/docentes", params={"save"})
    public String saveDocente(final Docente docente, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "docentesView";
        }

        this.docenteRepository.save(docente);
        model.clear();
        return "redirect:/docentes";
    }

    @RequestMapping(value = "/docentes", params={"delete"})
    public String deleteDocente(final Docente docente, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "docentesView";

        }

        this.docenteRepository.delete(docente);
        model.clear();
        return "redirect:/docentes";
    }

    @RequestMapping(value="/nuevoDocente")
    public String newDocente(
            final ModelMap model) {
        model.addAttribute("docente", new Docente());
        return "docenteABMView";
    }

}

