package com.proyecto.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by fuentald on 30/06/2016.
 */

@Entity
public class Horario {

    @Id
    @GeneratedValue
    private Long id;

    public enum Dia {
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horainicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime horafin;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Dia> dias;

    public Horario() {
    }

    public Horario(List<Dia> dias, LocalTime horainicio, LocalTime horafin) {
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.dias = dias;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Dia> getDias() {
        return dias;
    }

    public void setDias(List<Dia> dias) {
        this.dias = dias;
    }

    public LocalTime getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(LocalTime horainicio) {
        this.horainicio = horainicio;
    }

    public LocalTime getHorafin() {
        return horafin;
    }

    public void setHorafin(LocalTime horafin) {
        this.horafin = horafin;
    }
}
