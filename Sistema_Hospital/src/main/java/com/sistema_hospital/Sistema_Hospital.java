/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.sistema_hospital;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

/**
 *
 * @author norma
 */
public class Sistema_Hospital {

    private static final String CEDULA = "87654321";
    private static final String DOCUMENTO = "EXP-002";
    private static final String URL = "http://localhost:8081/expedientes/"
            + DOCUMENTO + "/" + CEDULA;

    public static void main(String[] args) {
        System.out.println("=== Sistema Hospital/Clínica ===");
        System.out.println("Solicitando expediente.");
        System.out.println("Documento: " + DOCUMENTO);
        System.out.println("Cédula médico: " + CEDULA);
        System.out.println("URL: " + URL);
        System.out.println("================================");

        try (CloseableHttpClient client = HttpClients.createDefault(); CloseableHttpResponse response = client.execute(new HttpGet(URL))) {

            int status = response.getCode();

            String body = null;
            if (response.getEntity() != null) {
                body = EntityUtils.toString(response.getEntity());
            }

            if (body == null || body.equals("null") || body.isEmpty()) {
                System.out.println("Acceso denegado o expediente no disponible.");
            } else {
                System.out.println("Expediente recibido:");
                System.out.println(body);
            }

        } catch (Exception e) {
            System.err.println("Error al consultar expediente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
