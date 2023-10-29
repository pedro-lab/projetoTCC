package controller;

import dao.LaboratorioDAO;
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
import model.Laboratorio;

@WebServlet(name = "GerenciarLaboratorio", urlPatterns = {"/gerenciarLaboratorio"})
public class GerenciarLaboratorio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idLaboratorio = request.getParameter("idLaboratorio");
        String mensagem = "";

        Laboratorio lab = new Laboratorio();
        LaboratorioDAO ldao = new LaboratorioDAO();
        

        try {
            if (acao.equals("listar")) {
                ArrayList<Laboratorio> laboratorios = new ArrayList<>();
                laboratorios = ldao.getLista();
                RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/listarLaboratorios.jsp");
                
                request.setAttribute("laboratorios", laboratorios);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {
                
                lab = ldao.getCarregarPorId(Integer.parseInt(idLaboratorio));
                if (lab.getIdLaboratorio()> 0) {
                    RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/cadastrarLaboratorio.jsp");
                    request.setAttribute("laboratorio", lab);
                    dispatcher.forward(request, response);
                }else{
                    mensagem = "Laboratorio não encontrado na base de dados!";
                }
                
            } else if (acao.equals("ativar")) {
                lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
                if (ldao.ativar(lab)) {
                    mensagem = "Laboratorio ativado com sucesso!";
                }else{
                    mensagem = "Falha ao ativar o laboratorio!";
                }
            } else if (acao.equals("desativar")) {
                lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
                if (ldao.desativar(lab)) {
                    mensagem = "Laboratorio desativado com sucesso!";
                }else{
                    mensagem = "Falha ao desativar o laboratorio!";
                }
            } else {
                response.sendRedirect("index.jsp");
            }

        } catch (SQLException e) {
            mensagem = "Erro: "+ e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarLaboratorio?acao=listar';"
                + "</script>"
        ); 
        
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

}
