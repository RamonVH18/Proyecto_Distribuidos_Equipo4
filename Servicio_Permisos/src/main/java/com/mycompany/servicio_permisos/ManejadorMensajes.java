/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_permisos;

import com.sistema.permisos.grpc.IngresoRequest;
import com.sistema.permisos.grpc.IngresoResponse;
import com.sistema.permisos.grpc.NotificacionPermisosServiceGrpc.NotificacionPermisosServiceImplBase;
import io.grpc.stub.StreamObserver;

/**
 *
 * @author Ramon Valencia
 */
public class ManejadorMensajes extends NotificacionPermisosServiceImplBase{
    
    @Override
    public void notificarIngresoUsuario(IngresoRequest request, StreamObserver<IngresoResponse> responseObserver) {
        // Aquí va tu lógica: ¿Qué haces cuando alguien entra?
        System.out.println("Me avisaron que entró: " + request.getUsuarioId());

        // Respondes al servicio de Autenticación
        IngresoResponse resp = IngresoResponse.newBuilder()
            .setMensaje("Recibido, permisos actualizados para " + request.getUsuarioId())
            .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
