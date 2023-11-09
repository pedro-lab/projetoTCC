package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.OrdemServico;

public class OrdemServicoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    
    public ArrayList<OrdemServico> getLista() throws SQLException{

        ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
        sql = "SELECT os.idOs, os.dataSolicitacao, os.dataEntrega, os.vencimento, "+
                "os.statusEntrega, c.idCliente, l.idLente, lab.idLaboratorio, c.idCliente "
                +"FROM ordemservico os INNER JOIN cliente c "+
                " ON os.idCliente = c.idCliente INNER JOIN lente l ON "
                + " os.idLente = l.idLente INNER JOIN laboratorio lab ON "
                + " os.idLaboratorio = lab.idLaboratorio ";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {            
            ClienteDAO cdao = new ClienteDAO();
            LenteDAO ldao = new LenteDAO();
            LaboratorioDAO labdao = new LaboratorioDAO();
            OrdemServico os = new OrdemServico();
            
            os.setIdOs(rs.getInt("os.idOs"));
            os.setDataSolicitacao(rs.getDate("os.dataSolicitacao"));
            os.setDataEntrega(rs.getDate("os.dataEntrega"));
            os.setVencimento(rs.getDate("os.vencimento"));
            os.setStatusEntrega(rs.getString("os.statusEntrega"));
            os.setCliente(cdao.getCarregarPorId(rs.getInt("c.idCliente")));
            os.setLente(ldao.getCarregarPorId(rs.getInt("l.idLente")));
            os.setLaboratorio(labdao.getCarregarPorId(rs.getInt("lab.idLaboratorio")));
            
            ordemServicos.add(os);
        }
        
        ConexaoFactory.close(con);
        return ordemServicos;
    }
    
    public OrdemServico getCarregarPorId(int idOS) throws SQLException{
        sql = "SELECT os.idOs, os.dataSolicitacao, os.dataEntrega, os.vencimento "+
                "os.statusEntrega, c.idCliente, l.idLente, lab.idLaboratorio, c.idCliente "
                +"FROM ordemservico os INNER JOIN cliente c "+
                " ON os.idCliente = c.idCliente INNER JOIN lente l ON "
                + " os.idLente = l.idLente INNER JOIN laboratorio lab ON "
                + " os.idLaboratorio = lab.idLaboratorio WHERE os.idOs = ?";
        
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idOS);
        rs = ps.executeQuery();
        OrdemServico os = new OrdemServico();
        
        if (rs.next()) {            
            
            ClienteDAO cdao = new ClienteDAO();
            LenteDAO ldao = new LenteDAO();
            LaboratorioDAO labdao = new LaboratorioDAO();
            
            os.setIdOs(rs.getInt("os.idOs"));
            os.setDataSolicitacao(rs.getDate("os.dataSolicitacao"));
            os.setDataEntrega(rs.getDate("os.dataEntrega"));
            os.setVencimento(rs.getDate("os.vencimento"));
            os.setStatusEntrega(rs.getString("os.statusEntrega"));
            os.setCliente(cdao.getCarregarPorId(rs.getInt("c.idCliente")));
            os.setLente(ldao.getCarregarPorId(rs.getInt("l.idLente")));
            os.setLaboratorio(labdao.getCarregarPorId(rs.getInt("lab.idLaboratorio")));
        }
        ConexaoFactory.close(con);
        return os;
    }
    
    public boolean gravar(OrdemServico os)throws SQLException{
        
        con = ConexaoFactory.conectar();
        
        if (os.getIdOs()== 0) {
            sql = "INSERT INTO ordemservico (idOs,dataSolicitacao,dataEntrega,vencimento,statusEntrega,"
                    + "idUsuario,idLaboratorio,idLente,idCliente,status) "+
                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setDate(1, new Date(os.getDataSolicitacao().getTime()));
            ps.setDate(2, new Date(os.getDataEntrega().getTime()));
            ps.setDate(3, new Date(os.getVencimento().getTime()));
            ps.setString(4, os.getStatusEntrega());
            ps.setInt(5, os.getUsuario().getIdUsuario());
            ps.setInt(6, os.getLaboratorio().getIdLaboratorio());
            ps.setInt(7, os.getLente().getIdLente());
            ps.setInt(8, os.getCliente().getIdCliente());
            ps.setInt(9, os.getStatus());
            
        } else {
            sql = "UPDATE ordemservico SET dataSolicitacao = ?, dataEntrega = ?, " +
                  "vencimento = ?,statusEntrega = ?, idUsuario = ?, idLaboratorio = ?, "
                  + "idLente = ?, idCliente = ?, status = ? WHERE idOs = ? ";
            
            ps.setDate(1, new Date(os.getDataSolicitacao().getTime()));
            ps.setDate(2, new Date(os.getDataEntrega().getTime()));
            ps.setDate(3, new Date(os.getVencimento().getTime()));
            ps.setString(4, os.getStatusEntrega());
            ps.setInt(5, os.getUsuario().getIdUsuario());
            ps.setInt(6, os.getLaboratorio().getIdLaboratorio());
            ps.setInt(7, os.getLente().getIdLente());
            ps.setInt(8, os.getCliente().getIdCliente());
            ps.setInt(9, os.getStatus());
            ps.setInt(10, os.getIdOs());
            
        }
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public boolean desativar(OrdemServico os) throws SQLException {
        
        sql = "UPDATE ordemservico SET status = 0 WHERE idOs = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, os.getIdOs());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
    
    public boolean ativar(OrdemServico os) throws SQLException {
       
        sql = "UPDATE ordemservico SET status = 1 WHERE idOs = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, os.getIdOs());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }
}
