package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;

@WebServlet(name = "GerenciarCliente", urlPatterns = {"/gerenciarCliente"})
public class GerenciarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idCliente = request.getParameter("idCliente");
        String mensagem = "";

        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();

        try {
            if (acao.equals("listar")) {
                ArrayList<Cliente> clientes = new ArrayList<>();
                clientes = cdao.getLista();
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher("/listarClientes.jsp");

                request.setAttribute("clientes", clientes);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {

                c = cdao.getCarregarPorId(Integer.parseInt(idCliente));
                if (c.getIdCliente()> 0) {
                    RequestDispatcher dispatcher
                            = getServletContext().getRequestDispatcher("/cadastrarCliente.jsp");
                    request.setAttribute("cliente", c);
                    dispatcher.forward(request, response);
                } else {
                    mensagem = "Cliente não encontrado na base de dados!";
                }

            } else if (acao.equals("ativar")) {
                c.setIdCliente(Integer.parseInt(idCliente));
                if (cdao.ativar(c)) {
                    mensagem = "Cliente ativado com sucesso!";
                } else {
                    mensagem = "Falha ao ativar o Cliente!";
                }
            } else if (acao.equals("desativar")) {
                c.setIdCliente(Integer.parseInt(idCliente));
                if (cdao.desativar(c)) {
                    mensagem = "Cliente desativado com sucesso!";
                } else {
                    mensagem = "Falha ao desativar o Cliente!";
                }
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
