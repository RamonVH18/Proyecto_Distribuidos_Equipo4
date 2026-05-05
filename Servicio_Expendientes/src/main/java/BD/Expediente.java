/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.time.LocalDateTime;

/**
 *
 * @author Ramon Valencia
 */
public class Expediente {

    private Long idExpediente;
    private String cedulaMedicoResponsable;
    private String nombrePaciente;
    private String diagnostico;
    private LocalDateTime fechaCreacion;

    // Constructor vacío para frameworks (como Jackson/JSON)
    public Expediente() {
    }

    // Constructor rápido para tus pruebas
    public Expediente(Long idExpediente, String cedulaMedicoResponsable, String nombrePaciente, String diagnostico) {
        this.idExpediente = idExpediente;
        this.cedulaMedicoResponsable = cedulaMedicoResponsable;
        this.nombrePaciente = nombrePaciente;
        this.diagnostico = diagnostico;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters (los necesitarás para que Quarkus los convierta a JSON)
    public Long getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Long idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getCedulaMedicoResponsable() {
        return cedulaMedicoResponsable;
    }

    public void setCedulaMedicoResponsable(String cedula) {
        this.cedulaMedicoResponsable = cedula;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombre) {
        this.nombrePaciente = nombre;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
