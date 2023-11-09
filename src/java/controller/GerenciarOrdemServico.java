package controller;

import dao.OrdemServicoDAO;
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
import model.OrdemServico;

@WebServlet(name = "GerenciarOrdemServico", urlPatterns = {"/gerenciarOrdemServico"})
public class GerenciarOrdemServico extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String idOs = request.getParameter("idOs");
        String mensagem = "";

        OrdemServicoDAO osdao = new OrdemServicoDAO();
        OrdemServico os = new OrdemServico();

        try {
            if (acao.equals("listar")) {
                ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
                ordemServicos = osdao.getLista();
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher("/listarOrdemServicos.jsp");

                request.setAttribute("ordemServicos", ordemServicos);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {

                os = osdao.getCarregarPorId(Integer.parseInt(idOs));
                if (os.getIdOs()> 0) {
                    RequestDispatcher dispatcher
                            = getServletContext().getRequestDispatcher("/cadastrarOS.jsp");
                    request.setAttribute("ordemServico", os);
                    dispatcher.forward(request, response);
                } else {
                    mensagem = "Ordem de Servico não encontrado na base de dados!";
                }

            } else if (acao.equals("ativar")) {
                os.setIdOs(Integer.parseInt(idOs));
                if (osdao.ativar(os)) {
                    mensagem = "Ordem de Servico ativado com sucesso!";
                } else {
                    mensagem = "Falha ao ativar a ordem de servico!";
                }
            } else if (acao.equals("desativar")) {
                os.setIdOs(Integer.parseInt(idOs));
                if (osdao.desativar(os)) {
                    mensagem = "Ordem de Servico desativado com sucesso!";
                } else {
                    mensagem = "Falha ao desativar a Ordem de Servico!";
                }
            } else {
                response.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            mensagem = "Erro: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarOrdemServico?acao=listar';"
                + "</script>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String aos;
    }
}
