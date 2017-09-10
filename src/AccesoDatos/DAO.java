/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import Entidades.Canal;
import Entidades.Paquete;
import Entidades.Programa;
import Entidades.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Fabian
 */
public class DAO {

    private static Connection con = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/tvbd", "root", "mekarlos87");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return con;
    }

    public static void cerrar() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public ArrayList<Canal> obtenerCanales() throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("select numero,nombre,descripcion,idpaquete from canal  ");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Canal> canales = new ArrayList<Canal>();
        while (rs.next()) {
            int numero = rs.getInt("numero");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            int idpaquete = rs.getInt("idpaquete");
            Canal canal = new Canal(numero, nombre, descripcion, idpaquete);
            canales.add(canal);
        }
        return canales;
    }

    public ArrayList<Programa> obtenerProgramas() throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("select id,nombre,horainicion,horafin,tipo from programa");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Programa> programas = new ArrayList<Programa>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            Date horainicio = rs.getDate("horainicion");
            Date horafin = rs.getDate("horafin");
            String tipo = rs.getString("tipo");
            Programa programa = new Programa(id, nombre, horainicio, horafin, tipo);
            programas.add(programa);
        }
        return programas;
    }
    public ArrayList<Usuario> obtenerUsuarios() throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("select codigo,cedula,nombre,telefono,direccion,idpaquete from usuario");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            int idpaquete = rs.getInt("idpaquete");
            String cedula = rs.getString("cedula");
            String nombre = rs.getString("nombre");
            String telefono = rs.getString("telefono");
            String tipo = rs.getString("direccion");
            Usuario usuario = new Usuario(codigo, cedula, nombre, telefono, tipo, idpaquete);
            usuarios.add(usuario);
        }
        return usuarios;
    }
    public ArrayList<Paquete> obtenerPaquetes() throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("select id,nombre,valor from paquete");
        ResultSet rs = stmt.executeQuery();
        ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            double valor = rs.getDouble("valor");
            Paquete paquete = new Paquete(id, nombre,valor);
            paquetes.add(paquete);
        }
        return paquetes;
    }

    public void insertarCanal(Canal canal) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO canal (numero,nombre,descripcion,idpaquete) VALUES (?,?,?,?)");
        stmt.setInt(1, canal.getNumero());
        stmt.setString(2, canal.getNombre());
        stmt.setString(3, canal.getDescripcion());
        stmt.setInt(4, canal.getIdPaquete());
        stmt.execute();

    }
    public void actualizarCanal(Canal canal) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("update canal set nombre=?,descripcion=?,idpaquete=? where numero=?");
        stmt.setInt(4, canal.getNumero());
        stmt.setString(2-1, canal.getNombre());
        stmt.setString(3-1, canal.getDescripcion());
        stmt.setInt(4-1, canal.getIdPaquete());
        stmt.execute();

    }
    public void eliminarCanal(Canal canal) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("delete from canal where numero=?");
        stmt.setInt(1, canal.getNumero());
        stmt.execute();

    }

    public void insertarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO usuario (codigo,cedula,nombre,telefono,direccion,idpaquete) VALUES (?,?,?,?,?,?)");
        stmt.setInt(1, usuario.getCodigo());
        stmt.setString(2, usuario.getCedula());
        stmt.setString(3, usuario.getNombre());
        stmt.setString(4, usuario.getTelefono());
        stmt.setString(5, usuario.getDireccion());
        stmt.setInt(6, usuario.getIdPaquete());
        stmt.execute();
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("update usuario set cedula=?,nombre=?,telefono=?,direccion=?,idpaquete=? where codigo=?");
        stmt.setInt(6, usuario.getCodigo());
        stmt.setString(2-1, usuario.getCedula());
        stmt.setString(3-1, usuario.getNombre());
        stmt.setString(4-1, usuario.getTelefono());
        stmt.setString(5-1, usuario.getDireccion());
        stmt.setInt(6-1, usuario.getIdPaquete());
        stmt.execute();
    }
    public void eliminarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("delete from usuario where codigo=?");
        stmt.setInt(1, usuario.getCodigo());
        stmt.execute();
    }
    public void insertarPaquete(Paquete paquete) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO paquete (id,nombre,valor) VALUES (?,?,?)");
        stmt.setInt(1, paquete.getId());
        stmt.setString(2, paquete.getNombre());
        stmt.setDouble(3, paquete.getValor());
        stmt.execute();

    }
    public void actualizarPaquete(Paquete paquete) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("update paquete set nombre=?,valor=? where id=?");
        stmt.setInt(3, paquete.getId());
        stmt.setString(2-1, paquete.getNombre());
        stmt.setDouble(3-1, paquete.getValor());
        stmt.execute();

    }
    public void eliminarPaquete(Paquete paquete) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("delete from paquete where id=?");
        stmt.setInt(1, paquete.getId());
        stmt.execute();

    }

    public void insertarPrograma(Programa programa) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("INSERT INTO programa (id,nombre,horainicion,horafin,tipo,idcanal) VALUES (?,?,?,?,?,?)");
        stmt.setInt(1, programa.getId());
        stmt.setString(2, programa.getNombre());
        stmt.setDate(3, new java.sql.Date( programa.getHoraInicio().getTime()));
        stmt.setDate(4, new java.sql.Date(  programa.getHoraFin().getTime()));
        stmt.setString(5, programa.getTipo());
        stmt.setInt(6, programa.getIdcanal());
        stmt.execute();

    }
    
    public void actualizarPrograma(Programa programa) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("update programa set nombre=?,horainicion=?,horafin=?,tipo=?,idcanal=? where id=?");
        stmt.setInt(6, programa.getId());
        stmt.setString(2-1, programa.getNombre());
        stmt.setDate(3-1, new java.sql.Date( programa.getHoraInicio().getTime()));
        stmt.setDate(4-1, new java.sql.Date(  programa.getHoraFin().getTime()));
        stmt.setString(5-1, programa.getTipo());
        stmt.setInt(6-1, programa.getIdcanal());
        stmt.execute();

    }
    public void eliminarPrograma(Programa programa) throws SQLException, ClassNotFoundException {
        obtener();
        PreparedStatement stmt = con.prepareStatement("delete from programa where id=?");
        stmt.setInt(1, programa.getId());      
        stmt.execute();

    }
    
    

    
}
