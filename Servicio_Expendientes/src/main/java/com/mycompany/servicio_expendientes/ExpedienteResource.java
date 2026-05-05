/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_expendientes;

import BD.BDExpediente;
import BD.Expediente;
import io.quarkus.grpc.GrpcService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *
 * @author Ramon Valencia
 */
@Path("/expedientes")
@Produces(MediaType.APPLICATION_JSON)
public class ExpedienteResource {

    @Inject // Quarkus buscará la instancia @Singleton que ya tiene el gRPC
    @GrpcService
    NotificationAccesoServiceImpl nasi;
    private BDExpediente bd = new BDExpediente();

    @GET
    @Path("/{id}/{cedula}")
    public Expediente getExpediente(@PathParam("id") String id, @PathParam("cedula") String cedula) {

        if (nasi.consultarAccesoMedico(cedula, id)) {
            return bd.buscarPorId(id);
        }
        return null;
    }
}
