package modelo;


/**
* @generated
*/
public class cuenta {
    

    private String entidad;  
    private String numero;
    private Boolean cuentActiva;
    private empleadoTH empleadoTH;
    
    

    public String getEntidad() {
        return this.entidad;
    }
    

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    
    public String getNumero() {
        return this.numero;
    }
    
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    public Boolean getCuentActiva() {
        return this.cuentActiva;
    }
    
    
    public void setCuentActiva(Boolean cuentActiva) {
        this.cuentActiva = cuentActiva;
    }
    
    
    public empleadoTH getEmpleadoTH() {
        return this.empleadoTH;
    }
    
    public void setEmpleadoTH(empleadoTH empleadoTH) {
        this.empleadoTH = empleadoTH;
    }

    public int retornaNumero() {
        return 0;
    }

    public boolean retornaActiva() {
        return false;
    }
    
    
}
