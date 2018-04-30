/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.marcio.servlet;

import br.com.marcio.dao.ClienteDao;
import br.com.marcio.entity.Cliente;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adm
 */

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
        public static List<Cliente> getListClientes(){
            List<Cliente> lista = new ArrayList<>();
            Cliente cliente = new Cliente();
            ClienteDao clienteDao= new ClienteDao();
            return lista = clienteDao.listar();
        }
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
            doPost(request, response);
	}
 
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException 
        {
            String acao = request.getParameter("acao");
            String destino = "sucesso.jsp";
            String mensagem = "";
            List<Cliente> lista = new ArrayList<>();
 
            Cliente cliente = new Cliente();
            ClienteDao clienteDao= new ClienteDao();
 
            try {

                    //Se a ação for DIFERENTE de Listar são lidos os dados da tela
                    if (!acao.equalsIgnoreCase("Listar")) {
                            cliente.setCpfCnpj((request.getParameter("cpfCnpj")));
                            cliente.setNome(request.getParameter("nome"));
                            cliente.setTelefone(request.getParameter("telefone"));
                            cliente.setEmail(request.getParameter("email"));

                            //Faz a leitura da data de cadastro. Caso ocorra um erro de formatação
                            // o sistema utilizará a data atual
                            try {
                                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");					
                                    cliente.setDataCadastro(df.parse(request.getParameter("dataCadastro")));
                            } catch (Exception e) {
                                    cliente.setDataCadastro(new Date());	
                            }

                    }

                    if (acao.equalsIgnoreCase("Incluir")) {
                            // Verifica se a matrícula informada já existe no Banco de Dados
                            // Se existir enviar uma mensagem senão faz a inclusão
                            if (clienteDao.existe(cliente)) {
                                    mensagem = "CPF/CNPJ informado já existe!";
                            } else if (clienteDao.validaIdade(cliente)) {
                                    mensagem = "Idade Menor que o esperado!";
                            } else {
                                    clienteDao.inserir(cliente);
                            }
                    } else if (acao.equalsIgnoreCase("editando")){ 
                            String cpfCnpj = request.getParameter("cpfCnpj");
                            cliente = clienteDao.pesquisarId(cpfCnpj);
                            request.setAttribute("cliente", cliente);
                            request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    } else if (acao.equalsIgnoreCase("Alterar")) {
                            clienteDao.alterar(cliente);
                    } else if (acao.equalsIgnoreCase("Excluir")) {
                            clienteDao.excluir(cliente);
                    } else if (acao.equalsIgnoreCase("Consultar")) {
                            String texto = request.getParameter("texto");
                            request.setAttribute("cliente", cliente);
                            cliente = clienteDao.consultar(texto);
                            //destino = "clienteLista.jsp";
                    }
            } catch (Exception e) {
                    mensagem += e.getMessage();
                    destino = "erro.jsp";
                    e.printStackTrace();
            }
            // Se a mensagem estiver vazia significa que houve sucesso!
            // Senão será exibida a tela de erro do sistema.
            //if (mensagem.length() == 0) {
            //        mensagem = "Cliente Cadastrado com sucesso!";
            //} else {
            //        destino = "erro.jsp";
            //}
 
            // Lista todos os registros existente no Banco de Dados
            lista = clienteDao.listar();
            request.setAttribute("listaCliente", lista);
            request.setAttribute("mensagem", mensagem);


            //O sistema é direcionado para a página 
            //sucesso.jsp Se tudo ocorreu bem
            //erro.jsp se houver algum problema.
            RequestDispatcher rd = request.getRequestDispatcher(destino);
            rd.forward(request, response);
	}
    
}
