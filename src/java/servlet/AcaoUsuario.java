/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Usuario;
import beans.Usuario;
import controller.Usuario_Controller;
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
@WebServlet("/usuario")
public class AcaoUsuario extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario_Controller ac = new Usuario_Controller();
        String acao = req.getParameter("acao");
        Usuario usuario = new Usuario();
       
        if(acao.equals("L")){ // L de Listar
            List lista = ac.listarTodos("");
            req.setAttribute("usuarios", lista);
            redirecionar(req, resp, "usuario.jsp");
          
        }else if(acao.equals("P")){ //P de pesquisar
            String nmusuario = req.getParameter("usuario");
            List lista = ac.listarTodos(nmusuario);
            req.setAttribute("usuarios", lista);
            redirecionar(req, resp, "usuario.jsp");
        }
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
