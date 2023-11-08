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
import javax.servlet.http.HttpSession;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idLaboratorio = request.getParameter("idLaboratorio");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        String mensagem = "";
        HttpSession sessao = request.getSession();

        Laboratorio lab = new Laboratorio();
        LaboratorioDAO ldao = new LaboratorioDAO();

        if (!idLaboratorio.isEmpty()) {
            lab.setIdLaboratorio(Integer.parseInt(idLaboratorio));
        }

        if (nome.isEmpty() || nome.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Laboratorio!");
            exibirMensagem(request, response);
        } else {
            lab.setNome(nome);
        }

        if (endereco.isEmpty() || endereco.equals("")) {
            sessao.setAttribute("msg", "Informe o endereco do Laboratorio!");
            exibirMensagem(request, response);
        } else {
            lab.setEndereco(endereco);
        }

        if (telefone.isEmpty() || telefone.equals("")) {
            sessao.setAttribute("msg", "Informe o telefone do laboratorio!");
            exibirMensagem(request, response);
        } else {
            lab.setTelefone(telefone);
        }

        if (email.isEmpty() || email.equals("")) {
            sessao.setAttribute("msg", "Informe o email do laboratorio!");
            exibirMensagem(request, response);
        } else {
            lab.setEmail(email);
        }
        if (status.isEmpty() || status.equals("")) {
            sessao.setAttribute("msg", "Informe o status do laboratorio!");
            exibirMensagem(request, response);
        } else {
            lab.setStatus(Integer.parseInt(status));
        }

        try {
            if (ldao.gravar(lab)) {
                mensagem = "Laboratorio salvo na base de dados!";
            } else {
                mensagem = "Falha ao salvar o Laboratorio na base de dados!";
            }
        } catch (SQLException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarLaboratorio?acao=listar';"
                + "</script>"
        );
    }
    
    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/cadastrarLente.jsp");
        dispatcher.forward(request, response);
    }

}
