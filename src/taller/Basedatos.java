/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author dpazolopez
 */
public class Basedatos {
    static Connection conex = null;
    static Statement s = null;
    static ResultSet rs = null;

    public static Connection conexion() {

        try {
            Class.forName("mysql-connector-java-5.1.35-bin.jar.ClientDriver");
            conex = DriverManager.getConnection("jdbc:MYSQL://localhost/taller", "root", "root");
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("erro de conexion" + e);
        }
        return conex;
    }

    public static void insertar(String cod, String nomb, String curso) {
        try {
            s = conex.createStatement();

            //consulta
            s.executeUpdate("INSERT INTO APP.ALUMNOS VALUES (" + cod + ",'" + nomb + "','" + curso + "')");
            JOptionPane.showMessageDialog(null, "Insercion Realizada");
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Error de insercion: " + ex);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR:\n " + e);
        }
    }

    public static void borrar() {
        try {
            s = conex.createStatement();
            String con = JOptionPane.showInputDialog("Codigo Alumno: ");
            s.executeUpdate("DELETE FROM APP.ALUMNOS WHERE CODA= " + con);
            JOptionPane.showMessageDialog(null, "Alumno eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);

        }
    }
    public static void modificar(){
        try{
            s = conex.createStatement();
            String con=JOptionPane.showInputDialog("Elije Codigo de alumno que deseas modificar: ");
            String nom= JOptionPane.showInputDialog("Nuevo nombre: ");
            s.executeQuery("UPDATE APP.ALUMNOS SET NOMBRE='"+nom+"'WHERE CODA= "+con);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error: "+e);
        }
    }
}
