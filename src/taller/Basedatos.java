package taller;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**** @author dpazolopez ****/
public class Basedatos {

    static Connection connect = null;
    static Statement s = null;
    static ResultSet rs = null;

    protected static Connection conexion() {

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connect = DriverManager.getConnection("jdbc:derby://localhost:1527/Base prog", "root", "root");
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
            s.executeUpdate("INSERT INTO APP.CLIENTES VALUES ('" + matricula + "','" + marca + "','" + modelo + "'," + kilometros + ",'" + nome + "','" + dni + "','" + direccion + "'," + telefono + ")");
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
            String matricula = JOptionPane.showInputDialog("Matricula: ");
            s.executeUpdate("DELETE FROM APP.CLIENTES WHERE MATRICULA= '" + matricula + "'");
            JOptionPane.showMessageDialog(null, "Vehiculo eliminado de la base de datos");
            s.close();
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }

    }

    protected static void modificar() {
        try {
            s = connect.createStatement();
            String matricula = JOptionPane.showInputDialog("Elije la matricula que deseas modificar: ");
            String kilometros = JOptionPane.showInputDialog("kilometros: ");
            String nombre = JOptionPane.showInputDialog("Nuevo nombre: ");
            String direccion = JOptionPane.showInputDialog("Nueva direccion: ");
            String telefono =JOptionPane.showInputDialog("Nuevo telefono:");
            

            s.executeQuery("UPDATE APP.CLIENTES SET NOMBRE='" + nombre + "'WHERE MATRICULA= '" + matricula+"'");
            s.executeQuery("UPDATE APP.CLIENTES SET DIRECCION='" + direccion + "'WHERE MATRICULA= '" + matricula+"'");
            s.executeQuery("UPDATE APP.CLIENTES SET TELEFONO='" + telefono + "'WHERE MATRICULA= '" + matricula+"'");
            s.executeQuery("UPDATE APP.CLIENTES SET DIRECCION='" + kilometros + "'WHERE MATRICULA= '" + matricula+"'");
            s.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: \n" + e);
        }
    }
}
