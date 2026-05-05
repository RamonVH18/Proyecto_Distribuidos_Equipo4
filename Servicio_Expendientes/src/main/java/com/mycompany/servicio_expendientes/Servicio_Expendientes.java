/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.servicio_expendientes;

import com.sistema.expedientes.grpc.NotificacionAccesoServiceGrpc;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

/**
 *
 * @author Ramon Valencia
 */
public class Servicio_Expendientes {

    public static void main(String[] args) throws InterruptedException {
        int port = 9091;

        Server server = ServerBuilder.forPort(port)
                .addService(new NotificationAccesoServiceImpl())
                .build();

        try {
            server.start();
            System.out.println("Servidor de Expedientes iniciando en el puerto " + port);
            server.awaitTermination();

        } catch (IOException ex) {
            System.getLogger(NotificacionAccesoServiceGrpc.class.getName())
                    .log(System.Logger.Level.ERROR, (String) null, ex);
        
        }

    }
}
