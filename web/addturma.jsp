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
        <link rel="stylesheet" href="css/jquery.datetimepicker.min.css">
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
        <div id="main" class="container">
           
            <center><h1>Cadastrar Turma</h1></center>
             <hr />
             
             <div class="col-lg-offset-2">
            <form action="turma" method="post">
                <input type="hidden" value="I" name="acao"/>
                
                <div class="form-group col-lg-2 ">
                    <label for="hora">Hor&aacute;rio</label>
                    <input type="text" id="hora" name="hora" class="form-control" required="">
                </div>
                
                <div class="form-group col-lg-4 row">
                    <label for="obs">Observa&ccedil;&atilde;o</label>
                    <textarea id="obs" name="obs" class="form-control"></textarea>
                </div>
                
                <div class="form-group col-lg-2 ">
                   <label for="data">Data</label>
                   <input id="data" name="data" class="form-control" required="">
                </div>
                <br><br><br> <br><br>
                <div class="form-group col-lg-4 ">
                    <label for="descricao">Descri&ccedil;&atilde;o</label>
                    <input id="descricao" name="descricao" class="form-control" required=""  >
                </div>
              
                <div class="form-group col-lg-11 "></div>
                <div class="form-group col-lg-4">
                    <label for="instrutor">Instrutor</label>
                    <jsp:useBean id="fc" class="controller.Funcionario_Controller" />
                    <select name="funcionario" id="instrutor" class="form-control">
                        <c:forEach var="funcionario" items="${fc.pesquisar_funcionario('')}" >
                            <option value="${funcionario.funcionario_Codigo}">${funcionario.funcionario_Nome}</option>
                        </c:forEach>
                            
                    </select>
                </div>
                
              
                <div class="form-group col-lg-4 ">
                    <label for="atividade">Atividade</label>
                     <jsp:useBean id="ac" class="controller.Atividade_Controller" />
                     
                    <select name="atividade" class="form-control">
                        <c:forEach var="atividade" items="${ac.pesquisar_atividade('')}" >     
                            <option value="${atividade.atividade_Codigo}"> ${atividade.atividade_Descricao}</option>  
                        </c:forEach>
                    </select>
                </div>
               <br><br><br><br><br><br><br><br><br>
                <div class="btn-group col-lg-8">
                    <button type="submit" class="btn btn-success">Salvar</button><button type="reset" class="btn btn-primary">Limpar</button>
                </div>
              
                  
            </form>
            </div>
             
        </div> <!-- /#main -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.datetimepicker.full.js"></script>
        <script>
            
            $("#hora").datetimepicker({
                datepicker: false,
                format: 'H:i',
                mask: true        
            });
            $.datetimepicker.setLocale('pt-BR');
           $("#data").datetimepicker({
                timepicker: false,
                format: 'd/m/Y',
                mask: true        
            });
            $.datetimepicker.setLocale('pt-BR');
            
            
        </script>
    </body>
</html>
