/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import com.google.gson.Gson;
import modelo.RegistroPagos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author JUAN
 */
public class DataBaseRegistroPagos implements DataBase{

    @Override
    public void persistir(Object object) {
            try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/data/RegistroPagos.json"));
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
            BufferedReader bf = new BufferedReader(new FileReader("src/data/RegistroPagos.json"));
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
        RegistroPagos[] configurador = gson.fromJson(json, RegistroPagos[].class);
        return configurador;
       }
    
}
