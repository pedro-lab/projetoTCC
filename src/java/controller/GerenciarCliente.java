package controller;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
                if (c.getIdCliente() > 0) {
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

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idCliente = request.getParameter("idCliente");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String dataNasc = request.getParameter("dataNasc");
        String dataAtual = request.getParameter("dataAtual");
        String status = request.getParameter("status");
        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();
        String mensagem = "";
        HttpSession sessao = request.getSession();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (!idCliente.isEmpty()) {
            c.setIdCliente(Integer.parseInt(idCliente));
        }

        if (nome.isEmpty() || nome.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Cliente!");
            exibirMensagem(request, response);
        } else {
            c.setNome(nome);
        }

        if (cpf.isEmpty() || cpf.equals("")) {
            sessao.setAttribute("msg", "Informe o cpf do Cliente!");
            exibirMensagem(request, response);
        } else {
            c.setCpf(cpf);
        }

        if (telefone.isEmpty() || telefone.equals("")) {
            sessao.setAttribute("msg", "Informe o telefone do Cliente!");
            exibirMensagem(request, response);
        } else {
            c.setTelefone(telefone);
        }

        if (status.isEmpty() || status.equals("")) {
            sessao.setAttribute("msg", "Informe o status do Cliente!");
            exibirMensagem(request, response);
        } else {
            c.setStatus(Integer.parseInt(status));
        }

        //Valores que podem ser nulos
        try {
            c.setDataNasc(df.parse(dataNasc));
            c.setIdade(calculaIdade(dataNasc, dataAtual));
        } catch (ParseException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        try {
            if (cdao.gravar(c)) {
                mensagem = "Cliente salvo na base de dados!";
            } else {
                mensagem = "Falha ao salvar o cliente na base de dados!";
            }
        } catch (SQLException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarCliente?acao=listar';"
                + "</script>"
        );
    }

    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/cadastrarCliente.jsp");
        dispatcher.forward(request, response);
    }

    private int calculaIdade(String dataNasc, String dataAtual) {

        String[] dataSeparadaNasc = dataNasc.split("-");
        String[] dataSeparadaAtual = dataAtual.split("-");

        int anoNasc = Integer.parseInt(dataSeparadaNasc[0]);
        int mesNasc = Integer.parseInt(dataSeparadaNasc[1]);
        int diaNasc = Integer.parseInt(dataSeparadaNasc[2]);

        int anoAtual = Integer.parseInt(dataSeparadaAtual[0]);
        int mesAtual = Integer.parseInt(dataSeparadaAtual[1]);
        int diaAtual = Integer.parseInt(dataSeparadaAtual[2]);

        System.out.println(anoAtual + "-" + mesAtual + "-" + diaAtual);
        System.out.println(anoNasc + "-" + mesNasc + "-" + diaNasc);

        if (mesNasc < mesAtual) {
            return anoAtual - anoNasc;
        } else if (mesNasc == mesAtual) {
            if (diaNasc <= diaAtual) {
                return anoAtual - anoNasc;
            } else {
                return anoAtual - anoNasc - 1;
            }
        } else {
            return anoAtual - anoNasc - 1;
        }
    }

}
