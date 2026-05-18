/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.servicio_permisos;

import websocket.ServidorWebSocket;
import com.sistema.permisos.grpc.NotificacionPermisosServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import utils.Configuracion;

/**
 *
 * @author Ramon Valencia
 */
public class Servicio_Permisos {

    public static void main(String[] args) throws InterruptedException {
        int port = Configuracion.getInt("port");
        int wsPort = Configuracion.getInt("websocket.port"); 

        ServidorWebSocket wsServer = new ServidorWebSocket(wsPort);
        wsServer.start();

        Server server = ServerBuilder.forPort(port)
                .addService(new ManejadorMensajes(wsServer))
                .build();

        try {
            server.start();
            System.out.println("--- Servicio de Permisos iniciado --- " + port);
            System.out.println("Servicio escuchando...");
            server.awaitTermination();
        } catch (IOException ex) {
            System.err.println("Error iniciando servidor: " + ex.getMessage());
        }
    }
}
