/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BD;

import enums.TipoPermiso;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
public class BDPermisos {

    private static final Map<String, TipoPermiso> permisosBD = new HashMap<>();
    //Este mapa hace referencia a los documentos a los que tiene acceso el medico
    private static final Map<String, String> documentos = new HashMap<>();

    static {
        permisosBD.put("12345678", TipoPermiso.EDITAR);
        permisosBD.put("87654321", TipoPermiso.LEER);
        permisosBD.put("13572468", TipoPermiso.NINGUNO);
    }

    static {
        documentos.put("12345678", "EXP-001");  
        documentos.put("87654321", "EXP-002"); 
        documentos.put("13572468", "EXP-003"); 
    }

    public boolean verificarPermisosMedico(String cedulaProfesional) {

        if (!permisosBD.containsKey(cedulaProfesional)) {
            System.out.println("No se encontro registro de esa cedula");
            return false;
        }

        TipoPermiso permiso = permisosBD.get(cedulaProfesional);
        if (permiso.equals(TipoPermiso.NINGUNO)) {
            System.out.println("El medico no tiene permiso a entrar a este documento.");
            return false;
        }

        System.out.println("El medico tiene permiso a " + permiso.toString().toLowerCase() + " este expediente");
        return true;
    }

    public TipoPermiso getPermisoMedico(String cedulaProfesional) {
        return permisosBD.get(cedulaProfesional);
    }

    public String getDocumentoAcceso(String cedulaProfesional) {
        return documentos.get(cedulaProfesional);
    }
}
