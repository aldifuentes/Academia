package com.proyecto.domain;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Alumno {
	
	   @Id
	   @GeneratedValue
	   private Long id;

	   private String dni;
	   private String nombre;
	   private String apellido;

	   @Column(nullable = true)
	   @Temporal(TemporalType.DATE)
	   @DateTimeFormat(pattern="yyyy-MM-dd")
	   private Date fechaNacimiento;

	   private String direccion;
	   private String telefono;
	   private String correo;

		/*@OneToMany(mappedBy="idalumno", cascade = CascadeType.ALL)
	   private List<Nota> notas;
	   */

	   @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	   private List<Curso> cursos; 

	public Alumno() {
	}

	public Alumno(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Alumno(String dni, String nombre, String apellido, Date fechaNacimiento, String direccion, String telefono, String correo) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}
	
	/*public Alumno(List<Nota> notas, List<Curso> cursos) {
		super();
		this.notas = notas;
		this.cursos = cursos;
	}*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public List<Nota> getNotas() {
		return notas;
	}

	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
*/

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}


	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String toString(){
		return this.getNombre() + " " + this.getApellido();
	}
}

