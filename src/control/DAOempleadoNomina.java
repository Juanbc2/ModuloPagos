package control;

import modelo.*;
import com.google.gson.Gson;
import data.DataBaseCuentas;
import data.DataBaseEmpleadosNomina;
import data.DataBaseEmpleadosTH;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DAOempleadoNomina {

    private DataBaseCuentas databaseCuentas = new DataBaseCuentas();
    private DataBaseEmpleadosTH databaseEmpleadosTH = new DataBaseEmpleadosTH();
    private DataBaseEmpleadosNomina databaseEmpleadosNomina = new DataBaseEmpleadosNomina();

    public void modificarSalario(int cedula, int salario) {
        empleadoTH[] empleados = (empleadoTH[]) databaseEmpleadosTH.leerRegistro();
        boolean existeCedula = false;
        String json = "";
        Gson gson = new Gson();
        for (int i = 0; i < empleados.length; i++) {

            if (empleados[i].getCedula() == cedula) {
                empleados[i].setSalario(salario);
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
                JOptionPane.showMessageDialog(null, "Salario modificado con éxito.");
                databaseEmpleadosTH.persistir(json);
            } else {
                JOptionPane.showMessageDialog(null, "Cedula no encontrada. Verifique.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo modificar salario, contacte con servicio técnico.", "Error", 0);
        }

    }

    public boolean pagarSalario() {
        empleadoTH[] empleados = (empleadoTH[]) databaseEmpleadosTH.leerRegistro();
        cuenta[] cuentasEmpleados = (cuenta[]) databaseCuentas.leerRegistro();
        String json = "";
        Gson gson = new Gson();
        String time = Configurador.time(false);
        Configurador.actualizarFechas(time, Configurador.time(true));
        ArrayList<consignacion> pagos = new ArrayList<>();
        for (cuenta cuentasEmpleado : cuentasEmpleados) {
            if (cuentasEmpleado.cuentaActiva == true) {
                int index = getIndexOfEmpleado(empleados, cuentasEmpleado.cedula);
                if (index != -1) {
                    pagos.add(new consignacion(cuentasEmpleado.numeroCuenta, time, empleados[index].salario));
                }
            }
        }
        for (int j = 0; j < pagos.size(); j++) {
            if (j == 0) {
                json += "[" + gson.toJson(pagos.get(j)) + ",\n";
            } else if (j < pagos.size() - 1) {
                json += gson.toJson(pagos.get(j)) + ",\n";
            } else {
                json += gson.toJson(pagos.get(j)) + "]";
            }
        }

        try {
            databaseCuentas.registrarPagos(json);
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No se pudo pagar salario, contacte con servicio técnico.", "Error", 0);
            return false;
        }
    }

    private int getIndexOfEmpleado(empleadoTH[] empleados, int cedula) {
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i].getCedula() == cedula) {
                return i;
            }
        }
        return -1;
    }

    public empleado verificarUsuario(int cedula, String pass) {
        empleadoNomina[] empleados = (empleadoNomina[]) databaseEmpleadosNomina.leerRegistro();
        for (int i = 0; i < empleados.length; i++) {

            if (empleados[i].getCedula() == cedula && empleados[i].password.equals(pass)) {
                return empleados[i];
            }
        }
        return null;

    }
}
