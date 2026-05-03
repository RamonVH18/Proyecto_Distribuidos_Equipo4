/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sensor_biometrico;

/**
 *
 * @author norma
 */
public class Sensor_Biometrico {

    public static void main(String[] args) {
        System.out.println("--- Simulador de sensor biométrico ---");
        System.out.println("Simulando que el médico puso su huella...");

        MensajeHuella mensaje = new MensajeHuella(
            "MED-002",          
            "87654321",          
            "Huella-Simulada-002",  
            "PAC-007"            
        );

        MQTTPublisher publisher = new MQTTPublisher();
        publisher.publicarHuella(mensaje);

        System.out.println("--- Proceso terminado ---");
    }
}
