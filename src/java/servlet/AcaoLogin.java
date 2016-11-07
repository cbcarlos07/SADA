/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import controller.Usuario_Controller;
import java.io.IOException;
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
@WebServlet("/logar")
public class AcaoLogin extends HttpServlet{
 public static String login;
 public static int codigo;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        login = req.getParameter("loginname").toUpperCase();
        String senha = req.getParameter("senha").toUpperCase();
        
        Usuario_Controller uc = new Usuario_Controller();
        boolean teste = uc.efetuarLogin(login, senha);
        
        if(teste){
            codigo = uc.getCodigo(login, senha);
            RequestDispatcher rd = req.getRequestDispatcher("/welcome.jsp");
            rd.forward(req, resp);
        }else{
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        }
        
        
        
    }
    
    
}
