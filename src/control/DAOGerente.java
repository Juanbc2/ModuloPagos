package control;

import com.google.gson.Gson;
import data.DataBaseEmpleadosNomina;
import javax.swing.JOptionPane;
import modelo.empleadoNomina;

public class DAOGerente {

    public void cambiarEstadoCuentaEmpleadoNomina(int cedula, boolean activo) {
        DataBaseEmpleadosNomina database = new DataBaseEmpleadosNomina();
        empleadoNomina[] empleados = (empleadoNomina[]) database.leerRegistro();
        boolean existeCedula = false;
        String json = "";
        Gson gson = new Gson();
        for (int i = 0; i < empleados.length; i++) {

            if (empleados[i].getCedula() == cedula) {
                empleados[i].activo = activo;
                existeCedula = true;
            }
            if (i == 0) {
                json += "[" + gson.toJson(empleados[i]) + ",\n";
            } else if (i < empleados.length - 1) {
                json += gson.toJson(empleados[i]) + ",\n";
            } else {
                json += gson.toJson(empleados[i]) + "]";
            }

        }
        try {
            if (existeCedula == true) {
                JOptionPane.showMessageDialog(null, "Estado de usuario modificado con éxito.");
                database.persistir(json);
            } else {
                JOptionPane.showMessageDialog(null, "Cedula no encontrada. Verifique.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos, contacte con servicio técnico.", "Error", 0);
        }
    }
}
