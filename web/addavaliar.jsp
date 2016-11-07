<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/" prefix="sada" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="ftm" %>
<html>
    <head>
        <title>Avaliar - SADA</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/jquery.datetimepicker.min.css">
        <link href="css/style.css" rel="stylesheet">
        <link href="img/malha.ico" rel="short icon">
        <script>
            function imc(){
                //alert('Teste');
                var peso = document.getElementById('peso').value;
                var altura = document.getElementById('altura').value.replace(',','.');
                var imc_ = document.getElementById('imc');
                var imc1 = peso / (altura * altura);
                var result = parseFloat(imc1.toFixed(2));
                console.log('Imc: '+parseFloat(imc1.toFixed(2)));
                imc_.value = result.toString().replace('.',','); 
            }
            function iac(){
                //alert('Teste');
                var peso = document.getElementById('peso').value;
                var altura = document.getElementById('altura').value.replace(',','.');
                var quadril = document.getElementById('quadril').value.replace(',','.');
                var iac_ = document.getElementById('iac');
                var iac1 = quadril / (altura * (Math.sqrt(altura))) - 18;
                var result = parseFloat(iac1.toFixed(2));
                console.log('Raiz quadrada de 4: '+Math.sqrt(4));
                console.log('IAC: '+parseFloat(iac1.toFixed(2)));
                iac_.value = result.toString().replace('.',',') + " %"; 
            }
            
        </script>
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
           
            <center><h1>Avaliar Aluno</h1><p>Matr&iacute;cula: ${param.mat}</p></center>
             <hr />
             <form action="avaliacao" method="post">
             <div class="col-lg-offset-2">
                 
                <input type="hidden" value="I" name="acao"/>
                <div class="col-lg-12">
                <div class="form-group col-lg-2 ">
                    <label for="data">Data</label>
                    <jsp:useBean id="hoje" class="java.util.Date" />
                    <fmt:formatDate var="now" value="${hoje}" pattern="dd/MM/yyyy" />
                    <input type="text" id="data" name="data" class="form-control" required="" value="${now}">
                </div>
                <div class="form-group col-lg-2 ">
                    <label for="altura">Altura</label>
                    <input type="text" id="altura" name="altura" class="form-control" required="">
                </div>
                <div class="form-group col-lg-1">
                    <label for="peso">Peso</label>
                    <input type="text" id="peso" name="peso" class="form-control" required="" >
                </div>
                </div>
                <div class="col-lg-12">    
                <div class="form-group col-lg-2 ">
                    <label for="pressao_max">Press&atilde;o (Max)</label>
                    <input type="text" id="pressao_max" name="pressao_max" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2">
                    <label for="pressao_min">Press&atilde;o (Min)</label>
                    <input type="text" id="pressao_min" name="pressao_min" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2">
                    <label for="braco">Bra&ccedil;o</label>
                    <input type="text" id="braco" name="braco" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2">
                    <label for="torax">Torax</label>
                    <input type="text" id="torax" name="torax" class="form-control" required="" >
                </div>
                    </div>
                <div class="col-lg-12">
                <div class="form-group col-lg-2 ">
                    <label for="cintura">Cintura</label>
                    <input type="text" id="cintura" name="cintura" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2 ">
                    <label for="abdomen">Abdomen</label>
                    <input type="text" id="abdomen" name="abdomen" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2 ">
                    <label for="quadril">Quadril</label>
                    <input type="text" id="quadril" name="quadril" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2">
                    <label for="panturrilha">Panturrilha</label>
                    <input type="text" id="panturrilha" name="panturrilha" class="form-control" required="" >
                </div>
                </div>
                <div class="col-lg-12">
                <div class="form-group col-lg-2">
                    <label for="culotes">Culotes</label>
                    <input type="text" id="culotes" name="culotes" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-3">
                    <label for="batimento_inicial">Batimentos (Inicial)</label>
                    <input type="text" id="batimentos_inicial" name="batimento_inicial" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-3">
                    <label for="batimento_final">Batimentos (Final)</label>
                    <input type="text" id="batimentos_final" name="batimento_final" class="form-control" required="" >
                </div>
                <div class="form-group col-lg-2">
                    <label for="coxa">Coxa</label>
                    <input type="text" id="coxa" name="coxa" class="form-control" required="" >
                </div>
                    </div> 
                </div> <!-- fim do offset -->
                <hr />
                <div class="col-lg-offset-2">
                    <div class="col-lg-12">
                        <div class="form-group col-lg-2">
                            <a href="#div" class="btn btn-primary" onclick="imc()">Calcular IMC</a>
                        </div>
                        <div class="form-group col-lg-2">
                            <a href="#div" class="btn btn-primary" onclick="iac()">Calcular IAC</a>
                        </div>
                    </div>
                    <div class="col-lg-12">
                        <div class="form-group col-lg-2">
                            <label for="img">IMC</label>
                            <input type="text" id="imc" name="imc" class="form-control"  >
                        </div>
                        <div class="form-group col-lg-2">
                            <label for="img">IAC</label>
                            <input type="text" id="iac" name="iac" class="form-control" required="" >
                        </div>
                    </div>    
                </div>
                <hr />
                <div class="col-lg-offset-2">
                <div class=" col-lg-12 ">
                <div class="form-group col-lg-4">
                    <label for="instrutor">Instrutor</label>
                    <jsp:useBean id="fc" class="controller.Funcionario_Controller" />
                    <select name="funcionario" id="instrutor" class="form-control">
                        <c:forEach var="funcionario" items="${fc.pesquisar_funcionario('')}" >
                            <option value="${funcionario.funcionario_Codigo}">${funcionario.funcionario_Nome}</option>
                        </c:forEach>
                            
                    </select>
                </div>
                
              
                <div class="form-group col-lg-2 row">
                    <label for="matricula">Matr&iacute;cula</label>
                    <input type="text" id="matricula" name="matricula" class="form-control" required="" value="${param.mat}">
                </div>
                </div>
               <br><br><br><br><br><br><br><br><br>
                <div class="btn-group col-lg-8">
                    <button type="submit" class="btn btn-success">Salvar</button><button type="reset" class="btn btn-primary">Limpar</button>
                </div>
              
                  </div> <!-- fim do offset -->
            </form>
            </div> <!-- /#main -->
             
        
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.datetimepicker.full.js"></script>
        <script>
            
            
           $("#inicio").datetimepicker({
                timepicker: false,
                format: 'd/m/Y',
                mask: true        
            });
            $("#vencimento").datetimepicker({
                timepicker: false,
                format: 'd/m/Y',
                mask: true        
            });
            $.datetimepicker.setLocale('pt-BR');
            
            
        </script>
    </body>
</html>
