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

    public ArrayList<OrdemServico> getLista() throws SQLException {

        ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
        sql = "SELECT os.idOs,os.dataOS, os.dataVenda, os.dataEntrega, os.dataVencimento, os.status, "
                + "os.statusEntrega, c.idCliente, l.idLente, lab.idLaboratorio, c.idCliente "
                + "FROM ordemservico os INNER JOIN cliente c "
                + " ON os.idCliente = c.idCliente INNER JOIN lente l ON "
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
            os.setDataOS(rs.getDate("os.dataOS"));
            os.setDataVenda(rs.getDate("os.dataVenda"));
            os.setDataEntrega(rs.getDate("os.dataEntrega"));
            os.setDataVencimento(rs.getDate("os.vencimento"));
            os.setStatus(rs.getInt("os.status"));
            os.setStatusEntrega(rs.getString("os.statusEntrega"));
            os.setCliente(cdao.getCarregarPorId(rs.getInt("c.idCliente")));
            os.setLente(ldao.getCarregarPorId(rs.getInt("l.idLente")));
            os.setLaboratorio(labdao.getCarregarPorId(rs.getInt("lab.idLaboratorio")));
            os.setStatusVencimento(os.verificaVencimento(os.getDataVencimento()));
            os.setDataOS(rs.getDate("os.dataOS"));

            ordemServicos.add(os);
        }

        ConexaoFactory.close(con);
        return ordemServicos;
    }

    public ArrayList<OrdemServico> getLista(int mes, int ano) throws SQLException {

        ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
        sql = "SELECT os.idOs,os.dataOS, os.dataVenda, os.dataEntrega, os.dataVencimento, os.status, "
                + "os.statusEntrega, c.idCliente, l.idLente, lab.idLaboratorio, c.idCliente "
                + "FROM ordemservico os INNER JOIN cliente c "
                + " ON os.idCliente = c.idCliente INNER JOIN lente l ON "
                + " os.idLente = l.idLente INNER JOIN laboratorio lab ON "
                + " os.idLaboratorio = lab.idLaboratorio "
                + "WHERE extract(month from dataOS) = ?"
                + " and extract(year from dataOS) = ?";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, mes);
        ps.setInt(2, ano);
        rs = ps.executeQuery();

        while (rs.next()) {
            ClienteDAO cdao = new ClienteDAO();
            LenteDAO ldao = new LenteDAO();
            LaboratorioDAO labdao = new LaboratorioDAO();
            OrdemServico os = new OrdemServico();

            os.setIdOs(rs.getInt("os.idOs"));
            os.setDataOS(rs.getDate("os.dataOS"));
            os.setDataVenda(rs.getDate("os.dataVenda"));
            os.setDataEntrega(rs.getDate("os.dataEntrega"));
            os.setDataVencimento(rs.getDate("os.dataVencimento"));
            os.setStatus(rs.getInt("os.status"));
            os.setStatusEntrega(rs.getString("os.statusEntrega"));
            os.setCliente(cdao.getCarregarPorId(rs.getInt("c.idCliente")));
            os.setLente(ldao.getCarregarPorId(rs.getInt("l.idLente")));
            os.setLaboratorio(labdao.getCarregarPorId(rs.getInt("lab.idLaboratorio")));
            os.setStatusVencimento(os.verificaVencimento(os.getDataVencimento()));
            os.setDataOS(rs.getDate("os.dataOS"));

            ordemServicos.add(os);
        }

        ConexaoFactory.close(con);
        return ordemServicos;
    }

    public OrdemServico getCarregarPorId(int idOS) throws SQLException {
        sql = "SELECT os.idOs,os.dataOS, os.dataVenda, os.dataEntrega, os.dataVencimento, "
                + "os.statusEntrega, c.idCliente, l.idLente, lab.idLaboratorio, c.idCliente "
                + "FROM ordemservico os INNER JOIN cliente c "
                + " ON os.idCliente = c.idCliente INNER JOIN lente l ON "
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
            os.setDataOS(rs.getDate("os.dataOS"));
            os.setDataVenda(rs.getDate("os.dataVenda"));
            os.setDataEntrega(rs.getDate("os.dataEntrega"));
            os.setDataVencimento(rs.getDate("os.dataVencimento"));
            os.setStatus(rs.getInt("os.status"));
            os.setStatusEntrega(rs.getString("os.statusEntrega"));
            os.setCliente(cdao.getCarregarPorId(rs.getInt("c.idCliente")));
            os.setLente(ldao.getCarregarPorId(rs.getInt("l.idLente")));
            os.setLaboratorio(labdao.getCarregarPorId(rs.getInt("lab.idLaboratorio")));
            os.setStatusVencimento(os.verificaVencimento(os.getDataVencimento()));
            os.setDataOS(rs.getDate("os.dataOS"));
        }
        ConexaoFactory.close(con);
        return os;
    }

    public boolean gravar(OrdemServico os) throws SQLException {

        con = ConexaoFactory.conectar();

        if (os.getIdOs() == 0) {
            sql = "INSERT INTO ordemservico (dataVenda,dataEntrega,dataVencimento,statusEntrega,"
                    + "idUsuario,idLaboratorio,idLente,idCliente,status,dataOS)"
                    + "VALUES (? , ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            ps.setDate(1, new Date(os.getDataVenda().getTime()));
            if (os.getDataEntrega() == null) {
                ps.setString(2, null);
            } else {
                ps.setDate(2, new Date(os.getDataEntrega().getTime()));
            }
            if (os.getDataVencimento() == null) {
                ps.setString(3, null);
            } else {
                ps.setDate(3, new Date(os.getDataVencimento().getTime()));
            }
            ps.setString(4, os.getStatusEntrega());
            ps.setInt(5, os.getUsuario().getIdUsuario());
            ps.setInt(6, os.getLaboratorio().getIdLaboratorio());
            ps.setInt(7, os.getLente().getIdLente());
            ps.setInt(8, os.getCliente().getIdCliente());
            ps.setInt(9, os.getStatus());
            ps.setDate(10, new Date(os.getDataOS().getTime()));

        } else {
            sql = "UPDATE ordemservico SET dataVenda = ?, dataEntrega = ?, "
                    + "vencimento = ?,statusEntrega = ?, idUsuario = ?, idLaboratorio = ?, "
                    + "idLente = ?, idCliente = ?, status = ?,dataOS = ? WHERE idOs = ? ";
            ps = con.prepareStatement(sql);

            ps.setDate(1, new Date(os.getDataVenda().getTime()));
            if (os.getDataEntrega() == null) {
                ps.setString(2, null);
            } else {
                ps.setDate(2, new Date(os.getDataEntrega().getTime()));
            }
            if (os.getDataVencimento() == null) {
                ps.setString(3, null);
            } else {
                ps.setDate(3, new Date(os.getDataVencimento().getTime()));
            }
            ps.setString(4, os.getStatusEntrega());
            ps.setInt(5, os.getUsuario().getIdUsuario());
            ps.setInt(6, os.getLaboratorio().getIdLaboratorio());
            ps.setInt(7, os.getLente().getIdLente());
            ps.setInt(8, os.getCliente().getIdCliente());
            ps.setInt(9, os.getStatus());
            ps.setDate(10, new Date(os.getDataOS().getTime()));
            ps.setInt(11, os.getIdOs());

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

    public boolean atualizaEntrega(int idOS, String status) throws SQLException {

        sql = "UPDATE ordemservico SET statusEntrega = ?, dataEntrega = ? WHERE idOs = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, status);
        ps.setDate(2, new Date(OrdemServico.dataAtual.getTime()));
        ps.setInt(3, idOS);
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }

    public Integer quantidadeOS(int ano, int mes) throws SQLException {

        Integer quantidadeOS = null;

        sql = "select count(*) from ordemservico where extract(month from dataOS) = ? and extract(year from dataOS) = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, mes);
        ps.setInt(2, ano);
        rs = ps.executeQuery();

        if (rs.next()) {

            quantidadeOS = (Integer.parseInt(rs.getString("count(*)")));
;
        }
        ConexaoFactory.close(con);
        return quantidadeOS;
    }

}
