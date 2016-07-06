package com.proyecto.controller;


import com.proyecto.repository.AlumnoRepository;
import com.proyecto.repository.DocenteRepository;
import com.proyecto.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proyecto.domain.Docente;
import com.proyecto.domain.Horario;
import com.proyecto.domain.Curso;
import com.proyecto.repository.CursoRepository;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

	@Autowired
	DocenteRepository docenteRepository;

	@Autowired
	HorarioRepository horarioRepository;

	@Autowired
	AlumnoRepository alumnoRepository;
	
	@RequestMapping(value = "/cursos", method = RequestMethod.GET)
	public String cursos(Model model){
	    model.addAttribute("cursos", this.cursoRepository.findAll());
	    return "cursosView"; // src/main/resources/templates/@#$.html
	}

	@RequestMapping(value = "/cursos", params={"save"})
	public String saveCurso(final Curso curso, final BindingResult bindingResult, final ModelMap model) {
        if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
            return "cursosView";
        }

		Horario h = new Horario(curso.getHorario().getDias(),  curso.getHorario().getHorainicio(), curso.getHorario().getHorafin());
		this.horarioRepository.save(h);
		curso.setHorario(h);


        this.cursoRepository.save(curso);
        model.clear();
        return "redirect:/cursos";
	}
	
	@RequestMapping(value = "/cursos", params={"delete"})
	public String deleteCurso(final Curso curso, final BindingResult bindingResult, final ModelMap model) {
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
            return "cursosView";
            
        }

        this.cursoRepository.delete(curso);
        model.clear();
        return "redirect:/cursos";
	}

	@RequestMapping(value="/deleteCurso/{id}", method = RequestMethod.GET)
	public String deleteCurso(@PathVariable Long id, Model model) {
		this.cursoRepository.delete(this.cursoRepository.findOne(id));
		return "redirect:/cursos";
	}


	@RequestMapping(value="/masInfoCurso/{id}", method = RequestMethod.GET)
	public String infoCurso(@PathVariable Long id, Model model) {
	    model.addAttribute("curso", this.cursoRepository.findOne(id));
		model.addAttribute("docente", this.docenteRepository.findOne(id));

		return "cursoMasInfoView";
	}

	@RequestMapping(value="/editarCurso/{id}", method = RequestMethod.GET)
	public String editCurso(@PathVariable Long id, Model model) {
	    model.addAttribute("curso", this.cursoRepository.findOne(id));
		//model.addAttribute("docente", this.docenteRepository.findOne(id));
		model.addAttribute("docentes", this.docenteRepository.findAll());
	    return "cursoABMView";
	}
	
	@RequestMapping(value="/nuevoCurso")
    public String newCurso(
            final ModelMap model) {
        model.addAttribute("curso", new Curso());
        return "cursoABMView";
    }

}
