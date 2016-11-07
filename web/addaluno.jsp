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
            <center><h1>Cadastrar Aluno</h1></center>
            <form action="aluno" method="post">
                <input type="hidden" value="I" name="acao"/>
                <div class="col-lg-offset-3">
                <div class="form-group col-lg-10 ">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" class="form-control" required="">
                </div>
                
                <div class="form-group col-lg-4 ">
                    <label for="cpf">CPF</label>
                    <input type="text" id="cpf" name="cpf" class="form-control" required="" id="cpf" onblur="javascript: validarCPF(this.value);" onkeypress="javascript: mascara(this, cpf_mask);"  maxlength="14">
                </div>
                
                <div class="form-group col-lg-4 ">
                   <label for="sexo">Sexo</label>
                   <select name="sexo" class="form-control">
                           <option value="M">Masculino</option>  
                           <option value="F">Feminino</option>  
                   </select>
                </div>
                <div class="form-group col-lg-11 "></div>
                <div class="form-group col-lg-4 ">
                    <label for="fone">Fone</label>
                    <input id="fone" name="fone" class="form-control" required=""  >
                </div>
              
                <div class="form-group col-lg-11 "></div>
                <div class="form-group col-lg-8 ">
                    <label for="rua">Rua</label>
                    <input type="text" id="rua" name="rua" class="form-control" required="">
                </div>
                
                <div class="form-group col-lg-2 ">
                    <label for="casa">Casa</label>
                    <input type="text" id="casa" name="casa" class="form-control" required="">
                </div>
                
                <div class="form-group col-lg-4 ">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" class="form-control" required="">
                </div>
                
                <div class="form-group col-lg-4 ">
                    <label for="bairro">Bairro</label>
                     <jsp:useBean id="bc" class="controller.Bairro_Controller" />
                     
                    <select name="bairro" class="form-control">
                        <c:forEach var="bairro" items="${bc.pesquisar_bairro('')}" >     
                            <option value="${bairro.bairro_Codigo}"> ${bairro.bairro_Descricao}</option>  
                        </c:forEach>
                    </select>
                </div>
               <div class="form-group col-lg-10 "><center><span><b>Informações de Login</b></span></center></div>     
                  <hr />
                 <div class="form-group col-lg-4 ">
                    <label for="login">Login</label>
                    <input type="text" id="login" name="login" class="form-control" required="">
                </div>
                 
                 <div class="form-group col-lg-3 ">
                    <label for="senha">Senha</label>
                    <input type="password" id="senha" name="senha" class="form-control" required="">
                </div>
                  <div class="form-group col-lg-3 ">
                    <label for="senha">Confirme a Senha</label>
                    <input type="password" id="confirmsenha" name="senha" class="form-control" required=""
                           data-match="#senha" data-match-error="Atenção! As senhas não estão iguais.">
                    <div class="help-block with-errors"></div>
                </div>
                <div class="btn-group col-lg-8">
                    <button type="submit" class="btn btn-success">Salvar</button><button type="reset" class="btn btn-primary">Limpar</button>
                </div>
                </div>
                 <br>    
                  
                 <hr />    
            </form>
            
        </div> <!-- /#main -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
        <script>
         function validarCPF( cpf ){
                        var filtro = /^\d{3}.\d{3}.\d{3}-\d{2}$/i;

                        if(!filtro.test(cpf))
                        {
                                window.alert("CPF inválido. Tente novamente.");
                                return false;
                        }

                        cpf = remove(cpf, ".");
                        cpf = remove(cpf, "-");

                        if(cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" ||
                                cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" ||
                                cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" ||
                                cpf == "88888888888" || cpf == "99999999999")
                        {
                                window.alert("CPF inválido. Tente novamente.");
                                return false;
                   }

                        soma = 0;
                        for(i = 0; i < 9; i++)
                        {
                                soma += parseInt(cpf.charAt(i)) * (10 - i);
                        }

                        resto = 11 - (soma % 11);
                        if(resto == 10 || resto == 11)
                        {
                                resto = 0;
                        }
                        if(resto != parseInt(cpf.charAt(9))){
                                window.alert("CPF inválido. Tente novamente.");
                                return false;
                        }

                        soma = 0;
                        for(i = 0; i < 10; i ++)
                        {
                                soma += parseInt(cpf.charAt(i)) * (11 - i);
                        }
                        resto = 11 - (soma % 11);
                        if(resto == 10 || resto == 11)
                        {
                                resto = 0;
                        }

                        if(resto != parseInt(cpf.charAt(10))){
                                window.alert("CPF inválido. Tente novamente.");
                                $('input[name="cpf"]').css("border-color","red").focus();
                                return false;
                        }

                        return true;
                 }

                function remove(str, sub) {
                        i = str.indexOf(sub);
                        r = "";
                        if (i == -1) return str;
                        {
                                r += str.substring(0,i) + remove(str.substring(i + sub.length), sub);
                        }

                        return r;
                }

                /**
                   * MASCARA ( mascara(o,f) e execmascara() ) CRIADAS POR ELCIO LUIZ
                   * elcio.com.br
                   */
                function mascara(o,f){
                        v_obj=o
                        v_fun=f
                        setTimeout("execmascara()",1)
                }

                function execmascara(){
                        v_obj.value=v_fun(v_obj.value)
                }

                function cpf_mask(v){
                        v=v.replace(/\D/g,"")                 //Remove tudo o que não é dígito
                        v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o terceiro e o quarto dígitos
                        v=v.replace(/(\d{3})(\d)/,"$1.$2")    //Coloca ponto entre o setimo e o oitava dígitos
                        v=v.replace(/(\d{3})(\d)/,"$1-$2")   //Coloca ponto entre o decimoprimeiro e o decimosegundo dígitos
                        return v
                }

        </script>
        
    </body>
</html>
