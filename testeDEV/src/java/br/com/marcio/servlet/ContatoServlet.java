/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.servlet;

import br.com.marcio.dao.ContatoDao;
import br.com.marcio.entity.Contato;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet implementation class EnderecoServlet
 */
@WebServlet("/ContatoServlet")
public class ContatoServlet extends HttpServlet {

 private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
       HttpServletResponse response) throws ServletException, IOException {

       String acao = request.getParameter("acao");
       String pagina = "/contatoLista.jsp";

       Contato contato = new Contato();
       if ((request.getParameter("id") != null) && (request.getParameter("id") != "")) {
           contato.setId(Long.parseLong(request.getParameter("id")));
       }
       contato.setCpfCnpj(request.getParameter("cpfCnpj"));
       contato.setNome(request.getParameter("nome"));
       contato.setTelefone(request.getParameter("telefone"));
       
       ContatoDao dao = new ContatoDao();


       List<Contato> contatos = new ArrayList<Contato>();

       switch (acao) {

       case "salvar":
           dao.inserir(contato);
           contatos = dao.todos();
       break;

       case "editar":
           dao.atualizar(contato);
           contatos = dao.todos();
       break;

       case "excluir":
           dao.remover(contato);
           contatos = dao.todos();
       break;
       
       case "Listar":

           contatos = dao.todos();
           request.setAttribute("contatos", contatos);
           request.getRequestDispatcher("contatoLista.jsp").forward(request, response);

       break;
       
       case "editando": 
           String id = request.getParameter("id");
           long idContato = Long.parseLong(id);
           contato = dao.pesquizarId(idContato);
           request.setAttribute("contato", contato);
           request.getRequestDispatcher("contato.jsp").forward(request, response);
       break;
     
       case "pesquisar":
           String texto = request.getParameter("texto");
           contatos = dao.consultar(texto);
           request.setAttribute("contatos", contatos);
           request.getRequestDispatcher("contatoLista.jsp").forward(request, response);
       break;
       }

       request.setAttribute("contatos", contatos);
       request.getRequestDispatcher(pagina).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
