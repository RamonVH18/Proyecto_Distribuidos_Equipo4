/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servicio_autenticacion;

import com.rabbitmq.client.*;

/**
 *
 * @author norma
 */
public class MensajeHuellaConsumer {

    private static final String RABBITMQ_HOST = "localhost";
    private static final String QUEUE_NAME = "mqtt-subscription-huella.capturada-qos1";
    private final BDAutenticacion verificador = new BDAutenticacion();

    public void iniciar() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBITMQ_HOST);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.queueBind(QUEUE_NAME, "amq.topic", "huella.capturada");
        System.out.println("Esperando mensajes de huella en: " + QUEUE_NAME);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            try {
                String json = new String(delivery.getBody(), "UTF-8");
                System.out.println("Mensaje recibido: " + json);

                MensajeHuella mensaje = toJson(json);

                boolean autenticado = verificador.verificarMedico(
                        mensaje.getCedulaProfesional(),
                        mensaje.getHuella(),
                        mensaje.getIdMedico(),
                        mensaje.getIdPaciente()
                );

                if (autenticado) {
                    System.out.println("Acceso AUTORIZADO para médico: " + mensaje.getIdMedico());
                } else {
                    System.out.println("Acceso DENEGADO para médico: " + mensaje.getIdMedico());
                }
            } catch (Exception e) {
                System.err.println("Error procesando mensaje: " + e.getMessage());
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }

    private MensajeHuella toJson(String json) {
        MensajeHuella mensaje = new MensajeHuella();
        mensaje.setIdMedico(extraerValor(json, "idMedico"));
        mensaje.setCedulaProfesional(extraerValor(json, "cedulaProfesional"));
        mensaje.setHuella(extraerValor(json, "huella"));
        mensaje.setIdPaciente(extraerValor(json, "idPaciente"));
        return mensaje;
    }

    private String extraerValor(String json, String clave) {
        String buscar = "\"" + clave + "\":\"";
        int inicio = json.indexOf(buscar) + buscar.length();
        int fin = json.indexOf("\"", inicio);
        return json.substring(inicio, fin);
    }
}
