/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_permisos;

import websocket.ServidorWebSocket;
import BD.BDPermisos;
import com.sistema.permisos.grpc.IngresoRequest;
import com.sistema.permisos.grpc.IngresoResponse;
import com.sistema.permisos.grpc.NotificacionPermisosServiceGrpc.NotificacionPermisosServiceImplBase;
import io.grpc.stub.StreamObserver;
import jwt.ServicioJwt;

/**
 *
 * @author Ramon Valencia
 */
public class ManejadorMensajes extends NotificacionPermisosServiceImplBase {

    private BDPermisos permisos = new BDPermisos();
    private EnvioPermisosService envio = new EnvioPermisosService();
    private ServidorWebSocket wsServer;

    public ManejadorMensajes(ServidorWebSocket wsServer) {
        this.wsServer = wsServer;
    }

    @Override
    public void notificarIngresoUsuario(IngresoRequest request, StreamObserver<IngresoResponse> responseObserver) {

        String cedulaProfesional = request.getCedulaProfesional();
        System.out.println("Médico autenticado recibido: " + cedulaProfesional);

        if (!permisos.verificarPermisosMedico(cedulaProfesional)) {
            IngresoResponse resp = IngresoResponse.newBuilder()
                    .setMensaje("Médico sin permisos registrados: " + cedulaProfesional)
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
            return;
        }

        System.out.println("Notificando al paciente para que apruebe el acceso...");
        boolean aprobado = wsServer.notificarYEsperarAprobacion(cedulaProfesional);

        if (!aprobado) {
            System.out.println("Paciente rechazó o no respondió. Acceso denegado.");
            IngresoResponse resp = IngresoResponse.newBuilder()
                    .setMensaje("Acceso denegado por el paciente.")
                    .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
            return;
        }

        System.out.println("Paciente aprobó. Generando token de acceso...");
        String permiso = permisos.getPermisoMedico(cedulaProfesional).toString();
        String documento = permisos.getDocumentoAcceso(cedulaProfesional);

        String token = ServicioJwt.generarToken(cedulaProfesional, documento, permiso);
        System.out.println("Token generado para médico: " + cedulaProfesional);

        envio.enviarDatosAcceso(cedulaProfesional, permiso, documento, token);

        IngresoResponse resp = IngresoResponse.newBuilder()
                .setMensaje("Acceso autorizado por el paciente para: " + cedulaProfesional)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
