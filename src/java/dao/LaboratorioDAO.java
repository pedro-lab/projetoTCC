package dao;

import factory.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Laboratorio;
import model.Lente;

public class LaboratorioDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql = "";
    ArrayList<Laboratorio> laboratorios = new ArrayList<>();

    public ArrayList<Laboratorio> getLista() throws SQLException {

        sql = "SELECT idLaboratorio, nome, endereco, telefone, email, status "
                + "FROM laboratorio";

        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {

            Laboratorio lab = new Laboratorio();

            lab.setIdLaboratorio(rs.getInt("idLaboratorio"));
            lab.setNome(rs.getString("nome"));
            lab.setEndereco(rs.getString("endereco"));
            lab.setTelefone(rs.getString("telefone"));
            lab.setEmail(rs.getString("email"));
            lab.setStatus(rs.getInt("status"));

            laboratorios.add(lab);
        }

        ConexaoFactory.close(con);
        return laboratorios;
    }

    public boolean gravar(Laboratorio lab) throws SQLException {

        con = ConexaoFactory.conectar();

        if (lab.getIdLaboratorio()== 0) {
            sql = "INSERT INTO laboratorio(nome, endereco, telefone, email, status) "
                    + "VALUES(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, lab.getNome());
            ps.setString(2, lab.getEndereco());
            ps.setString(3, lab.getTelefone());
            ps.setString(4, lab.getEmail());
            ps.setInt(5, lab.getStatus());

        } else {
            sql = "UPDATE laboratorio SET nome = ?, endereco = ?, telefone = ?, "
                    + "email = ?, status = ? WHERE idLaboratorio = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, lab.getNome());
            ps.setString(2, lab.getEndereco());
            ps.setString(3, lab.getTelefone());
            ps.setString(4, lab.getEmail());
            ps.setInt(5, lab.getStatus());
            ps.setInt(6, lab.getIdLaboratorio());
        }
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;
    }

    public Laboratorio getCarregarPorId(int idLaboratorio) throws SQLException {

        Laboratorio lab = new Laboratorio();

        sql = "SELECT idLaboratorio, nome, endereco, telefone, email, status "
                + "FROM laboratorio WHERE idLaboratorio = ?";

        con = ConexaoFactory.conectar();

        ps = con.prepareStatement(sql);
        ps.setInt(1, idLaboratorio);
        rs = ps.executeQuery();

        if (rs.next()) {
            lab.setIdLaboratorio(rs.getInt("idLaboratorio"));
            lab.setNome(rs.getString("nome"));
            lab.setEndereco(rs.getString("endereco"));
            lab.setTelefone(rs.getString("telefone"));
            lab.setEmail(rs.getString("email"));
            lab.setStatus(rs.getInt("status"));

        }

        ConexaoFactory.close(con);
        return lab;

    }

    public boolean ativar(Laboratorio lab) throws SQLException {

        sql = "UPDATE laboratorio SET status = 1 WHERE idLaboratorio = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, lab.getIdLaboratorio());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;

    }

    public boolean desativar(Laboratorio lab) throws SQLException {

        sql = "UPDATE laboratorio SET status = 0 WHERE idLaboratorio = ?";
        con = ConexaoFactory.conectar();
        ps = con.prepareStatement(sql);
        ps.setInt(1, lab.getIdLaboratorio());
        ps.executeUpdate();
        ConexaoFactory.close(con);
        return true;

    }

}
