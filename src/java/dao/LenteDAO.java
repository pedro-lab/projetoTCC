package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Lente;

public class LenteDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    ArrayList<Lente> lentes = new ArrayList<>();
    
    public ArrayList<Lente> getLista() throws SQLException {
        
        sql = "SELECT idLente, nome, modelo, preco,status,fabricante "
                + "FROM Lente";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {            
            
            Lente l = new Lente();
            
            l.setIdLente(rs.getInt("idLente"));
            l.setModelo(rs.getString("modelo"));
            l.setNome(rs.getString("nome"));
            l.setPreco(rs.getDouble("preco"));
            l.setStatus(rs.getInt("status"));
            l.setFabricante(rs.getString("fabricante"));
            
            lentes.add(l);
        }
        
        ConexaoFactory.close(con);
        return lentes;
    }
    
    public boolean gravar(Lente l) throws SQLException {
        
        con = ConexaoFactory.conectar();
        
        if (l.getIdLente()== 0) {
            sql = "INSERT INTO lente(nome, modelo, fabricante, preco, status) "+
                    "VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, l.getNome());
            ps.setString(2, l.getModelo());
            ps.setString(3, l.getFabricante());
            ps.setDouble(4, l.getPreco());
            ps.setInt(5, l.getStatus());
            
        } else {
            sql = "UPDATE lente SET nome = ?, modelo = ?,fabricante = ?, "
                    + "preco = ?, status = ? WHERE idLente = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, l.getNome());
            ps.setString(2, l.getModelo());
            ps.setString(3, l.getFabricante());
            ps.setDouble(4, l.getPreco());
            ps.setInt(6, l.getStatus());
            ps.setInt(5, l.getIdLente());
        }
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public Lente getCarregarPorId(int idLente) throws SQLException{
        
        Lente lente = new Lente();
        
        sql = "SELECT idLente, nome, modelo, preco,status,fabricante "
                + "FROM Lente WHERE idLente = ? ";
        
        con = ConexaoFactory.conectar();
        
        ps = con.prepareStatement(sql);
        ps.setInt(1, idLente);
        rs = ps.executeQuery();
        
        if (rs.next()) {
            lente.setIdLente(rs.getInt("idLente"));
            lente.setModelo(rs.getString("modelo"));
            lente.setNome(rs.getString("nome"));
            lente.setPreco(rs.getDouble("preco"));
            lente.setStatus(rs.getInt("status"));
            lente.setFabricante(rs.getString("fabricante"));
        
        }
        
        ConexaoFactory.close(con);
        return lente;
        
    }
    
    public boolean ativar(Lente l) throws SQLException {
        
        sql = "UPDATE lente SET status = 1 WHERE idLente = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, l.getIdLente());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
        
    }

    public boolean desativar(Lente l) throws SQLException {
        
        sql = "UPDATE lente SET status = 0 WHERE idLente = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, l.getIdLente());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
        
    }

}
