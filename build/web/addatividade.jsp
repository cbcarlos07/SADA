<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="sada" %>
<html>
    <head>
        <title>Tela Principal</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <link href="img/malha.ico" rel="short icon">
    </head>
    <body>
        <!-- Modal -->
<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalLabel">Excluir Item</h4>
            </div>
            <div class="modal-body">Deseja realmente excluir este item? </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Sim</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
            </div>
        </div>
    </div>
</div>
        
        <c:import url="menu.jsp" />
        <div id="main" class="container-fluid">
            <center><h1>Cadastrar Atividade</h1></center>
            <form action="atividade" method="post">
                <input type="hidden" value="I" name="acao"/>
                <div class="col-lg-offset-3">
                <div class="form-group col-lg-4 ">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" class="form-control" required="">
                </div>
                <div class="form-group col-lg-11 "></div>
                <div class="form-group col-lg-4 ">
                    <label for="descricao">Descri&ccedil;&atilde;o</label>
                    <input type="text" id="nivel" name="descricao" class="form-control" required="">
                </div>
                <div class="form-group col-lg-11 "></div>
                <div class="form-group col-lg-4 ">
                    <label for="descricao">Nivel</label>
                     <jsp:useBean id="nc" class="controller.Nivel_Controller" />
                     
                    <select name="nivel" class="form-control">
                       <c:forEach var="nivel" items="${nc.pesquisar_nivel('')}" >     
                            <option value="${nivel.nivel_Codigo}">${nivel.nivel_Descricao}</option>  
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-lg-11 "></div>     
                <hr>
                <div class="btn-group col-lg-8">
                    <button type="submit" class="btn btn-success">Salvar</button><button type="reset" class="btn btn-primary">Limpar</button>
                </div>
                </div>
            </form>
            
        </div> <!-- /#main -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
