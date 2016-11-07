/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Nivel;
import controller.Nivel_Controller;
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
@WebServlet("/nivel")
public class AcaoNivel extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Nivel_Controller nc = new Nivel_Controller();
        String acao = req.getParameter("acao");
        Nivel nivel = new Nivel();
       
        if(acao.equals("L")){ // L de Listar
            
            
            List lista = nc.pesquisar_nivel("");
            req.setAttribute("niveis", lista);
            redirecionar(req, resp, "nivel.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            String nmnivel = req.getParameter("nivel");
            nivel.setNivel_Descricao(nmnivel);
            boolean teste = nc.inserir(nivel);
            if(teste){
               List lista = nc.pesquisar_nivel("");
               req.setAttribute("niveis", lista);
               redirecionar(req, resp, "nivel.jsp");
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String nmnivel = req.getParameter("nivel");
            String codnivel = req.getParameter("id");
            nivel.setNivel_Codigo(Integer.parseInt(codnivel));
            nivel.setNivel_Descricao(nmnivel);            
            boolean teste = nc.alterar(nivel);
            if(teste){
                List lista = nc.pesquisar_nivel("");
                req.setAttribute("niveis", lista);
                redirecionar(req, resp, "nivel.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codnivel = req.getParameter("id");
            nivel = nc.procurar_codigo(Integer.parseInt(codnivel));
            req.setAttribute("nivel", nivel);
            redirecionar(req, resp, "editnivel.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codnivel = req.getParameter("id");
            boolean teste = nc.excluir(Integer.parseInt(codnivel));
            if(teste){
                List lista = nc.pesquisar_nivel("");
                req.setAttribute("niveis", lista);
                redirecionar(req, resp, "nivel.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmbairro = req.getParameter("nivel");
            List lista = nc.pesquisar_nivel(nmbairro);
            req.setAttribute("niveis", lista);
            redirecionar(req, resp, "nivel.jsp");
        }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
