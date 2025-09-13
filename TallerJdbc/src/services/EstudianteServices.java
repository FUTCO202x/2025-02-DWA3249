package services;
import database.DatabaseDB;
import models.Estudiante;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstudianteServices {
    private DatabaseDB db = new DatabaseDB();

    public void insertar(Estudiante est) {
        String sql = "INSERT INTO estudiantes(nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = db.conexionDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, est.getNombre());
            ps.setString(2, est.getApellido());
            ps.setString(3, est.getCorreo());
            ps.setInt(4, est.getEdad());
            ps.setString(5, est.getEstadoCivil());
            ps.executeUpdate();
            System.out.println("Estudiante insertado");

        } catch (SQLException e) {
            System.out.println("Error al insertar estudiante");
            e.printStackTrace();
        }
    }

    public void actualizar(String correo, String nombre, String apellido, int edad, String estadoCivil) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection conn = db.conexionDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setInt(3, edad);
            ps.setString(4, estadoCivil);
            ps.setString(5, correo);

            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Estudiante actualizado");
            else
                System.out.println("No se encontró estudiante con ese correo");

        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante");
            e.printStackTrace();
        }
    }

    public void eliminar(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try (Connection conn = db.conexionDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("✅ Estudiante eliminado");
            else
                System.out.println("⚠️ No se encontró estudiante con ese correo");

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar estudiante");
            e.printStackTrace();
        }
    }

    public List<Estudiante> consultarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = db.conexionDb();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        rs.getString("estado_civil")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar estudiantes");
            e.printStackTrace();
        }
        return lista;
    }

    public Estudiante consultarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try (Connection conn = db.conexionDb();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        rs.getString("estado_civil")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar estudiante por correo");
            e.printStackTrace();
        }
        return null;
    }
}
