/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_expendientes;

import com.sistema.expedientes.grpc.AccesoMedicoRequest;
import com.sistema.expedientes.grpc.AccesoMedicoResponse;
import com.sistema.expedientes.grpc.NotificacionAccesoServiceGrpc.NotificacionAccesoServiceImplBase;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Ramon Valencia
 */
@GrpcService
public class NotificationAccesoServiceImpl extends NotificacionAccesoServiceImplBase {

    private final ConcurrentHashMap<String, Integer> medicosActivos = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> documentos = new ConcurrentHashMap<>();

    @Override
    public void registrarAccesoMedico(AccesoMedicoRequest request, StreamObserver<AccesoMedicoResponse> responseObserver) {
        // Guardamos la cédula y el nivel que vienen de Permisos
        medicosActivos.put(request.getCedulaProfesional(), request.getNivelPermiso());
        documentos.put(request.getDocumentoAcceso(), request.getCedulaProfesional());
        System.out.println("Acceso registrado: Méd. " + request.getCedulaProfesional()
                + " con nivel " + request.getNivelPermiso());

        AccesoMedicoResponse response = AccesoMedicoResponse.newBuilder()
            .setRecibidoConExito(true)
            .setMensaje("Ficha de acceso sincronizada")
            .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    // Método para que tus otros servicios de Expedientes verifiquen permisos
    public Integer obtenerNivel(String cedula) {
        return medicosActivos.getOrDefault(cedula, 0); // 0 = Sin acceso
    }

    public Boolean consultarAccesoMedico(String cedula, String documento) {
        return documentos.get(cedula).equals(documento);
    }
}
