package com.proyecto.domain;

import com.proyecto.domain.Curso;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by fuentald on 24/06/2016.
 */

@Entity
public class Docente {

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

    @OneToMany(mappedBy = "docente")
    private List<Curso> cursos;



    public Docente() {
    }

    public Docente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /*
    @PreRemove
    private void removeDocenteFromcurso() {
        for (Curso c : this.getCursos()) {
            c.setDocente(null);
        }
    }
    */

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

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
