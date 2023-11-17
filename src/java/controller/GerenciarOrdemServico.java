package controller;

import dao.OrdemServicoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jdk.nashorn.internal.objects.Global;
import model.Cliente;
import model.Laboratorio;
import model.Lente;
import model.OrdemServico;
import model.Usuario;

@WebServlet(name = "GerenciarOrdemServico", urlPatterns = {"/gerenciarOrdemServico"})
public class GerenciarOrdemServico extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        String acao = request.getParameter("acao");
        String ano = request.getParameter("ano");
        String mes = request.getParameter("mes");
        String idOs = request.getParameter("idOrdemServico");
        String statusEntrega = request.getParameter("statusEntrega");

        String mensagem = "";

        OrdemServicoDAO osdao = new OrdemServicoDAO();
        OrdemServico os = new OrdemServico();
        HttpSession sessao = request.getSession();

        try {
            if (acao.equals("listar")) {
                ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
                ordemServicos = osdao.getLista(Integer.parseInt(mes),
                        Integer.parseInt(ano));
                RequestDispatcher dispatcher
                        = getServletContext().getRequestDispatcher("/listarOrdemServicos.jsp");
                sessao.setAttribute("ano", ano);
                sessao.setAttribute("mes", mes);
                request.setAttribute("ordemServico", ordemServicos);
                dispatcher.forward(request, response);
            } else if (acao.equals("alterar")) {

                os = osdao.getCarregarPorId(Integer.parseInt(idOs));
                if (os.getIdOs() > 0) {
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
            } else if (acao.equals("atualizarEntrega")) {
                if (osdao.atualizaEntrega(Integer.parseInt(idOs), statusEntrega)) {
                    mensagem = "Ordem de Servico concluido!";
                } else {
                    mensagem = "Falha a atualizar o status de entrega";
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
        PrintWriter out = response.getWriter();
        String idOs = request.getParameter("idOs");
        String idUsuario = request.getParameter("idUsuario");
        String dataOS = request.getParameter("dataOS");
        String dataVenda = request.getParameter("dataSolicitacao");
        String statusEntrega = request.getParameter("statusEntrega");
        String vencimento = request.getParameter("vencimento");
        String idCliente = request.getParameter("idCliente");
        String idLente = request.getParameter("idLente");
        String idLaboratorio = request.getParameter("idLaboratorio");
        String status = request.getParameter("status");
        String mensagem = "";
        HttpSession sessao = request.getSession();

        OrdemServico os = new OrdemServico();
        OrdemServicoDAO osdao = new OrdemServicoDAO();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        if (!idOs.isEmpty()) {
            try {
                os.setIdOs(Integer.parseInt(idOs));
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        if (idUsuario.isEmpty() || idUsuario.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Usuario!");
            exibirMensagem(request, response);
        } else {
            Usuario usuario = new Usuario();
            try {
                usuario.setIdUsuario(Integer.parseInt(idUsuario));
                os.setUsuario(usuario);
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }

        }

        if (dataVenda.isEmpty() || dataVenda.equals("")) {
            sessao.setAttribute("msg", "Informe a data de Venda!");
            exibirMensagem(request, response);
        } else {
            try {
                os.setDataVenda(df.parse(dataVenda));
            } catch (ParseException e) {
                mensagem = "Error: " + e.getMessage();
                e.printStackTrace();
            }

        }

        try {
            os.setDataOS(df.parse(dataOS));
        } catch (ParseException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        System.out.println(dataVenda);
        //Para atributos em que o valor pode ser nulo
        try {
            Date dataEntrega = null;
            os.setDataVencimento(df.parse(vencimento));
            os.setDataEntrega(dataEntrega);
        } catch (ParseException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        if (idCliente.isEmpty() || idCliente.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Cliente!");
            exibirMensagem(request, response);
        } else {
            Cliente cliente = new Cliente();
            try {
                cliente.setIdCliente(Integer.parseInt(idCliente));
                os.setCliente(cliente);
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        if (idLente.isEmpty() || idLente.equals("")) {
            sessao.setAttribute("msg", "Informe o nome da Lente!");
            exibirMensagem(request, response);
        } else {
            Lente lente = new Lente();
            try {
                lente.setIdLente(Integer.parseInt(idLente));
                os.setLente(lente);
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        if (idLaboratorio.isEmpty() || idLaboratorio.equals("")) {
            sessao.setAttribute("msg", "Informe o nome do Laboratorio!");
            exibirMensagem(request, response);
        } else {
            Laboratorio laboratorio = new Laboratorio();
            try {
                laboratorio.setIdLaboratorio(Integer.parseInt(idLaboratorio));
                os.setLaboratorio(laboratorio);
            } catch (NumberFormatException e) {
                mensagem = "Error" + e.getMessage();
            }
        }

        os.setStatusEntrega(statusEntrega);

        if (status.isEmpty() || status.equals("")) {
            sessao.setAttribute("msg", "Informe o status do Usuário!");
            exibirMensagem(request, response);
        } else {
            os.setStatus(Integer.parseInt(status));
        }

        if (os.getStatusEntrega() == null) {
            os.setStatusEntrega("");
        }

        try {
            if (osdao.gravar(os)) {
                System.out.println(os);
                mensagem = "Ordem de servico salvo na base de dados!";
            } else {
                mensagem = "Falha ao salvar o ordem de servico na base de dados!";
            }
        } catch (SQLException e) {
            mensagem = "Error: " + e.getMessage();
            e.printStackTrace();
        }

        out.println(
                "<script type='text/javascript'>"
                + "alert('" + mensagem + "');"
                + "location.href='gerenciarOrdemServico?acao=listar';"
                + "</script>"
        );

    }

    private void exibirMensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().
                getRequestDispatcher("/cadastrarOS.jsp");
        dispatcher.forward(request, response);
    }
}
