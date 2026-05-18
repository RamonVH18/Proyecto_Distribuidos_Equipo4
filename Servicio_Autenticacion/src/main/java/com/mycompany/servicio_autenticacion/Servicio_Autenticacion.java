/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.servicio_autenticacion;

/**
 *
 * @author Ramon Valencia 
 */
public class Servicio_Autenticacion {

    public static void main(String[] args) throws Exception {
        System.out.println("--- Servicio de Autenticación iniciado ---");

        MensajeHuellaConsumer consumer = new MensajeHuellaConsumer();
        consumer.iniciar();

        System.out.println("Servicio escuchando...");
        Thread.currentThread().join();
    }
}
