/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Bairro;
import controller.Bairro_Controller;
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
@WebServlet("/bairro")
public class AcaoBairro extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bairro_Controller bc = new Bairro_Controller();
        String acao = req.getParameter("acao");
        Bairro bairro = new Bairro();
       
        if(acao.equals("L")){ // L de Listar
            
            
            List lista = bc.pesquisar_bairro("");
            req.setAttribute("bairros", lista);
            redirecionar(req, resp, "bairros.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            String nmbairro = req.getParameter("bairro");
            bairro.setBairro_Descricao(nmbairro);
            boolean teste = bc.inserir(bairro);
            if(teste){
               List lista = bc.pesquisar_bairro("");
               req.setAttribute("bairros", lista);
               redirecionar(req, resp, "bairros.jsp");
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String nmbairro = req.getParameter("bairro");
            String codbairro = req.getParameter("id");
            bairro.setBairro_Codigo(Integer.parseInt(codbairro));
            bairro.setBairro_Descricao(nmbairro);            
            boolean teste = bc.alterar(bairro);
            if(teste){
                List lista = bc.pesquisar_bairro("");
                req.setAttribute("bairros", lista);
                redirecionar(req, resp, "bairros.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codbairro = req.getParameter("id");
            bairro = bc.procurar_codigo(Integer.parseInt(codbairro));
            req.setAttribute("bairro", bairro);
            redirecionar(req, resp, "editbairro.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codbairro = req.getParameter("id");
            boolean teste = bc.excluir(Integer.parseInt(codbairro));
            if(teste){
                List lista = bc.pesquisar_bairro("");
                req.setAttribute("bairros", lista);
                redirecionar(req, resp, "bairros.jsp");
            }  
        }else if(acao.equals("P")){ //P de pesquisar
            String nmbairro = req.getParameter("bairro");
            List lista = bc.pesquisar_bairro(nmbairro);
            req.setAttribute("bairros", lista);
            redirecionar(req, resp, "bairros.jsp");
        }
        }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
