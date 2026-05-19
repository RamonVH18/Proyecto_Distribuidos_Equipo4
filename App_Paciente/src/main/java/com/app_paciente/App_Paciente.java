package com.app_paciente;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

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
                String msgLower = message.toLowerCase();
                String cedula = message;
                
                if (msgLower.startsWith("solicitud:")) {
                    cedula = message.substring("solicitud:".length());
                }

                System.out.println("\n========================================");
                System.out.println("= Solicitud de acceso a tu Expediente =");
                System.out.println("Médico con cédula: " + cedula);
                System.out.println("========================================");
                
                System.out.println("APROBANDO ACCESO AUTOMÁTICAMENTE...");
                send("APROBADO");
                System.out.println("Respuesta 'APROBADO' enviada.");
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                System.out.println("Desconectado del servidor. Reintentando en 5s...");
            }

            @Override
            public void onError(Exception ex) {
                System.err.println("Error de conexión: " + ex.getMessage());
            }
        };

        while (true) {
            try {
                System.out.println("Intentando conectar a " + uri + "...");
                if (client.connectBlocking()) {
                    break;
                }
            } catch (InterruptedException e) {
                break;
            }
            Thread.sleep(5000);
        }
        
        Thread.currentThread().join();
    }
}
