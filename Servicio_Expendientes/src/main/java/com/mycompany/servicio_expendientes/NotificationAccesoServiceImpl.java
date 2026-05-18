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
import jakarta.inject.Singleton;
import java.util.concurrent.ConcurrentHashMap;
import jwt.ValidadorJwt;

/**
 *
 * @author Ramon Valencia
 */
@GrpcService
@Singleton
public class NotificationAccesoServiceImpl extends NotificacionAccesoServiceImplBase {

    private final ConcurrentHashMap<String, String> tokens = new ConcurrentHashMap<>();

    @Override
    public void registrarAccesoMedico(AccesoMedicoRequest request,
            StreamObserver<AccesoMedicoResponse> responseObserver) {

        tokens.put(request.getDocumentoAcceso(), request.getToken());
        System.out.println("Token registrado para documento: " + request.getDocumentoAcceso());

        AccesoMedicoResponse response = AccesoMedicoResponse.newBuilder()
                .setRecibidoConExito(true)
                .setMensaje("Token de acceso registrado")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public Boolean consultarAccesoMedico(String cedula, String documento) {
        String token = tokens.get(documento);

        if (token == null) {
            System.out.println("No hay token para documento: " + documento);
            return false;
        }

        boolean valido = ValidadorJwt.validarToken(token, cedula, documento);
        System.out.println("Token válido: " + valido + " para médico: " + cedula);
        return valido;
    }
}