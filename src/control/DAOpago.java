/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.*;


public class DAOpago {
    
    public empleadoTH[] empleados(){
        String json="";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("MOCK_DATA.json"));
            String linea="";
            while((linea = bf.readLine()) != null){
                json += linea;
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
        Gson gson = new Gson();
        empleadoTH[] empleados = gson.fromJson(json, empleadoTH[].class);
        
        return empleados;
    }
    
    public consignacion[] pagos(){
        String json="";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("PAGOS.json"));
            String linea="";
            while((linea = bf.readLine()) != null){
                json += linea;
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
        Gson gson = new Gson();
        consignacion[] empleados = gson.fromJson(json, consignacion[].class);
        
        return empleados;
    }
    
    public void pagarEmpleados(empleadoTH[] empleados, consignacion[] pago, int cedula){
        boolean existeUsuario = false;
        int salario=0;
        String json="";
        Gson gson = new Gson();
        
        for(int i=0; i < empleados.length ; i++){
            if(empleados[i].getCedula() == cedula){
                existeUsuario = true;
                salario = empleados[i].getSalario();
            }
            if(i == 0){
               json += "["+gson.toJson(empleados[i])+",\n";  
            }else if(i < empleados.length - 1 ){
               json += gson.toJson(empleados[i])+",\n";  
            }else{
                json += gson.toJson(empleados[i])+"]"; 
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("PAGOS.json"));
            bw.write(json);
            bw.close();
            if(existeUsuario == true){
                JOptionPane.showMessageDialog(null, "Salario modificado con éxito.");
            }else{
                JOptionPane.showMessageDialog(null, "Cedula no encontrada. Verifique.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR. El salario no pudo ser modificado con éxito.");
        }
        
        
    }
}
