/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Atividade;
import beans.Funcionario;
import beans.Turma;
import controller.Turma_Controller;
import java.io.IOException;
import java.sql.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
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
@WebServlet("/turma")
public class AcaoTurma extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Turma_Controller nc = new Turma_Controller();
        String acao = req.getParameter("acao");
        Turma turma = new Turma();
       
        if(acao.equals("L")){ // L de Listar
            
            
            List lista = nc.pesquisar_turma("");
            req.setAttribute("turma", lista);
            redirecionar(req, resp, "turma.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            String hora = req.getParameter("hora");
            String obs = req.getParameter("obs");
            String date = req.getParameter("data");
            String descricao = req.getParameter("descricao");
            String funcionario = req.getParameter("funcionario");
            String atividade = req.getParameter("atividade");
            System.out.println("Hora  recuperada: "+hora);
            System.out.println("Data recuperada: "+date);
            Time hora1 = null;
            Date data1 =  null;
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                hora1 = new java.sql.Time(sdf.parse(hora+":00").getTime());
                 System.out.println("hORA convertida de String para util.Date: "+hora1);
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                data1 = (Date) sdf1.parse(date);
            }catch(ParseException e){
                e.printStackTrace();
            }
            
            System.out.println("Codigo do funcionario: "+funcionario);
            turma.setTurma_Horario(hora1);
            turma.setTurma_Observacao(obs);
            turma.setTurma_Data(data1);
            turma.setTurma_Descricao(descricao);
            turma.setFuncionario(new Funcionario());
            turma.getFuncionario().setFuncionario_Codigo(Integer.parseInt(funcionario));
            turma.setAtividade(new Atividade());
            turma.getAtividade().setAtividade_Codigo(Integer.parseInt(atividade));
            boolean teste = nc.inserir(turma);
            if(teste){
               List lista = nc.pesquisar_turma("");
               req.setAttribute("turma", lista);
               redirecionar(req, resp, "turma.jsp");
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String codigo = req.getParameter("codigo");
            String hora = req.getParameter("hora");
            String obs = req.getParameter("obs");
            String date = req.getParameter("data");
            String descricao = req.getParameter("descricao");
            String funcionario = req.getParameter("funcionario");
            String atividade = req.getParameter("atividade");
            System.out.println("Hora  recuperada: "+hora);
            System.out.println("Data recuperada: "+date);
            Time hora1 = null;
            Date data1 =  null;
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                hora1 = new java.sql.Time(sdf.parse(hora+":00").getTime());
                 System.out.println("hORA convertida de String para util.Date: "+hora1);
                SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
                data1 = (Date) sdf1.parse(date);
            }catch(ParseException e){
                e.printStackTrace();
            }
            
            System.out.println("Codigo do funcionario: "+funcionario);
            turma.setTurma_Codigo(Integer.parseInt(codigo));
            turma.setTurma_Horario(hora1);
            turma.setTurma_Observacao(obs);
            turma.setTurma_Data(data1);
            turma.setTurma_Descricao(descricao);
            turma.setFuncionario(new Funcionario());
            turma.getFuncionario().setFuncionario_Codigo(Integer.parseInt(funcionario));
            turma.setAtividade(new Atividade());
            turma.getAtividade().setAtividade_Codigo(Integer.parseInt(atividade));
            boolean teste = nc.alterar(turma);
            if(teste){
                List lista = nc.pesquisar_turma("");
                req.setAttribute("turma", lista);
                redirecionar(req, resp, "turma.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codturma = req.getParameter("id");
            turma = nc.procurar_codigo(Integer.parseInt(codturma));
            req.setAttribute("turma", turma);
            redirecionar(req, resp, "editturma.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codturma = req.getParameter("id");
            boolean teste = nc.excluir(Integer.parseInt(codturma));
            if(teste){
                List lista = nc.pesquisar_turma("");
                req.setAttribute("turma", lista);
                redirecionar(req, resp, "turma.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmbairro = req.getParameter("turma");
            List lista = nc.pesquisar_turma(nmbairro);
            req.setAttribute("turma", lista);
            redirecionar(req, resp, "turma.jsp");
        }
        else if(acao.equals("C")){ //C de Tela de Cadastro
            
            
            req.setAttribute("user", AcaoLogin.codigo);
            redirecionar(req, resp, "addturma.jsp");
        }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
