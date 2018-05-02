/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.servlet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.marcio.dao.EnderecoDao;
import br.com.marcio.entity.Endereco;
import java.io.IOException;

/**
 * Servlet implementation class EnderecoServlet
 */
@WebServlet("/EnderecoServlet")
public class EnderecoServlet extends HttpServlet {

 private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
       HttpServletResponse response) throws ServletException, IOException {

       String acao = request.getParameter("acao");
       String pagina = "/enderecoLista.jsp";

       Endereco endereco = new Endereco();
       if ((request.getParameter("id") != null) && (request.getParameter("id") != "")) {
           endereco.setId(Long.parseLong(request.getParameter("id")));
       }
       endereco.setCpfCnpj(request.getParameter("cpfCnpj"));
       endereco.setLogradouro(request.getParameter("logradouro"));
       endereco.setBairro(request.getParameter("bairro"));
       endereco.setCidade(request.getParameter("cidade"));
       endereco.setUf(request.getParameter("uf"));
       endereco.setCep(request.getParameter("cep"));

       EnderecoDao dao = new EnderecoDao();
      // Endereco endereco = getParameters(request);


       List<Endereco> enderecos = new ArrayList<Endereco>();

       switch (acao) {

       case "salvar":
           dao.inserir(endereco);
           enderecos = dao.todos();
       break;

       case "editar":
           dao.atualizar(endereco);
           enderecos = dao.todos();
       break;

       case "excluir":
           dao.remover(endereco);
           enderecos = dao.todos();
       break;
       
       case "Listar":

           enderecos = dao.todos();
           request.setAttribute("enderecos", enderecos);
           request.getRequestDispatcher("enderecoLista.jsp").forward(request, response);

       break;
       
       case "editando": 
           String id = request.getParameter("id");
           long idEndereco = Long.parseLong(id);
           endereco = dao.pesquizarId(idEndereco);
           request.setAttribute("endereco", endereco);
           request.getRequestDispatcher("endereco.jsp").forward(request, response);
       break;
     
       case "pesquisar":
           String texto = request.getParameter("texto");
           enderecos = dao.consultar(texto);
           request.setAttribute("enderecos", enderecos);
           request.getRequestDispatcher("enderecoLista.jsp").forward(request, response);
       break;
       }

       request.setAttribute("enderecos", enderecos);
       request.getRequestDispatcher(pagina).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
