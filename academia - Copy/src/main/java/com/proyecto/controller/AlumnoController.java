package com.proyecto.controller;

import com.proyecto.domain.Alumno;
import com.proyecto.domain.Curso;
import com.proyecto.repository.AlumnoRepository;
import com.proyecto.repository.CursoRepository;
import com.proyecto.repository.DocenteRepository;
import com.proyecto.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by fuentald on 04/07/2016.
 */

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    CursoRepository cursoRepository;



    @RequestMapping(value = "/alumnos", method = RequestMethod.GET)
    public String alumnos(Model model){
        model.addAttribute("alumnos", this.alumnoRepository.findAll());
        return "alumnosView"; // src/main/resources/templates/@#$.html
    }

    @RequestMapping(value="/masInfoAlumno/{id}", method = RequestMethod.GET)
    public String infoAlumno(@PathVariable Long id, Model model) {
        model.addAttribute("alumno", this.alumnoRepository.findOne(id));
        model.addAttribute("curso", this.cursoRepository.findOne(id));
        return "alumnoMasInfoView";
    }

    @RequestMapping(value="/editarAlumno/{id}", method = RequestMethod.GET)
    public String editAlumno(@PathVariable Long id, Model model) {
        model.addAttribute("alumno", this.alumnoRepository.findOne(id));
        return "alumnoABMView";
    }


    @RequestMapping(value = "/alumnos", params={"save"})
    public String saveAlumno(final Alumno alumno, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "alumnosView";
        }

        this.alumnoRepository.save(alumno);
        model.clear();
        return "redirect:/alumnos";
    }

    @RequestMapping(value = "/alumnos", params={"delete"})
    public String deleteAlumno(final Alumno alumno, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "alumnosView";

        }

        this.alumnoRepository.delete(alumno);
        model.clear();
        return "redirect:/alumnos";
    }


    @RequestMapping(value="/deleteAlumno/{id}", method = RequestMethod.GET)
    public String deleteAlumno(@PathVariable Long id, Model model) {
        this.alumnoRepository.delete(this.alumnoRepository.findOne(id));
        return "redirect:/alumnos";
    }

    @RequestMapping(value="/nuevoAlumno")
    public String newAlumno(
            final ModelMap model) {
        model.addAttribute("alumno", new Alumno());
        return "alumnoABMView";
    }

}
