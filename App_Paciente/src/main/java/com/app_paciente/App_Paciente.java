/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.app_paciente;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.Scanner;

/**
 *
 * @author norma
 */
public class App_Paciente {

    public static void main(String[] args) throws Exception {
        URI uri = new URI("ws://servicio-permisos:8080");

        WebSocketClient client = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshake) {
                System.out.println("--- Conectado al Servicio de Permisos ---");
                System.out.println("Esperando solicitudes de médico...");
            }

            @Override
            public void onMessage(String message) {
                // el mensaje viene como "SOLICITUD:87654321"
                String cedula = message.replace("SOLICITUD:", "");

                System.out.println("= Solicitud de acceso a tu Expediente =");
                System.out.println("Médico con cédula: " + cedula);
                System.out.println("========================================");
                System.out.print("¿Aprobar acceso? (si/no): ");

                Scanner scanner = new Scanner(System.in);
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("si")) {
                    send("APROBADO");
                    System.out.println("Acceso aprobado.");
                } else {
                    send("RECHAZADO");
                    System.out.println("Acceso rechazado.");
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Desconectado del servidor.");
            }

            @Override
            public void onError(Exception ex) {
                System.err.println("Error: " + ex.getMessage());
            }
        };

        client.connect();
        Thread.currentThread().join();
    }
}
