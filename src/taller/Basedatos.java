/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller;

import java.awt.HeadlessException;
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

    static Connection connect = null;
    static Statement s = null;
    static ResultSet rs = null;

    protected static Connection conexion() {

        try {
            Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:taller");
            System.out.println("Conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("erro de conexion" + e);
        }
        return connect;
    }

    protected static void insertar(String matricula, String marca, String modelo, String kilometros, String nome, String dni, String direccion, String telefono) {
        try {
            s = connect.createStatement();

            //consulta
            String s="INSERT OR IGNORE INTO clientes(matricula,marca,modelo,kilometros,nome,dni,direccion,telefono) VALUES(" +matricula+","+modelo+","+ kilometros + "," + nome + "," + dni + "," + direccion + "," + telefono + "')";
            JOptionPane.showMessageDialog(null, "Insercion Realizada");
            
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Error de insercion: " + ex);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR:\n " + e);
        }
    }

    protected static void borrar() {
        try {
            s = connect.createStatement();
            String con = JOptionPane.showInputDialog("Matricula: ");
            s.executeUpdate("DELETE FROM CLIENTES WHERE MATRICULA= " + con);
            JOptionPane.showMessageDialog(null, "Vehiculo eliminado de la base de datos");
            s.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }

    protected static void modificar() {
        try {
            s = connect.createStatement();
            String con = JOptionPane.showInputDialog("Elije Codigo de alumno que deseas modificar: ");
            String nom = JOptionPane.showInputDialog("Nuevo nombre: ");
            s.executeQuery("UPDATE CLIENTES SET NOMBRE='" + nom + "'WHERE CODA= " + con);
            s.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }
}
