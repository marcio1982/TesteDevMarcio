<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.marcio.entity.Cliente"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt">
<head>
  <title>Cadastro de Cliente</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
  <script type="text/javascript" src="./resources/js/jquery-1.10.2.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.maskedinput.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.validate.min.js"></script>
  <script type="text/javascript" src="./resources/js/jquery.zebra-datepicker.js"></script>
  <script type="text/javascript">
  $("input[id*='cpfCnpj']").inputmask({
    mask: ['999.999.999-99', '99.999.999/9999-99'],
    keepStatic: true
   });
</script>
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
        <form method="post" action="ClienteServlet" id="validate">
             <% 
                Cliente cliente = (Cliente)request.getAttribute("cliente");
             %>
            <fieldset>
                    <legend>Cadastro de Cliente</legend>
                    <div class="form-group">
                            <label>CPF/CNPJ: </label>
                            <input  id="cpfCnpj" name="cpfCnpj" class="form-control" autofocus="autofocus" required
                                    value="<%= cliente != null ? cliente.getCpfCnpj(): "" %>"
                                    placeholder="" size="20" />
                    </div>
                    <div class="form-group">        
                        <label>Nome:</label>
                        <input name="nome" class="form-control" required="true"  
                                   value="<%= cliente != null ? cliente.getNome() : "" %>"/>
                    </div>
                    <div class="form-group">
                        <label>Telefone:</label>
                        <input name="telefone" type="text" class="form-control"
                               value="<%= cliente != null ?  cliente.getTelefone() : "" %>" id="telefone" />
                    </div>
                    <div class="form-group">
                        <label>Celular:</label>
                        <input name="celular" type="text" class="form-control" id="celular">
                    </div>
                    <div class="form-group">
                         <label>Email:</label>
                         <input name="email" type="text" class="form-control"  
                                value="<%= cliente != null ? cliente.getEmail() : "" %>"/>
                    </div>
                    <div class="form-group">
                        <label>Data de Nascimento/Cadastro</label>
                        <input name="" type="text" class="form-control" id="data" value="<%= cliente != null ? (cliente.getDataCadastro()) : "" %>"
                                placeholder="" /><br /> 
                    </div>
                    <% if (cliente != null){ %>
                        <input type="hidden" name="acao"value="Alterar">
                    <% }else{ %>
                        <input type="hidden" name="acao"value="Incluir">
                    <% } %>        
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-primary">Salvar</button>
                        <button type="reset" class="btn btn-primary">Limpar</button>
                    </div>
            </fieldset>
        </form>
    </div>
</body>
</html>