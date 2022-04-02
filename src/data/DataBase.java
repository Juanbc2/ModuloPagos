/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package data;

/**
 *
 * @author JUAN
 */
public interface DataBase {
    public void persistir(Object object);
    public Object leerRegistro();
}
