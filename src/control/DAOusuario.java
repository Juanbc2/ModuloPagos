/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import modelo.*;

public class DAOusuario {
    
    public user[] leerUsuarios(){
        String json="";
        try{
            BufferedReader bf = new BufferedReader(new FileReader("USUARIOS.json"));
            String linea="";
            while((linea = bf.readLine()) != null){
                json += linea;
            }
            bf.close();
        }catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());  
        }
        Gson gson = new Gson();
        user[] usuarios = gson.fromJson(json, user[].class);
        return usuarios;
    }
    
    public boolean verificarUsuario(user[] usuarios , String user, String password){
        
        boolean existeUsuario = false;
        
        String json = "";
        Gson gson = new Gson();
        
        for(int i=0; i<usuarios.length; i++){
            if(usuarios[i].getUser().equals(user)&& usuarios[i].getPassword().equals(password)){
                existeUsuario = true;
            }
            if(i == 0){
               json += "["+gson.toJson(usuarios[i])+",\n";  
            }else if(i < usuarios.length - 1 ){
               json += gson.toJson(usuarios[i])+",\n";  
            }else{
                json += gson.toJson(usuarios[i])+"]"; 
            }
        }
        return existeUsuario;
    }
}
    

