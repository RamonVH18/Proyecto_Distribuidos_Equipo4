/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_autenticacion;

/**
 *
 * @author norma
 */
public class MensajeHuella {

    private String idMedico;
    private String cedulaProfesional;
    private String huella;
    private String idPaciente;

    public MensajeHuella() {
    }

    public MensajeHuella(String medicoId, String cedulaProfesional,
            String templateHuella, String pacienteId) {
        this.idMedico = medicoId;
        this.cedulaProfesional = cedulaProfesional;
        this.huella = templateHuella;
        this.idPaciente = pacienteId;
    }

    public String getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getHuella() {
        return huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }
    
}

