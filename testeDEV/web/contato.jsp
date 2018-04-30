<%@page import="br.com.marcio.entity.Contato"%>
<%@page import="br.com.marcio.servlet.ClienteServlet"%>
<%@page import="java.util.List"%>
<%@page import="br.com.marcio.entity.Cliente"%>
<%@page import="br.com.marcio.entity.Endereco"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt">
<head>
  <title>Cadastro de Endereço</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <script type="text/javascript" src="./resources/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.maskedinput.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.zebra-datepicker.js"></script>

</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">Site S.A</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="cliente.jsp">Cadastrar Cliente</a></li>
      <li><a href="ClienteServlet?acao=Listar">Listar Cliente</a></li>
      <li><a href="endereco.jsp">Cadastrar Endereço</a></li>
      <li><a href="EnderecoServlet?acao=Listar">Listar Endereço</a></li>
      <li><a href="contato.jsp">Cadastrar Contato</a></li>
      <li><a href="ContatoServlet?acao=Listar">Listar Contato</a></li>
    </ul>

  </div>
</nav>
<div class="container">

<form action="ContatoServlet" method="post" id="validate">
 <% 
        Contato contato = (Contato)request.getAttribute("contato");
 %>
    <fieldset>
	<legend>Cadastro de Contato</legend>
        <% if (contato != null){ %>
            <input type="hidden" name="acao"value="editar">
        <% }else{ %>
            <input type="hidden" name="acao"value="salvar">
        <% } %> 
        <input type="hidden" name="id" value="<%= contato != null ? contato.getId() : ""  %>">
            	
        <div class="form-group">
            <label>Cliente:</label>
            <select name="cpfCnpj"class="form-control" required="True">
            <%
                List<Cliente> list = ClienteServlet.getListClientes();
                Cliente cli;
                for (Object obj : list){
                    cli = (Cliente)obj; %>
                    <option value="<%=cli.getCpfCnpj()%>"><%=cli.getNome()%><option>
            <%    }
            %> 
            </select>
        </div>
        <div class="form-group">
            <label>Nome:</label>
            <input type="text" class="form-control" name="nome" id="nome" value="<%= contato != null ? contato.getNome() : ""  %>" size="255">
        </div>
        <div class="form-group">
            <label>Telefone</label>
            <input type="text" class="form-control" name="telefone" id="telefone" value="<%= contato != null ? contato.getTelefone() : "" %>" size="255">
        </div>
         <div class="col-md-12">
            <button type="submit" class="btn btn-primary">Salvar</button>
            <button type="reset" class="btn btn-primary">Limpar</button>
         </div>

    </fieldset>
 </form>

 </div>
 </body>
</html>
