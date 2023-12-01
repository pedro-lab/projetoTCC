package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.AgendaConsulta;
import model.Cliente;

public class AgendaConsultaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";

    public ArrayList<AgendaConsulta> getLista() throws SQLException {

        ArrayList<AgendaConsulta> consultas = new ArrayList<>();
        sql = "SELECT con.idConsulta,con.dia_hora,con.observacoes,con.confirmacao,"
                + "c.nome,c.dataNasc,c.idade,c.telefone,c.idCliente "
                + "FROM cliente c INNER JOIN agendaConsulta con "
                + " ON c.idCliente = con.idCliente";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            AgendaConsulta consulta = new AgendaConsulta();
            ClienteDAO clientedao = new ClienteDAO();

            consulta.setConfirmacao(rs.getString("con.confirmacao"));
            consulta.setDiaHora(rs.getDate("con.dia_hora"));
            consulta.setIdConsulta(rs.getInt("con.idConsulta"));
            consulta.setObservacoes(rs.getString("con.observacoes"));
            consulta.setCliente(clientedao.getCarregarPorId(rs.getInt("c.idCliente")));

            consultas.add(consulta);
        }

        ConexaoFactory.close(con);
        return consultas;
    }

    public ArrayList<AgendaConsulta> getLista(String dataInicial,String dataFinal) throws SQLException {

        ArrayList<AgendaConsulta> consultas = new ArrayList<>();
        sql = "SELECT con.idConsulta,con.dia_hora,con.observacoes,con.confirmacao,"
                + "c.nome,c.dataNasc,c.idade,c.telefone,c.idCliente "
                + "FROM cliente c INNER JOIN agendaConsulta con "
                + " ON c.idCliente = con.idCliente WHERE "
                + "dia_hora BETWEEN ? AND ?";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setString(1, dataInicial);
        ps.setString(2, dataFinal);
        rs = ps.executeQuery();

        while (rs.next()) {
            AgendaConsulta consulta = new AgendaConsulta();
            ClienteDAO clientedao = new ClienteDAO();

            consulta.setConfirmacao(rs.getString("con.confirmacao"));
            consulta.setDiaHora(rs.getDate("con.dia_hora"));
            consulta.setIdConsulta(rs.getInt("con.idConsulta"));
            consulta.setObservacoes(rs.getString("con.observacoes"));
            consulta.setCliente(clientedao.getCarregarPorId(rs.getInt("c.idCliente")));

            consultas.add(consulta);
        }

        ConexaoFactory.close(con);
        return consultas;
    }

    public AgendaConsulta getCarregarPorId(int idConsulta) throws SQLException {
        sql = "SELECT con.idConsulta,con.dia_hora,con.observacoes,con.confirmacao,"
                + "c.nome,c.dataNasc,c.idade,c.telefone,c.idCliente "
                + "FROM cliente c INNER JOIN agendaConsulta con "
                + " ON c.idCliente = con.idCliente WHERE con.idConsulta = ?";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, idConsulta);
        rs = ps.executeQuery();
        AgendaConsulta consulta = new AgendaConsulta();

        if (rs.next()) {

            ClienteDAO clientedao = new ClienteDAO();

            consulta.setConfirmacao(rs.getString("con.confirmacao"));
            consulta.setDiaHora(rs.getDate("con.dia_hora"));
            consulta.setIdConsulta(rs.getInt("con.idConsulta"));
            consulta.setObservacoes(rs.getString("con.observacoes"));
            consulta.setCliente(clientedao.getCarregarPorId(rs.getInt("c.idCliente")));
        }
        ConexaoFactory.close(con);
        return consulta;
    }

    public boolean gravar(AgendaConsulta c) throws SQLException {

        con = ConexaoFactory.conectar();

        if (c.getIdConsulta() == 0) {
            sql = "INSERT INTO agendaconsulta (dia_hora,observacoes,confirmacao,idCliente) "
                    + "VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            
            ps.setTimestamp(1, new Timestamp(c.getDiaHora().getTime()));
            ps.setString(2, c.getObservacoes());
            ps.setString(3, c.getConfirmacao());
            ps.setInt(4, c.getCliente().getIdCliente());

        } else {
            sql = "UPDATE agendaconsulta SET dia_hora = ?, observacoes = ?, confirmacao = ?, "
                    + "idCliente = ? WHERE idConsulta = ? ";

            ps = con.prepareStatement(sql);
            ps.setDate(1, new Date(c.getDiaHora().getTime()));
            ps.setString(2, c.getObservacoes());
            ps.setString(3, c.getConfirmacao());
            ps.setInt(4, c.getCliente().getIdCliente());
            ps.setInt(5, c.getIdConsulta());

        }
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }

    public boolean desativar(AgendaConsulta consulta) throws SQLException {

        sql = "UPDATE agendaConsulta SET status = 0 WHERE idConsulta = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, consulta.getIdConsulta());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }

    public boolean ativar(AgendaConsulta consulta) throws SQLException {

        sql = "UPDATE agendaConsulta SET status = 1 WHERE idConsulta = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, consulta.getIdConsulta());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }

}
