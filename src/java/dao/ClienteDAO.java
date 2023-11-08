package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    ArrayList<Cliente> clientes = new ArrayList<>();

    public ArrayList<Cliente> getLista() throws SQLException {

        sql = "SELECT idCliente, nome, cpf, telefone, status, idade, dataNasc "
                + "FROM cliente";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {

            Cliente c = new Cliente();

            c.setIdCliente(rs.getInt("idCliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setTelefone(rs.getString("telefone"));
            c.setIdade(rs.getInt("idade"));
            c.setDataNasc(rs.getDate("dataNasc"));
            c.setStatus(rs.getInt("status"));

            clientes.add(c);
        }

        ConexaoFactory.close(con);
        return clientes;
    }

    public boolean gravar(Cliente c) throws SQLException {

        con = ConexaoFactory.conectar();

        if (c.getIdCliente()== 0) {
            sql = "INSERT INTO cliente(nome, cpf, telefone, status, idade, dataNasc) "
                    + "VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getTelefone());
            ps.setInt(4, c.getStatus());
            ps.setInt(5, c.getIdade());
            ps.setDate(6, new Date(c.getDataNasc().getTime()));

        } else {
            sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, "
                    + "status = ?, idade = ? ,dataNasc = ? WHERE idCliente = ?";
            ps = con.prepareStatement(sql);
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getCpf());
            ps.setString(3, c.getTelefone());
            ps.setInt(4, c.getStatus());
            ps.setInt(5, c.getIdade());
            ps.setDate(6, new Date(c.getDataNasc().getTime()));
            ps.setInt(7, c.getIdCliente());
        }
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }

    public Cliente getCarregarPorId(int idCliente) throws SQLException {

        Cliente c = new Cliente();

        sql = "SELECT idCliente, nome, cpf, telefone, status, idade, dataNasc "
                + "FROM cliente WHERE idCliente = ?";

        con = ConexaoFactory.conectar();

        ps = con.prepareStatement(sql);
        ps.setInt(1, idCliente);
        rs = ps.executeQuery();

        if (rs.next()) {
            c.setIdCliente(rs.getInt("idCliente"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setTelefone(rs.getString("telefone"));
            c.setIdade(rs.getInt("idade"));
            c.setDataNasc(rs.getDate("dataNasc"));
            c.setStatus(rs.getInt("status"));

        }

        ConexaoFactory.close(con);
        return c;

    }

    public boolean ativar(Cliente c) throws SQLException {

        sql = "UPDATE cliente SET status = 1 WHERE idCliente = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, c.getIdCliente());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;

    }

    public boolean desativar(Cliente c) throws SQLException {

        sql = "UPDATE cliente SET status = 0 WHERE idCliente = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, c.getIdCliente());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;

    }
}
