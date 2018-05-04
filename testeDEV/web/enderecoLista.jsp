<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt">
<head>
  <title>Listagem de Endereço</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	<div class="col-md-6">
            <legend>Listagem de Endereço</legend>
            <div class="input-group h2">
                <form method="post" action="EnderecoServlet">
                    <span class="input-group-btn">
                        <input type="hidden" name="acao"value="pesquisar">
                        <input name="texto" class="form-control" type="text" placeholder="Pesquisar Itens">
                        <button class="btn btn-primary" type="submit">
                            <span class="glyphicon glyphicon-search"></span>
                        </button>
                    </span>
                </form>
            </div>
       </div>
        <div align="center">
		<table class="table table-striped">
			<thead>
                            <tr>
				<th>Id</th>
				<th>Logradouro</th>
				<th>Bairro</th>
				<th>Cidade</th>
				<th>UF</th>
				<th>Cep</th>
                                <th>Comandos</th>
                             </tr>
			</thead>
			<c:forEach var="endereco" items="${enderecos}">
			<tbody>	
                            <tr>
                                <td>${endereco.id}</td>
                                <td>${endereco.logradouro}</td>
                                <td>${endereco.bairro}</td>
                                <td>${endereco.cidade}</td>
                                <td>${endereco.uf}</td>
                                <td>${endereco.cep}</td>
                                <td>
                                    <a class="btn btn-warning btn-xs" href="EnderecoServlet?acao=editando&id=${endereco.id}">Editar</a>
                                    <a class="btn btn-danger btn-xs" href="EnderecoServlet?acao=excluir&id=${endereco.id}">Excluir</a> 
                                </td>
                            </tr>
                        </tbody>
			</c:forEach>
		</table>
		<c:if test="${fn:length(enderecos) > 0}">
   		Existem ${fn:length(enderecos)} endereços!
 		</c:if><br> 		
	</div>
</div>    
</body>
</html>