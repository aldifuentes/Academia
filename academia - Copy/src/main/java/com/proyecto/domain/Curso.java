package com.proyecto.domain;


import javax.persistence.*;
import com.proyecto.domain.Docente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.time.*;




@Entity
public class Curso {

	 @Id
	 @GeneratedValue
	 private Long id;
	 
	 private String nombre; 
	 private String descripcion;
	 
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date fechainicio;
	 
	 @Temporal(TemporalType.DATE)
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	 private Date fechafin;
	 
	 private int cupo;
	 private int costo;

	 @ManyToOne
	 private Docente docente;

	 @OneToOne
	 private Horario horario;

	 @ManyToMany(mappedBy="cursos", cascade={CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.EAGER)
	 private List<Alumno> alumnos;

	 
	public Curso(){
		
	}
	 
	
	public Curso(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}




	public Curso(String nombre, String descripcion , Date fechaincio, Date fechafin, int cupo, int costo,
			Horario horario, List<Alumno> alumnos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.horario = horario;
		this.cupo = cupo;
		this.costo = costo;
		this.horario = horario;
		this.alumnos = alumnos;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}


	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	 
	public void addAlumno(Alumno alumno){
		this.alumnos.add(alumno);
	}

	public void deleteAlumno(Alumno alumno){
		this.alumnos.remove(alumno);
	}

	public void deleteAllAlumnos(){
		this.alumnos.removeAll(alumnos);
	}


	 

}
