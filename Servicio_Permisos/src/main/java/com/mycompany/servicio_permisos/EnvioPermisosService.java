/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_permisos;

import com.sistema.expedientes.grpc.AccesoMedicoRequest;
import com.sistema.expedientes.grpc.AccesoMedicoResponse;
import com.sistema.expedientes.grpc.NotificacionAccesoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import utils.Configuracion;

/**
 *
 * @author Ramon Valencia
 */

public class EnvioPermisosService {
    private final ManagedChannel channelGrpc = ManagedChannelBuilder.forAddress
        (Configuracion.get("host"), 9091)
        .usePlaintext() // Sin cifrado para pruebas locales
        .build();

    private final NotificacionAccesoServiceGrpc.NotificacionAccesoServiceBlockingStub permisosStub 
        = NotificacionAccesoServiceGrpc.newBlockingStub(channelGrpc);

    /**
     * Envía la información del médico al Servicio de Expedientes.
     * @param cedula La cédula profesional del médico.
     * @param nivel El nivel de permiso (1, 2, 3...).
     * @param documento
     */
    public void enviarDatosAcceso(String cedula, int nivel, String documento) {
        try {
            // Construimos el mensaje basado en el .proto
            AccesoMedicoRequest request = AccesoMedicoRequest.newBuilder()
                    .setCedulaProfesional(cedula)
                    .setNivelPermiso(nivel)
                    .setDocumentoAcceso(documento)
                    .build();

            // Llamada síncrona/bloqueante
            AccesoMedicoResponse response = permisosStub.registrarAccesoMedico(request);

            if (response.getRecibidoConExito()) {
                System.out.println("Éxito: " + response.getMensaje());
            } else {
                System.err.println("Expedientes rechazó el registro: " + response.getMensaje());
            }

        } catch (Exception e) {
            System.err.println("Error de comunicación gRPC: " + e.getMessage());
            // Aquí podrías implementar una lógica de reintento si eres "más fuerte"
        }
    }

    // Método para cerrar el canal cuando el servicio se destruya
    public void shutdown() {
        if (channelGrpc != null && !channelGrpc.isShutdown()) {
            channelGrpc.shutdown();
        }
    }
}
