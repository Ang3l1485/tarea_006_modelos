package com.eafit.nutrition.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "nutricionista")
public class Nutricionista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "numero_licencia", nullable = false, length = 50, unique = true)
    private String numeroLicencia;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;
    
    // Relaciones (se definen más adelante)
    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Paciente> pacientes = new ArrayList<>();

    @OneToMany(mappedBy = "nutricionista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Nota> notas = new ArrayList<>();

// Método helper para añadir notas
    public void addNota(Nota nota) {
     notas.add(nota);
        nota.setNutricionista(this);
    }

    
    // Constructor vacío requerido por JPA
    public Nutricionista() {
    }
    
    // Constructor con parámetros principales
    public Nutricionista(String nombre, String apellido, String numeroLicencia, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroLicencia = numeroLicencia;
        this.email = email;
    }

    public void addPaciente(Paciente paciente) {
        pacientes.add(paciente);
        paciente.setNutricionista(this);
    }


    // Getters y setters (omitidos por brevedad)

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isActivo() {
        return activo;
    }

}
