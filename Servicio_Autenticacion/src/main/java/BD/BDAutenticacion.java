/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author norma
 */
public class BDAutenticacion {
    private static final Map<String, String> medicosBD = new HashMap<>();

    static {
        medicosBD.put("12345678", "Huella-Simulada-001");
        medicosBD.put("87654321", "Huella-Simulada-002");
    }

    private static final Map<String, String> medicosAutenticados = new HashMap<>();

    public boolean verificarMedico(String cedulaProfesional, String templateHuella,
            String medicoId, String pacienteId) {

        if (!medicosBD.containsKey(cedulaProfesional)) {
            System.out.println("Cédula no registrada: " + cedulaProfesional);
            return false;
        }

        String huellaRegistrada = medicosBD.get(cedulaProfesional);
        if (!huellaRegistrada.equals(templateHuella)) {
            System.out.println("Huella no coincide para cédula: " + cedulaProfesional);
            return false;
        }

        medicosAutenticados.put(medicoId, pacienteId);
        System.out.println("Médico autenticado exitosamente: " + medicoId);
        return true;
    }

    public boolean estaAutenticado(String medicoId) {
        return medicosAutenticados.containsKey(medicoId);
    }

    public String getPacienteDeMediaco(String medicoId) {
        return medicosAutenticados.get(medicoId);
    }
}
