/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Aluno;
import beans.Usuario;
import controller.Aluno_Controller;
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
@WebServlet("/aluno")
public class AcaoAluno extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Aluno_Controller ac = new Aluno_Controller();
        String acao = req.getParameter("acao");
        Aluno aluno = new Aluno();
       
        if(acao.equals("L")){ // L de Listar
            List lista = ac.pesquisar_aluno("");
            req.setAttribute("alunos", lista);
            redirecionar(req, resp, "aluno.jsp");
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
            aluno.setAluno_Nome(nome);
            aluno.setAluno_Cpf(cpf);
            aluno.setAluno_Sexo(sexo);
            aluno.setAluno_Fone(fone);
            aluno.setAluno_Rua(rua);
            aluno.setAluno_Casa(casa);
            aluno.setAluno_Email(email);
            //JOptionPane.showMessageDialog(null,"Codigo do bairro: "+bairro);
            aluno.setBairro_Codigo(Integer.parseInt(bairro));
            boolean teste = ac.inserir(aluno);
            int codigoAluno = ac.gerar_codigo();
            System.out.println("Codigo gerado: "+codigoAluno);
            if(teste){
                System.out.println("Hora de inserir usuario");
                Usuario usuario = new Usuario();
                usuario.setUsuario_Login(login);
                usuario.setUsuario_Senha(senha);
                usuario.setUsuario_Tipo("1");
                usuario.setUsuario_Aluno(codigoAluno);
                System.out.println("Variaveis de usuarios setadas. ");
                //usuario.setUsuario_Funcionario(null);
                Usuario_Controller uc = new Usuario_Controller();
                //JOptionPane.showMessageDialog(null, "Salvar usuario");
                boolean teste1 = uc.inserirUserAluno(usuario);
                if(teste1){
                    List lista = ac.pesquisar_aluno("");
                    req.setAttribute("alunos", lista);
                    redirecionar(req, resp, "aluno.jsp");
                }
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String codigo = req.getParameter("codigo");
            String nome = req.getParameter("nome").toUpperCase();
            String cpf = req.getParameter("cpf");
            String sexo = req.getParameter("sexo");
            String fone = req.getParameter("fone");
            String rua = req.getParameter("rua");
            String casa = req.getParameter("casa");
            String email = req.getParameter("email");
            String bairro = req.getParameter("bairro");
            
            aluno.setAluno_Codigo(Integer.parseInt(codigo));
            aluno.setAluno_Nome(nome);
            aluno.setAluno_Cpf(cpf);
            aluno.setAluno_Sexo(sexo);
            aluno.setAluno_Fone(fone);
            aluno.setAluno_Rua(rua);
            aluno.setAluno_Casa(casa);
            aluno.setAluno_Email(email);
            aluno.setBairro_Codigo(Integer.parseInt(bairro));
            boolean teste = ac.alterar(aluno);
            
            
            if(teste ){
                List lista = ac.pesquisar_aluno("");
                req.setAttribute("alunos", lista);
                redirecionar(req, resp, "aluno.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codaluno = req.getParameter("id");
            aluno = ac.procurar_codigo(Integer.parseInt(codaluno));
            
            Usuario usuario = new Usuario();
            Usuario_Controller uc = new Usuario_Controller();
            usuario = uc.pesquisarUserAluno(Integer.parseInt(codaluno));
            req.setAttribute("aluno", aluno);
            req.setAttribute("usuario", usuario.getUsuario_Login());
            redirecionar(req, resp, "editaluno.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codaluno = req.getParameter("id");
            boolean teste = ac.excluir(Integer.parseInt(codaluno));
            if(teste){
                List lista = ac.pesquisar_aluno("");
                req.setAttribute("alunos", lista);
                redirecionar(req, resp, "aluno.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmaluno = req.getParameter("aluno");
            List lista = ac.pesquisar_aluno(nmaluno);
            req.setAttribute("alunos", lista);
            redirecionar(req, resp, "aluno.jsp");
        }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
