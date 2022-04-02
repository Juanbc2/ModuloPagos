/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import modelo.empleadoTH;

/**
 *
 * @author JUAN
 */
public class DataBaseEmpleadosTH implements DataBase{

    @Override
    public void persistir(Object object) {
            try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/EmpleadosTH.json"));
            bw.write((String)object);
            bw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la base de datos, comuníquese con soporte técnico.","Error",0);
        }
   }

    @Override
    public Object leerRegistro() {
        String json = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/data/EmpleadosTH.json"));
            String linea = "";
            while ((linea = bf.readLine()) != null) {
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
    
    
}
