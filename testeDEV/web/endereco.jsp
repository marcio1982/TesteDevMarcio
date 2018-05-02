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
  <script language='JavaScript'>
    function SomenteNumero(e){
        var tecla=(window.event)?event.keyCode:e.which;
        if((tecla>47 && tecla<58)) return true;
        else{
            if (tecla==8 || tecla==0) return true;
            else  return false;
        }
    }
  </script>
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

<form action="EnderecoServlet" method="post" id="validate">
 <% 
        Endereco endereco = (Endereco)request.getAttribute("endereco");
 %>
    <fieldset>
	<legend>Cadastro de Endereço</legend>
        <% if (endereco != null){ %>
            <input type="hidden" name="acao"value="editar">
        <% }else{ %>
            <input type="hidden" name="acao"value="salvar">
        <% } %> 
        <input type="hidden" name="id" value="<%= endereco != null ? endereco.getId() : ""  %>">
            	
        <div class="form-group">
            <label>Cliente:</label>
            <select name="cpfCnpj"class="form-control" required="True">
            <c:forEach var="cliente" items="${ClienteServlet.getListClientes()}">
                    <option value="${cliente.cpfCnpj}">${cliente.nome}<option>
            </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Logradouro:</label>
            <input type="text" class="form-control" name="logradouro" value="<%= endereco != null ? endereco.getLogradouro() : ""  %>" size="255">
        </div>
        <div class="form-group">
            <label>Bairro:</label>
            <input type="text" class="form-control" name="bairro" value="<%= endereco != null ? endereco.getBairro() : "" %>" size="255">
        </div>
        <div class="form-group"> 
            <label>Cidade:</label>
            <input type="text" class="form-control" name="cidade" value="<%= endereco != null ? endereco.getCidade() : "" %>" size="255">
         </div>
         <div class="form-group">'
            <label>UF:</label>
            <select class="form-control" name="uf" value="<%= endereco != null ? endereco.getUf() : "" %>" >
                <option value="">Selecione</option>
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
                <option value="AP">Amapá</option>
                <option value="AM">Amazonas</option>
                <option value="BA">Bahia</option>
                <option value="CE">Ceará</option>
                <option value="DF">Distrito Federal</option>
                <option value="ES">Espirito Santo</option>
                <option value="GO">Goiás</option>
                <option value="MA">Maranhão</option>
                <option value="MS">Mato Grosso do Sul</option>
                <option value="MT">Mato Grosso</option>
                <option value="MG">Minas Gerais</option>
                <option value="PA">Pará</option>
                <option value="PB">Paraíba</option>
                <option value="PR">Paraná</option>
                <option value="PE">Pernambuco</option>
                <option value="PI">Piauí</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="RN">Rio Grande do Norte</option>
                <option value="RS">Rio Grande do Sul</option>
                <option value="RO">Rondônia</option>
                <option value="RR">Roraima</option>
                <option value="SC">Santa Catarina</option>
                <option value="SP">São Paulo</option>
                <option value="SE">Sergipe</option>
                <option value="TO">Tocantins</option>
            </select>
         </div>
         <div class="form-group">
            <label>Cep:</tlabeld>
            <input type="text" class="form-control" onkeypress="return SomenteNumero(event);" name="cep" value="<%= endereco != null ? endereco.getCep() : "" %>" size="10" id="cep">
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
