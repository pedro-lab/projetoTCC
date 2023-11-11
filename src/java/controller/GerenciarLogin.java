package controller;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Menu;
import model.Usuario;

@WebServlet(name = "GerenciarLogin", urlPatterns = {"/gerenciarLogin"})
public class GerenciarLogin extends HttpServlet {

    private static HttpServletResponse response;
    RequestDispatcher dispatcher = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("ulogado") != null) {
            sessao.removeAttribute("ulogado");
            sessao.invalidate();
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        GerenciarLogin.response = response;
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String mensagem = "";
        Usuario u = new Usuario();
        UsuarioDAO udao = new UsuarioDAO();
        HttpSession sessao = request.getSession();

        if (login.equals("") || login.isEmpty()) {
            sessao.setAttribute("msg", "Informe o login do Usuário!");
            exibirMensagem(request, response);
        } else {
            u.setLogin(login);
        }
        System.out.println(senha);
        if (senha.equals("") || senha.isEmpty()) {
            sessao.setAttribute("msg", "Informe a senha do Usuário!");
            exibirMensagem(request, response);
        } else {
            u.setSenha(senha);
        }

        try {
            u = udao.getRecuperarUsuario(login);
            if ((u.getIdUsuario() > 0) && (u.getSenha().equals(senha))) {
                sessao.setAttribute("ulogado", u);
                response.sendRedirect("index.jsp");

            } else {
                mensagem = "Login ou senha inválida!";
            }
        } catch (SQLException e) {
            mensagem = "Error : " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='formLogin.jsp';"
                + "</script>"
        );

    }

    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/formLogin.jsp");
        dispatcher.forward(request, response);
    }

    public static Usuario verificarAcesso(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        GerenciarLogin.response = response;
        Usuario usuario = null;

        try {
            HttpSession sessao = request.getSession();
            if (sessao.getAttribute("ulogado") == null) {
                request.setAttribute("msg", "Usuário não autenticado no sistema!");
                response.sendRedirect("formLogin.jsp");
            } else {
                String uri = request.getRequestURI();
                String queryString = request.getQueryString();

                if (queryString != null) {
                    uri += "?" + queryString;
                }

                usuario = (Usuario) request.getSession().getAttribute("ulogado");
                if (usuario == null) {
                    request.setAttribute("msg", "Usuário não autenticado no sistema!");
                } else {
                    response.sendRedirect("formLogin.jsp");
                    boolean possuiAcesso = false;
                    for (Menu m : usuario.getPerfil().getMenus()) {
                        System.out.println(uri.contains(m.getLink()));
                        if (uri.contains(m.getLink())) {
                            System.out.println(uri);
                            possuiAcesso = true;
                            break;
                        }

                        if (!possuiAcesso) {
                            sessao.setAttribute("msg", "Usuário não autorizado!");
                        }
                        System.out.println("Variavel possui acesso: "+possuiAcesso);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return usuario;
    }
}
