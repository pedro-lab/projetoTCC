package controller;

import dao.AgendaConsultaDAO;
import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AgendaConsulta;
import model.Cliente;

@WebServlet(name = "GerenciarAgendaConsulta", urlPatterns = {"/gerenciarAgendaConsulta"})
public class GerenciarAgendaConsulta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idConsulta = request.getParameter("idCliente");
        String dataInicial = request.getParameter("dataInicial");
        String dataFinal = request.getParameter("dataFinal");
        String mensagem = "";

        AgendaConsulta consulta = new AgendaConsulta();
        AgendaConsultaDAO consultadao = new AgendaConsultaDAO();

        try {
            if (acao.equals("listar")) {
                ArrayList<AgendaConsulta> consultas = new ArrayList<>();
                consultas = consultadao.getLista(dataInicial, dataFinal);
                System.out.println("Entrou");
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher("/listarConsulta.jsp");

                request.setAttribute("consultas", consultas);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {

                consulta = consultadao.getCarregarPorId(Integer.parseInt(idConsulta));
                if (consulta.getIdConsulta() > 0) {
                    RequestDispatcher dispatcher
                            = getServletContext().getRequestDispatcher("/cadastrarConsulta.jsp");
                    request.setAttribute("consulta", consulta);
                    dispatcher.forward(request, response);
                } else {
                    mensagem = "O agendamento não foi encontrado na base de dados!";
                }

            } else if (acao.equals("ativar")) {
                consulta.setIdConsulta(Integer.parseInt(idConsulta));
                if (consultadao.ativar(consulta)) {
                    mensagem = "Consulta ativado com sucesso!";
                } else {
                    mensagem = "Falha ao ativar a consulta!";
                }
            } else if (acao.equals("desativar")) {
                consulta.setIdConsulta(Integer.parseInt(idConsulta));
                if (consultadao.desativar(consulta)) {
                    mensagem = "Consulta desativado com sucesso!";
                } else {
                    mensagem = "Falha ao desativar a consulta!";
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
        String idConsulta = request.getParameter("idConsulta");
        String diaHora = request.getParameter("diaHora");
        String observacoes = request.getParameter("observacoes");
        String confirmacoes = request.getParameter("confirmacoes");
        String idCliente = request.getParameter("idCliente");
        String filtro = request.getParameter("filtro");

        AgendaConsulta consulta = new AgendaConsulta();
        AgendaConsultaDAO cdao = new AgendaConsultaDAO();
        String mensagem = "";
        HttpSession sessao = request.getSession();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfComplex = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (!idConsulta.isEmpty()) {
            consulta.setIdConsulta(Integer.parseInt(idConsulta));
        }

        if (diaHora.isEmpty() || diaHora.equals("")) {
            sessao.setAttribute("msg", "Informe a data e a hora da consulta!");
            exibirMensagem(request, response);
        } else {
            try {
                consulta.setDiaHora(dfComplex.parse(diaHora));
            } catch (ParseException e) {
                mensagem = "Error: " + e.getMessage();
                e.printStackTrace();
            }

        }

        consulta.setObservacoes(observacoes);
        consulta.setConfirmacao(confirmacoes);

        if (idCliente.isEmpty() || idCliente.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Cliente!");
            exibirMensagem(request, response);
        } else {
            Cliente cliente = new Cliente();
            try {
                cliente.setIdCliente(Integer.parseInt(idCliente));
                consulta.setCliente(cliente);
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        try {
            if (cdao.gravar(consulta)) {
                mensagem = "Agendamento salvo na base de dados!";
            } else {
                mensagem = "Falha ao salvar o agendamento na base de dados!";
            }
        } catch (SQLException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href="+filtro+";"
                + "</script>"
        );
    }

    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/cadastrarConsulta.jsp");
        dispatcher.forward(request, response);
    }

}
