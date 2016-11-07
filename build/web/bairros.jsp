<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Gerenciamento de Bairros</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
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
            <div class="modal-body">Deseja realmente excluir o item <span class="nome"></span>? </div>
            <div class="modal-footer">
                <a href="#" type="button"  class="btn btn-primary delete-yes">Sim</a>
                <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
            </div>
        </div>
    </div>
</div>
        
        <c:import url="menu.jsp" />
        <div id="main" class="container">
            <div id="top" class="row">
                <div class="col-md-3">
                    <h2>Bairros</h2>
                </div>
                <form action="bairro" method="post">
                    <input type="hidden" value="P" name="acao" />
                <div class="col-md-6">
                    <div class="input-group h2">
                        <input name="bairro" class="form-control" id="search" type="text" placeholder="Pesquisar Itens">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                    </div>
                </div>
                </form>    
                <div class="col-md-3">
                    <a href="addbairro.jsp" class="btn btn-primary pull-right h2">Novo Item</a>
                </div>
            </div><!-- /#top -->
            <hr />
            
           <!-- <jsp:useBean id="bc" class="controller.Bairro_Controller" /> -->
           
            <div id="list" class="row">
                <div class="table-responsive col-md-12">
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>NOME</th>                       
                            </tr>
                        </thead>
                        <tbody>
                        
                            <c:forEach var="bairro" items="${bairros}" >
                                <tr>
                                    <td>${bairro.bairro_Codigo}</td>
                                    <td>${bairro.bairro_Descricao}</td>                                
                                    <td class="actions">                                    
                                        <a class="btn btn-warning btn-xs" href="bairro?acao=B&id=${bairro.bairro_Codigo}">Editar</a>
                                        <a class="btn btn-danger btn-xs delete" href="#" data-toggle="modal" data-target="#delete-modal" data-nome="${bairro.bairro_Descricao}" data-id="${bairro.bairro_Codigo}">Excluir</a>
                                    </td>
                                </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div><!-- /#list -->
            <div id="buttom" class="row">
                <div class="col-md-12">
                    <ul class="pagination">
                        <li class="disabled"><a>&lt; Anterior</a></li>
                        <li class="disabled"><a>1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
                    </ul>
                </div>
            </div><!-- /#bottom -->
        </div> <!-- /#main -->
        
        
           
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
                $('.delete').on('click', function(){
                var nome = $(this).data('nome'); // vamos buscar o valor do atributo data-name que temos no botão que foi clicado
                var id = $(this).data('id'); // vamos buscar o valor do atributo data-id
                //$('span.nome').text(nome+ ' (id = ' +id+ ')'); // inserir na o nome na pergunta de confirmação dentro da modal
                $('span.nome').text(nome);
                $('.delete-yes').attr('href', 'bairro?acao=D&id=' +id); // mudar dinamicamente o link, href do botão confirmar da modal
                $('#myModal').modal('show'); // modal aparece
          });
        </script> 
    </body>
</html>
