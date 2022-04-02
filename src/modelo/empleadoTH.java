package modelo;


public class empleadoTH extends empleado {
    
    public String rol;
    public int salario;
    public String contraseña;
    
    
    public empleadoTH(String rol, int salario) {
        this.rol = rol;
        this.salario = salario;
    }
    
    public empleadoTH(){
        
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
    
    
   
    
}
