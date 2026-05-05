/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class BDExpediente {
    private final Map<String, Expediente> baseDeDatos = new HashMap<>();

    public BDExpediente() {
        baseDeDatos.put("EXP-001", new Expediente(1L, "12345678", "Juan Perez", "Gripe común"));
        baseDeDatos.put("EXP-002", new Expediente(2L, "87654321", "Maria Lopez", "Fractura de fémur"));
        baseDeDatos.put("EXP-003", new Expediente(3L, "12345678", "Carlos Ruiz", "Hipertensión arterial"));
    }

    /**
     * Busca un expediente por su ID único.
     * @param id
     * @return 
     */
    public Expediente buscarPorId(String id) {
        return baseDeDatos.get(id);
    }

    /**
     * Retorna todos los expedientes (útil para una lista general).
     * @return 
     */
    public List<Expediente> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    /**
     * Permite agregar nuevos expedientes en tiempo de ejecución.
     * @param expediente
     * @param documento
     */
    public void guardar(Expediente expediente, String documento) {
        baseDeDatos.put(documento, expediente);
    }
    
}
