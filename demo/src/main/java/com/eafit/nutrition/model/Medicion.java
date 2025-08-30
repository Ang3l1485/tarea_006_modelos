package com.eafit.nutrition.model;

import jakarta.persistence.*;
import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;


@Entity
@Table(name = "medicion")
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "peso", nullable = false)
    private Double peso; // en kg

    @Column(name = "altura", nullable = false)
    private Double altura; // en cm

    @Column(name = "circunferencia_cintura")
    private Double circunferenciaCintura; // en cm

    @Column(name = "circunferencia_cadera")
    private Double circunferenciaCadera; // en cm
    
    @Column(name = "porcentaje_grasa_corporal")
    private Double porcentajeGrasaCorporal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nutricionista_id", nullable = false)
    private Nutricionista nutricionista;

    // Método para calcular el IMC
    public Double calcularIMC() {
        if (altura == null || peso == null || altura <= 0) {
            return null;
        }
        double alturaEnMetros = altura / 100.0;
        return peso / (alturaEnMetros * alturaEnMetros);
    }

    // En la clase Medicion
@JsonProperty("pacienteInfo")
public Map<String, Object> getPacienteInfo() {
    if (paciente == null) {
        return null;
    }
    
    Map<String, Object> info = new HashMap<>();
    info.put("id", paciente.getId());
    info.put("nombre", paciente.getNombre());
    info.put("apellido", paciente.getApellido());
    info.put("email", paciente.getEmail());
    return info;
}

@JsonProperty("nutricionistaInfo")
public Map<String, Object> getNutricionistaInfo() {
    if (nutricionista == null) {
        return null;
    }
    
    Map<String, Object> info = new HashMap<>();
    info.put("id", nutricionista.getId());
    info.put("nombre", nutricionista.getNombre());
    info.put("apellido", nutricionista.getApellido());
    info.put("numeroLicencia", nutricionista.getNumeroLicencia());
    info.put("especialidad", nutricionista.getEspecialidad());
    return info;
}

public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
}

public void setNutricionista(Nutricionista nutricionista) {
    this.nutricionista = nutricionista;
}

public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
} 

public LocalDate getFecha() {
    return fecha;
}

}
