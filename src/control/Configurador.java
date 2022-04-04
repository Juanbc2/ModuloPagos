package control;

import modelo.RegistroPagos;
import data.DataBaseRegistroPagos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modelo.empleadoNomina;
import modelo.empleadoTH;
import modelo.empleado;

/**
 *
 * @author JUAN
 */
public final class Configurador {

    private static DataBaseRegistroPagos database = new DataBaseRegistroPagos();
    private static empleado empleadoActual;
    private static volatile Configurador miconfigurador;
    private static boolean gerente;
    public static String nombreEmpleadoActual;
    public static String fechaUltimoPago;
    public static String fechaSiguientePago;

    public static Configurador getConfigurador(empleado empleadoActual) {
        RegistroPagos[] ultimosRegistros = (RegistroPagos[]) database.leerRegistro();
        Configurador result = miconfigurador;
        if (result != null) {
            return miconfigurador;
        }
        synchronized (Configurador.class) {
            if (miconfigurador == null) {
                miconfigurador = new Configurador(empleadoActual, ultimosRegistros[ultimosRegistros.length-1].fechaUltimoPago, ultimosRegistros[ultimosRegistros.length-1].fechaSiguientePago);
            }
            return miconfigurador;
        }
    }

    private Configurador(empleado empleadoActual, String fechaUltimoPago, String fechaSiguientePago) {
        Configurador.fechaUltimoPago = fechaUltimoPago;
        Configurador.fechaSiguientePago = fechaSiguientePago;
        if (empleadoActual.getClass().equals(empleadoTH.class)) {
            Configurador.gerente = false;
        } else if (empleadoActual.getClass().equals(empleadoNomina.class)) {
            Configurador.gerente = true;
        }
    }

    public static void actualizarFechas(String fechaUltimoPago, String fechaSiguientePago) {
        Configurador.fechaUltimoPago = fechaUltimoPago;
        Configurador.fechaSiguientePago = fechaSiguientePago;
        String json = "[{\"fechaUltimoPago\":\"" + fechaUltimoPago + "\",\"fechaSiguientePago\":\"" + fechaSiguientePago + "\"}]";
        database.persistir(json);
    }

    public static boolean pagosDisponibles() {
        Calendar fechaActual = Calendar.getInstance();
        Calendar siguientePago = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
        try {
            //fechaActual.setTime(sdf.parse(fechaUltimoPago));
            siguientePago.setTime(sdf.parse(fechaSiguientePago));
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
        int n = fechaActual.compareTo(siguientePago);              
        return n >= 1;
    }

    public static String time(boolean nextPayDate) {
        Calendar cal = Calendar.getInstance();
        if (nextPayDate) {
            cal.add(Calendar.DATE, +15);
        }
        SimpleDateFormat sdf24 = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
        Date dat = cal.getTime();
        String time24 = sdf24.format(dat);
        return time24;
    }
}
