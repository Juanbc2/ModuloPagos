package control;

import com.google.gson.Gson;
import data.DataBaseEmpleadosNomina;
import data.DataBaseGerencia;
import javax.swing.JOptionPane;
import modelo.empleado;
import modelo.empleadoNomina;
import modelo.gerente;

public class DAOGerente {

    public void cambiarEstadoCuentaEmpleadoNomina(int cedula, boolean activo) {
        DataBaseEmpleadosNomina databaseEmpleadosNomina = new DataBaseEmpleadosNomina();

        empleadoNomina[] empleados = (empleadoNomina[]) databaseEmpleadosNomina.leerRegistro();
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
                databaseEmpleadosNomina.persistir(json);
            } else {
                JOptionPane.showMessageDialog(null, "Cedula no encontrada. Verifique.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos, contacte con servicio técnico.", "Error", 0);
        }
    }

    public empleado verificarUsuario(int cedula, String pass) {
        DataBaseGerencia databaseGerencia = new DataBaseGerencia();
        gerente[] empleados = (gerente[]) databaseGerencia.leerRegistro();
        for (int i = 0; i < empleados.length; i++) {

            if (empleados[i].getCedula() == cedula && empleados[i].password.equals(pass)) {
                return empleados[i];
            }
        }
        return null;

    }
}
