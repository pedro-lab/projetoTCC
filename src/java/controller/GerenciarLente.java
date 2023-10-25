package controller;

import dao.LenteDAO;
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
import model.Lente;

@WebServlet(name = "GerenciarLente", urlPatterns = {"/gerenciarLente"})
public class GerenciarLente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idLente = request.getParameter("idLente");
        String mensagem = "";

        Lente l = new Lente();
        LenteDAO ldao = new LenteDAO();

        try {
            if (acao.equals("listar")) {
                ArrayList<Lente> lentes = new ArrayList<>();
                lentes = ldao.getLista();
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher("/listarLentes.jsp");

                request.setAttribute("lentes", lentes);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {

                l = ldao.getCarregarPorId(Integer.parseInt(idLente));
                if (l.getIdLente()> 0) {
                    RequestDispatcher dispatcher
                            = getServletContext().getRequestDispatcher("/cadastrarLente.jsp");
                    request.setAttribute("lente", l);
                    dispatcher.forward(request, response);
                } else {
                    mensagem = "Lente não encontrado na base de dados!";
                }

            } else if (acao.equals("ativar")) {
                l.setIdLente(Integer.parseInt(idLente));
                if (ldao.ativar(l)) {
                    mensagem = "Lente ativado com sucesso!";
                } else {
                    mensagem = "Falha ao ativar a lente!";
                }
            } else if (acao.equals("desativar")) {
                l.setIdLente(Integer.parseInt(idLente));
                if (ldao.desativar(l)) {
                    mensagem = "Lente desativado com sucesso!";
                } else {
                    mensagem = "Falha ao desativar a lente!";
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
                + "location.href='gerenciarLente?acao=listar';"
                + "</script>"
        );
    }
        
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//}
