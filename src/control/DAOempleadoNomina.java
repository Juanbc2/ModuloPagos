package control;

import modelo.*;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class DAOempleadoNomina {
    
    public empleadoTH[] leerRegistros(){
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
    
    public void modificarSalario(empleadoTH[] empleados, int cedula, int salario){
        boolean existeCedula = false;
        String json = "";
        Gson gson = new Gson();
        for(int i=0; i< empleados.length; i++){

            if(empleados[i].getCedula() == cedula){
                empleados[i].setSalario(salario);
                existeCedula = true;
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("MOCK_DATA.json"));
            bw.write(json);
            bw.close();
            if(existeCedula == true){
                JOptionPane.showMessageDialog(null, "Salario modificado con éxito.");
            }else{
                JOptionPane.showMessageDialog(null, "Cedula no encontrada. Verifique.");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "ERROR. El salario no pudo ser modificado con éxito.");
        }
    }
}

