package controller;

import dao.MenuDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;

@WebServlet(name = "GerenciarMenu", urlPatterns = {"/gerenciarMenu"})
public class GerenciarMenu extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String acao = request.getParameter("acao");
        String idMenu = request.getParameter("idMenu");
        String mensagem = "";

        Menu m = new Menu();
        MenuDAO mdao = new MenuDAO();
        

        try {
            if (acao.equals("listar")) {
                ArrayList<Menu> menus = new ArrayList<>();
                menus = mdao.getLista();
                RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/listarMenus.jsp");
                
                request.setAttribute("menus", menus);
                dispatcher.forward(request, response);

            } else if (acao.equals("alterar")) {
                
                m = mdao.getCarregarPorId(Integer.parseInt(idMenu));
                if (m.getIdMenu() > 0) {
                    RequestDispatcher dispatcher = 
                        getServletContext().getRequestDispatcher("/cadastrarMenu.jsp");
                    request.setAttribute("menu", m);
                    dispatcher.forward(request, response);
                }else{
                    mensagem = "Menu não encontrado na base de dados!";
                }
                
            } else if (acao.equals("ativar")) {
                m.setIdMenu(Integer.parseInt(idMenu));
                if (mdao.ativar(m)) {
                    mensagem = "Menu ativado com sucesso!";
                }else{
                    mensagem = "Falha ao ativar o menu!";
                }
            } else if (acao.equals("desativar")) {
                m.setIdMenu(Integer.parseInt(idMenu));
                if (mdao.desativar(m)) {
                    mensagem = "Menu desativado com sucesso!";
                }else{
                    mensagem = "Falha ao desativar o menu!";
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
                + "location.href='gerenciarMenu?acao=listar';"
                + "</script>"
        ); 
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idMenu = request.getParameter("idMenu");
        String nome = request.getParameter("nome");
        String link = request.getParameter("link");
        String exibir = request.getParameter("exibir");
        String status = request.getParameter("status");
        String mensagem = "";
        HttpSession sessao = request.getSession();

        Menu m = new Menu();
        MenuDAO mdao = new MenuDAO();

        if(!idMenu.isEmpty()) {
            try {
                m.setIdMenu(Integer.parseInt(idMenu));
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        if (nome.isEmpty() || nome.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Menu!");
            exibirMensagem(request, response);
        } else {
            m.setNome(nome);
        }

        if (link.isEmpty() || link.equals("")) {
            sessao.setAttribute("msg", "Informe o link do menu!");
            exibirMensagem(request, response);
        } else {
             m.setLink(link);
        }
        
        if (link.isEmpty() || link.equals("")) {
            sessao.setAttribute("msg", "Informe o link do Menu!");
            exibirMensagem(request, response);
        } else {
             m.setLink(link);
        }
        
        if (exibir.isEmpty() || exibir.equals("")) {
            sessao.setAttribute("msg", "Informe o tipo de exibição do Menu!");
            exibirMensagem(request, response);
        } else {
            try {
                m.setExibir(Integer.parseInt(exibir));
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        if (status.isEmpty() || status.equals("")) {
            sessao.setAttribute("msg", "Informe o status do Menu!");
            exibirMensagem(request, response);
        } else {
            try {
                m.setStatus(Integer.parseInt(status));
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        try {
            if (mdao.gravar(m)) {
                mensagem = "Menu salvo na base de dados!";
            } else {
                mensagem = "Falha ao salvar o menu na base de dados!";
            }
        } catch (SQLException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarMenu?acao=listar';"
                + "</script>"
        );
    }

    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/cadastrarMenu.jsp");
        dispatcher.forward(request, response);
    }
           
}