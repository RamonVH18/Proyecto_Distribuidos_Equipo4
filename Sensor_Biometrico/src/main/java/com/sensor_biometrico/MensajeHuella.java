/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sensor_biometrico;

/**
 *
 * @author norma
 */
public class MensajeHuella {

    private String idMedico;
    private String cedulaProfesional;
    private String huella;
    private String idPaciente;

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

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public String getHuella() {
        return huella;
    }

    public String getIdPaciente() {
        return idPaciente;
    }
}
