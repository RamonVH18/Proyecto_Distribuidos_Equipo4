/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sensor_biometrico;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author norma
 */
public class MQTTPublisher {

    private static final String BROKER_URL = "tcp://localhost:1883";
    private static final String TOPIC = "huella.capturada";
    private static final String CLIENT_ID = "sensor-biometrico-01";

    public void publicarHuella(MensajeHuella mensaje) {
        try {
            MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("guest");
            options.setPassword("guest".toCharArray());
            options.setCleanSession(true);

            System.out.println("Conectando al broker MQTT...");
            client.connect(options);
            System.out.println("Conectado.");

            String json = "{"
                + "\"idMedico\":\"" + mensaje.getIdMedico() + "\","
                + "\"cedulaProfesional\":\"" + mensaje.getCedulaProfesional() + "\","
                + "\"huella\":\"" + mensaje.getHuella() + "\","
                + "\"idPaciente\":\"" + mensaje.getIdPaciente()+ "\""
                + "}";

            MqttMessage mqttMessage = new MqttMessage(json.getBytes());
            mqttMessage.setQos(1);

            System.out.println("Publicando huella en topic: " + TOPIC);
            client.publish(TOPIC, mqttMessage);
            System.out.println("Huella publicada: " + json);

            client.disconnect();
            System.out.println("Desconectado del broker.");

        } catch (Exception e) {
            System.err.println("Error al publicar huella: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
