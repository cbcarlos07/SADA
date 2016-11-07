/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Aluno;
import beans.Funcionario;
import beans.Matricula;
import controller.Matricula_Controller;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlos.brito
 */
@WebServlet("/matricula")
public class AcaoMatricula extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Matricula_Controller ac = new Matricula_Controller();
        String acao = req.getParameter("acao");
        Matricula matricula = new Matricula();
       
        if(acao.equals("L")){ // L de Listar
            List lista = ac.pesquisar_matricula("");
            req.setAttribute("matriculas", lista);
            redirecionar(req, resp, "matricula.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            
            String inicio = req.getParameter("inicio");
            String vencimento = req.getParameter("vencimento");
            String status = req.getParameter("status").toUpperCase();
            String aluno = req.getParameter("aluno");
            String funcionario = req.getParameter("funcionario");
            
            SimpleDateFormat formato_br = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicio     = null;
            Date dataVencimento = null;
            try {
                 dataInicio     = formato_br.parse(inicio);
                 dataVencimento = formato_br.parse(vencimento);
            } catch (ParseException ex) {
                Logger.getLogger(AcaoMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            
            matricula.setMat_Data_Inicio(dataInicio);
            matricula.setMat_Date_Vencimento(dataVencimento);
            matricula.setMat_Status(status);
            matricula.setMat_Aluno(new Aluno());
            matricula.getMat_Aluno().setAluno_Codigo(Integer.parseInt(aluno));
            matricula.setMat_Funcionario(new Funcionario());
            matricula.getMat_Funcionario().setFuncionario_Codigo(Integer.parseInt(funcionario));
            
            boolean teste = ac.inserir(matricula);
            
            
            if(teste){
                System.out.println("Matricula efetuada com sucesso");
                
                    List lista = ac.pesquisar_matricula("");
                    req.setAttribute("matriculas", lista);
                    redirecionar(req, resp, "matricula.jsp");
                
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String str_matricula = req.getParameter("matricula");
            String inicio = req.getParameter("inicio");
            String vencimento = req.getParameter("vencimento");
            String status = req.getParameter("status").toUpperCase();
            String aluno = req.getParameter("aluno");
            String funcionario = req.getParameter("funcionario");
            
            SimpleDateFormat formato_br = new SimpleDateFormat("dd/MM/yyyy");
            Date dataInicio     = null;
            Date dataVencimento = null;
            try {
                 dataInicio     = formato_br.parse(inicio);
                 dataVencimento = formato_br.parse(vencimento);
            } catch (ParseException ex) {
                Logger.getLogger(AcaoMatricula.class.getName()).log(Level.SEVERE, null, ex);
            }
            
           
            matricula.setMat_Codigo(Integer.parseInt(str_matricula));
            matricula.setMat_Data_Inicio(dataInicio);
            matricula.setMat_Date_Vencimento(dataVencimento);
            matricula.setMat_Status(status);
            matricula.setMat_Aluno(new Aluno());
            matricula.getMat_Aluno().setAluno_Codigo(Integer.parseInt(aluno));
            matricula.setMat_Funcionario(new Funcionario());
            matricula.getMat_Funcionario().setFuncionario_Codigo(Integer.parseInt(funcionario));
            
            boolean teste = ac.alterar(matricula);
            
            
            if(teste ){
                List lista = ac.pesquisar_matricula("");
                req.setAttribute("matriculas", lista);
                redirecionar(req, resp, "matricula.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codmatricula = req.getParameter("id");
            matricula = ac.procurar_codigo(Integer.parseInt(codmatricula));
            
            req.setAttribute("matricula", matricula);
            
            redirecionar(req, resp, "editmatricula.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codmatricula = req.getParameter("id");
            boolean teste = ac.excluir(Integer.parseInt(codmatricula));
            if(teste){
                List lista = ac.pesquisar_matricula("");
                req.setAttribute("matriculas", lista);
                redirecionar(req, resp, "matricula.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmmatricula = req.getParameter("matricula");
            List lista = ac.pesquisar_matricula(nmmatricula);
            req.setAttribute("matriculas", lista);
            redirecionar(req, resp, "matricula.jsp");
        } 
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
