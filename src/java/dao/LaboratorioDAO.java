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

    public Lente getCarregarPorId(int idLente) throws SQLException {

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
