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
import javax.servlet.http.HttpSession;
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
                if (l.getIdLente() > 0) {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String idLente = request.getParameter("idLente");
        String nome = request.getParameter("nome");
        String modelo = request.getParameter("modelo");
        String fabricante = request.getParameter("fabricante");
        String preco = request.getParameter("preco");
        String status = request.getParameter("status");
        String mensagem = "";
        HttpSession sessao = request.getSession();

        Lente l = new Lente();
        LenteDAO ldao = new LenteDAO();

        if (!idLente.isEmpty()) {
            l.setIdLente(Integer.parseInt(idLente));
        }

        if (nome.isEmpty() || nome.equals("")) {
            sessao.setAttribute("msg", "Informe o nome da Lente!");
            exibirMensagem(request, response);
        } else {
            l.setNome(nome);
        }

        if (preco.isEmpty() || preco.equals("")) {
            sessao.setAttribute("msg", "Informe o preco da lente!");
            exibirMensagem(request, response);
        } else {
            if (podeConverterParaDouble(preco)) {
                preco = preco.replaceAll(",", ".");
                l.setPreco(Double.valueOf(preco));
            }else{
                sessao.setAttribute("msg", "Formato de preco invalido");
                exibirMensagem(request, response);
            }
        }

        if (status.isEmpty() || status.equals("")) {
            sessao.setAttribute("msg", "Informe o status da Lente!");
            exibirMensagem(request, response);
        } else {
            l.setStatus(Integer.parseInt(status));
        }

        //campos que podem ser nulos
        l.setModelo(modelo);
        l.setFabricante(fabricante);
        
        try {
            if (ldao.gravar(l)) {
                mensagem = "Lente salvo na base de dados!";
            } else {
                mensagem = "Falha ao salvar a Lente na base de dados!";
            }
        } catch (SQLException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarLente?acao=listar';"
                + "</script>"
        );
    }

    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/cadastrarLente.jsp");
        dispatcher.forward(request, response);
    }

    private boolean podeConverterParaDouble(String s) {

        //Verifica se o texto tem so uma virgula ou so um ponto
        int ponto = 0;
        int virgula = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                ponto += 1;
            } else if (s.charAt(i) == ',') {
                virgula += 1;
            }
        }

        //Verifica se o texto pode ser convertido para numero
        boolean ehDouble = false;
        if (virgula == 0 && ponto == 0 || virgula == 1 && ponto == 0 || virgula == 0 && ponto == 1) {
            s.replaceAll(".", "");
            s.replaceAll(",", "");
            for (int i = 0; i < s.length(); i++) {
                if (Character.isDigit(s.charAt(i))) {
                    ehDouble = true;
                }
            }
        }
        return ehDouble;
    }

}
