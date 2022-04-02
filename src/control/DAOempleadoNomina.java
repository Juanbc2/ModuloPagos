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
    private empleadoNomina[] empleadosNomina = (empleadoNomina[]) databaseEmpleadosNomina.leerRegistro();
    private empleadoTH[] empleadosTH = (empleadoTH[]) databaseEmpleadosTH.leerRegistro();

    public void modificarSalario(int cedula, int salario) {

        boolean existeCedula = false;
        String json = "";
        Gson gson = new Gson();
        for (int i = 0; i < empleadosTH.length; i++) {

            if (empleadosTH[i].getCedula() == cedula) {
                empleadosTH[i].setSalario(salario);
                existeCedula = true;
            }
            if (i == 0) {
                json += "[" + gson.toJson(empleadosTH[i]) + ",\n";
            } else if (i < empleadosTH.length - 1) {
                json += gson.toJson(empleadosTH[i]) + ",\n";
            } else {
                json += gson.toJson(empleadosTH[i]) + "]";
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
        cuenta[] cuentasEmpleados = (cuenta[]) databaseCuentas.leerRegistro();
        String json = "";
        Gson gson = new Gson();
        String time = Configurador.time(false);
        Configurador.actualizarFechas(time, Configurador.time(true));
        ArrayList<consignacion> pagos = new ArrayList<>();
        for (cuenta cuentasEmpleado : cuentasEmpleados) {
            if (cuentasEmpleado.cuentaActiva == true) {
                int index = getIndexOfEmpleado(empleadosTH, cuentasEmpleado.cedula);
                if (index != -1) {
                    pagos.add(new consignacion(cuentasEmpleado.numeroCuenta, time, empleadosTH[index].salario));
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

    public empleadoNomina getEmpleado(int cedula) {
        for (empleadoNomina empleadosNomina1 : empleadosNomina) {
            if (empleadosNomina1.getCedula() == cedula) {
                return empleadosNomina1;
            }
        }
        return null;
    }

    public empleado verificarUsuario(int cedula, String pass) {

        for (empleadoNomina empleadosNomina1 : empleadosNomina) {
            if (empleadosNomina1.getCedula() == cedula && empleadosNomina1.password.equals(pass)) {
                return empleadosNomina1;
            }
        }
        return null;

    }
}
