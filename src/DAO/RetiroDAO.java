package DAO;

import Modelo.Retiro;
import Util.Conexion;
import java.sql.*;
import javax.swing.JOptionPane;

public class RetiroDAO {
    
    public void insertarRetiro(Retiro retiro) {
        String sql = "INSERT INTO Retiro (numMatricula) VALUES (?)";
        
        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, retiro.getNumMatricula());
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Retiro registrado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al insertar retiro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public boolean existeRetiro(int numMatricula) {
        String sql = "SELECT COUNT(*) FROM Retiro WHERE numMatricula = ?";
        
        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, numMatricula);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar retiro: " + e.getMessage());
        }
        return false;
    }
    
    public void cancelarRetiro(int numRetiro) {
        String sql = "DELETE FROM Retiro WHERE numRetiro = ?";
        
        try (Connection con = new Conexion().getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, numRetiro);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Retiro cancelado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cancelar retiro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}