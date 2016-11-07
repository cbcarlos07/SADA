/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Funcionario;
import beans.Usuario;
import controller.Funcionario_Controller;
import controller.Usuario_Controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos.brito
 */
@WebServlet("/funcionario")
public class AcaoFuncionario extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Funcionario_Controller ac = new Funcionario_Controller();
        String acao = req.getParameter("acao");
        Funcionario funcionario = new Funcionario();
       
        if(acao.equals("L")){ // L de Listar
            List lista = ac.pesquisar_funcionario("");
            req.setAttribute("funcionarios", lista);
            redirecionar(req, resp, "funcionario.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            String nome = req.getParameter("nome").toUpperCase();
            String cpf = req.getParameter("cpf");
            String sexo = req.getParameter("sexo");
            String fone = req.getParameter("fone");
            String rua = req.getParameter("rua").toUpperCase();
            String casa = req.getParameter("casa").toUpperCase();
            String email = req.getParameter("email").toUpperCase();
            String bairro = req.getParameter("bairro");
            String login = req.getParameter("login").toUpperCase();
            String senha = req.getParameter("senha");
            funcionario.setFuncionario_Nome(nome);
            funcionario.setFuncionario_Cpf(cpf);
            funcionario.setFuncionario_Sexo(sexo);
            funcionario.setFuncionario_Fone(fone);
            funcionario.setFuncionario_Rua(rua);
            funcionario.setFuncionario_Casa(casa);
            funcionario.setFuncionario_Email(email);
            //JOptionPane.showMessageDialog(null,"Codigo do bairro: "+bairro);
            funcionario.setBairro_Codigo(Integer.parseInt(bairro));
            boolean teste = ac.inserir(funcionario);
            int codigoFuncionario = ac.gerar_codigo();
            System.out.println("Codigo gerado: "+codigoFuncionario);
            if(teste){
                System.out.println("Hora de inserir usuario");
                Usuario usuario = new Usuario();
                usuario.setUsuario_Login(login);
                usuario.setUsuario_Senha(senha);
                usuario.setUsuario_Tipo("1");
                usuario.setUsuario_Funcionario(codigoFuncionario);
                //usuario.setUsuario_Funcionario(null);
                Usuario_Controller uc = new Usuario_Controller();
                //JOptionPane.showMessageDialog(null, "Salvar usuario");
                boolean teste1 = uc.inserirUserFunc(usuario);
                if(teste1){
                    List lista = ac.pesquisar_funcionario("");
                    req.setAttribute("funcionarios", lista);
                    redirecionar(req, resp, "funcionario.jsp");
                }
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String codigo = req.getParameter("codigo");
            String nome = req.getParameter("nome").toUpperCase();
            String cpf = req.getParameter("cpf");
            String sexo = req.getParameter("sexo");
            String fone = req.getParameter("fone");
            String rua = req.getParameter("rua").toUpperCase();
            String casa = req.getParameter("casa").toUpperCase();
            String email = req.getParameter("email").toUpperCase();
            String bairro = req.getParameter("bairro");
            
            funcionario.setFuncionario_Codigo(Integer.parseInt(codigo));
            funcionario.setFuncionario_Nome(nome);
            funcionario.setFuncionario_Cpf(cpf);
            funcionario.setFuncionario_Sexo(sexo);
            funcionario.setFuncionario_Fone(fone);
            funcionario.setFuncionario_Rua(rua);
            funcionario.setFuncionario_Casa(casa);
            funcionario.setFuncionario_Email(email);
            funcionario.setBairro_Codigo(Integer.parseInt(bairro));
            boolean teste = ac.alterar(funcionario);
            
            
            if(teste ){
                List lista = ac.pesquisar_funcionario("");
                req.setAttribute("funcionarios", lista);
                redirecionar(req, resp, "funcionario.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codfuncionario = req.getParameter("id");
            funcionario = ac.procurar_codigo(Integer.parseInt(codfuncionario));
            
            Usuario usuario = new Usuario();
            Usuario_Controller uc = new Usuario_Controller();
            usuario = uc.pesquisarUserFunc(Integer.parseInt(codfuncionario));
            req.setAttribute("funcionario", funcionario);
            req.setAttribute("usuario", usuario.getUsuario_Login());
            redirecionar(req, resp, "editfuncionario.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codfuncionario = req.getParameter("id");
            boolean teste = ac.excluir(Integer.parseInt(codfuncionario));
            if(teste){
                List lista = ac.pesquisar_funcionario("");
                req.setAttribute("funcionarios", lista);
                redirecionar(req, resp, "funcionario.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmfuncionario = req.getParameter("funcionario");
            List lista = ac.pesquisar_funcionario(nmfuncionario);
            req.setAttribute("funcionarios", lista);
            redirecionar(req, resp, "funcionario.jsp");
        }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
