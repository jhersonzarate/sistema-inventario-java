package DAO;

import Modelo.Alumno;
import Modelo.Curso;
import Util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class CursoDAO {
    
    public void insertarCurso(Curso curso) {
        
        String sql = "INSERT INTO Curso (codCurso, Asignatura, Ciclo, Creditos, Horas) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, curso.getCodCurso());
            ps.setString(2, curso.getAsignatura());
            ps.setInt(3, curso.getCiclo());
            ps.setInt(4, curso.getCreditos());
            ps.setInt(5, curso.getHoras());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Curso insertado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar curso: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void actualizarCurso(Curso curso) {
        String sql = "UPDATE Curso SET Asignatura = ?, Ciclo = ?, Creditos = ?, Horas = ? WHERE codCurso = ?";

        try (Connection con = new Conexion().getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, curso.getAsignatura());
            ps.setInt(2, curso.getCiclo());
            ps.setInt(3, curso.getCreditos());
            ps.setInt(4, curso.getHoras());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Curso actualizado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar curso: " + e.getMessage());
        }
    }
}
