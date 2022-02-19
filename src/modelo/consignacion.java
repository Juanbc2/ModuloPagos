
package modelo;

import java.util.Date;


public class consignacion {
    

    private String numeroCuenta;
    private Date fecha;
    private Integer valor;
    private empleadoNomina empleadoNomina;
    
    
    public String getNumeroCuenta() {
        return this.numeroCuenta;
    }
    
    /**
    * @generated
    */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
    
    /**
    * @generated
    */
    public Date getFecha() {
        return this.fecha;
    }
    
    /**
    * @generated
    */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    /**
    * @generated
    */
    public Integer getValor() {
        return this.valor;
    }
    
    /**
    * @generated
    */
    public void setValor(Integer valor) {
        this.valor = valor;
    }
    
    
    /**
    * @generated
    */
    public empleadoNomina getEmpleadoNomina() {
        return this.empleadoNomina;
    }
    
    /**
    * @generated
    */
    public void setEmpleadoNomina(empleadoNomina empleadoNomina) {
        this.empleadoNomina = empleadoNomina;
    }
    

    //                          Operations                                  
    
    /**
    * @generated
    */
    public void registrarConsignacion() {
        
    }
}
