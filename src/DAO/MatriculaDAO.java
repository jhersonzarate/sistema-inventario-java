package DAO;

import Modelo.Matricula;
import Util.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;

public class MatriculaDAO {
    
    public void insertarMatricula(Matricula matricula) {
        String sql = "INSERT INTO Matricula (codAlumno, codCurso) VALUES (?, ?)";
        
        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, matricula.getCodAlumno());
            ps.setInt(2, matricula.getCodCurso());
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Matrícula registrada correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void eliminarMatricula(int numMatricula) {
        String sql = "DELETE FROM Matricula WHERE numMatricula = ?";
        
        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, numMatricula);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Matrícula eliminada correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar matrícula: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean existeMatricula(int codAlumno, int codCurso) {
        String sql = "SELECT COUNT(*) FROM Matricula WHERE codAlumno = ? AND codCurso = ?";
        
        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, codAlumno);
            ps.setInt(2, codCurso);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar matrícula: " + e.getMessage());
        }
        return false;
    }
}