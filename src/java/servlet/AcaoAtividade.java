/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Atividade;
import controller.Atividade_Controller;
import java.io.IOException;
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
@WebServlet("/atividade")
public class AcaoAtividade extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Atividade_Controller ac = new Atividade_Controller();
        String acao = req.getParameter("acao");
        Atividade atividade = new Atividade();
       
        if(acao.equals("L")){ // L de Listar
            List lista = ac.pesquisar_atividade("");
            req.setAttribute("atividades", lista);
            redirecionar(req, resp, "atividade.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            String nome = req.getParameter("nome");
            String descricao = req.getParameter("descricao");
            String nivel = req.getParameter("nivel");
            atividade.setAtividade_Nome(nome);
            atividade.setAtividade_Descricao(descricao);
            atividade.setNivel_Codigo(Integer.parseInt(nivel));
            boolean teste = ac.inserir(atividade);
            if(teste){
               List lista = ac.pesquisar_atividade("");
               req.setAttribute("atividades", lista);
               redirecionar(req, resp, "atividade.jsp");
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String nome = req.getParameter("nome");
            String descricao = req.getParameter("descricao");
            String nivel = req.getParameter("nivel");
            String codigo = req.getParameter("codigo");
            atividade.setAtividade_Codigo(Integer.parseInt(codigo));
            atividade.setAtividade_Nome(nome);
            atividade.setAtividade_Descricao(descricao);
            atividade.setNivel_Codigo(Integer.parseInt(nivel));   
            boolean teste = ac.alterar(atividade);
            if(teste){
                List lista = ac.pesquisar_atividade("");
                req.setAttribute("atividades", lista);
                redirecionar(req, resp, "atividade.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codatividade = req.getParameter("id");
            atividade = ac.procurar_codigo(Integer.parseInt(codatividade));
            req.setAttribute("atividade", atividade);
            redirecionar(req, resp, "editatividade.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codatividade = req.getParameter("id");
            boolean teste = ac.excluir(Integer.parseInt(codatividade));
            if(teste){
                List lista = ac.pesquisar_atividade("");
                req.setAttribute("atividades", lista);
                redirecionar(req, resp, "atividade.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmatividade = req.getParameter("atividade");
            List lista = ac.pesquisar_atividade(nmatividade);
            req.setAttribute("atividades", lista);
            redirecionar(req, resp, "atividade.jsp");
        }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
