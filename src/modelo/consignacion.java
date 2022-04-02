
package modelo;

import java.util.Date;


public class consignacion {
    

    private int numeroCuenta;
    private String fecha;
    private int valor;
    
    
    public consignacion(int numeroCuenta,String fecha,int valor){
        this.numeroCuenta = numeroCuenta;
        this.fecha = fecha;
        this.valor = valor; 
    }
}
