/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_expendientes;
import jakarta.enterprise.event.Observes;
import io.quarkus.runtime.StartupEvent;

/**
 *
 * @author norma
 */
public class StartupBean {

    void onStart(@Observes StartupEvent ev) {
        System.out.println("-- Servicio de Expedientes iniciado ---");
        System.out.println("Servicio escuchando...");
    }
}