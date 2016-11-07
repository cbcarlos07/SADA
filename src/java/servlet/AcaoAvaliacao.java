/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Aluno;
import beans.Funcionario;
import beans.Avaliacao;
import beans.Matricula;
import controller.Avaliacao_Controller;
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
@WebServlet("/avaliacao")
public class AcaoAvaliacao extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Avaliacao_Controller ac = new Avaliacao_Controller();
        String acao = req.getParameter("acao");
        Avaliacao avaliacao = new Avaliacao();
       
        if(acao.equals("L")){ // L de Listar
            List lista = ac.pesquisar_avaliacao("");
            req.setAttribute("avaliacao", lista);
            redirecionar(req, resp, "avaliacao.jsp");
        }else if(acao.equals("I")){ //I DE INSERIR
            
            //String data = req.getParameter("data"); // a data é automatica do banco
            String altura = req.getParameter("altura").replace(",", ".");
            String peso = req.getParameter("peso").replace(",", ".");
            String pressao_max = req.getParameter("pressao_max");
            String pressao_min = req.getParameter("pressao_min");
            String braco = req.getParameter("braco");
            String torax = req.getParameter("torax").replace(",", ".");
            String cintura = req.getParameter("cintura").replace(",", ".");
            String abdomen = req.getParameter("abdomen").replace(",", ".");
            String quadril = req.getParameter("quadril").replace(",", ".");
            String panturrilha = req.getParameter("panturrilha").replace(",", ".");
            String culotes = req.getParameter("culotes").replace(",", ".");
            String batimento_inicial = req.getParameter("batimento_inicial");
            String batimento_final = req.getParameter("batimento_final");
            String coxa = req.getParameter("coxa").replace(",", ".");
            String funcionario = req.getParameter("funcionario");
            String matricula = req.getParameter("matricula");
       /*     
            SimpleDateFormat formato_br = new SimpleDateFormat("dd/MM/yyyy");
            Date data_avaliacao     = null;
            try {
                 data_avaliacao     = formato_br.parse(data);
                 
            } catch (ParseException ex) {
                Logger.getLogger(AcaoAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
            }
         */   
           
                System.out.println("Funcionario: "+funcionario);
                avaliacao.setAvaliacao_Peso(Double.parseDouble(peso));
                avaliacao.setAvaliacao_Altura(Double.parseDouble(altura));
                avaliacao.setAvaliacao_Pressao_Max(Integer.parseInt(pressao_max));
                avaliacao.setAvaliacao_Pressao_Min(Integer.parseInt(pressao_min));
                avaliacao.setAvaliacao_Braco(Integer.parseInt(braco));
                avaliacao.setAvaliacao_Torax(Double.parseDouble(torax));
                avaliacao.setAvaliacao_Cintura(Double.parseDouble(cintura));
                avaliacao.setAvaliacao_Abdomen(Double.parseDouble(abdomen));
                avaliacao.setAvaliacao_Quadril(Double.parseDouble(quadril));
                avaliacao.setAvaliacao_Panturrilha(Double.parseDouble(panturrilha));
                avaliacao.setAvaliacao_Culotes(Double.parseDouble(culotes));
                avaliacao.setAvaliacao_Bat_Inicial(Integer.parseInt(batimento_inicial));
                avaliacao.setAvaliacao_Bat_Final(Integer.parseInt(batimento_final));
                avaliacao.setAvaliacao_Coxa(Double.parseDouble(coxa));
                avaliacao.setAvaliacao_Funcionaio(new Funcionario());
                avaliacao.getAvaliacao_Funcionario().setFuncionario_Codigo(Integer.parseInt(funcionario));
                avaliacao.setAvaliacao_Matricula(new Matricula());
                avaliacao.getAvaliacao_Matricula().setMat_Codigo(Integer.parseInt(matricula));
                
            
            boolean teste = ac.inserir(avaliacao);
            
            
            if(teste){
                System.out.println("Avaliacao efetuada com sucesso");
                
                    List lista = ac.pesquisar_avaliacao("");
                    req.setAttribute("avaliacao", lista);
                    redirecionar(req, resp, "avaliacao.jsp");
                
            }
        }else if(acao.equals("E")){ //E DE EDITAR
            String codigo = req.getParameter("codigo");
            String data = req.getParameter("data"); 
            String altura = req.getParameter("altura");
            String peso = req.getParameter("peso");
            String pressao_max = req.getParameter("pressao_maxs");
            String pressao_min = req.getParameter("pressao_min");
            String braco = req.getParameter("braco");
            String torax = req.getParameter("torax");
            String cintura = req.getParameter("cintura");
            String abdomen = req.getParameter("abdomen");
            String quadril = req.getParameter("quadril");
            String panturrilha = req.getParameter("panturrilha");
            String culotes = req.getParameter("culotes");
            String batimento_inicial = req.getParameter("batimento_inicial");
            String batimento_final = req.getParameter("batimento_final");
            String coxa = req.getParameter("coxa");
            String funcionario = req.getParameter("funcionario");
            String matricula = req.getParameter("matricula");
            
            SimpleDateFormat formato_br = new SimpleDateFormat("dd/MM/yyyy");
            Date data_avaliacao     = null;
            try {
                 data_avaliacao     = formato_br.parse(data);
                 
            } catch (ParseException ex) {
                Logger.getLogger(AcaoAvaliacao.class.getName()).log(Level.SEVERE, null, ex);
            }
                avaliacao.setAvaliacao_Codigo(Integer.parseInt(codigo));
                avaliacao.setAvaliacao_Data(data_avaliacao);
                avaliacao.setAvaliacao_Peso(Double.parseDouble(peso));
                avaliacao.setAvaliacao_Altura(Double.parseDouble(altura));
                avaliacao.setAvaliacao_Pressao_Max(Integer.parseInt(pressao_max));
                avaliacao.setAvaliacao_Pressao_Min(Integer.parseInt(pressao_min));
                avaliacao.setAvaliacao_Braco(Integer.parseInt(braco));
                avaliacao.setAvaliacao_Torax(Double.parseDouble(torax));
                avaliacao.setAvaliacao_Cintura(Double.parseDouble(cintura));
                avaliacao.setAvaliacao_Abdomen(Double.parseDouble(abdomen));
                avaliacao.setAvaliacao_Quadril(Double.parseDouble(quadril));
                avaliacao.setAvaliacao_Panturrilha(Double.parseDouble(panturrilha));
                avaliacao.setAvaliacao_Culotes(Double.parseDouble(culotes));
                avaliacao.setAvaliacao_Bat_Inicial(Integer.parseInt(batimento_inicial));
                avaliacao.setAvaliacao_Bat_Final(Integer.parseInt(batimento_final));
                avaliacao.setAvaliacao_Coxa(Double.parseDouble(coxa));
                avaliacao.setAvaliacao_Funcionaio(new Funcionario());
                avaliacao.getAvaliacao_Funcionario().setFuncionario_Codigo(Integer.parseInt(funcionario));
                avaliacao.setAvaliacao_Matricula(new Matricula());
                avaliacao.getAvaliacao_Matricula().setMat_Codigo(Integer.parseInt(matricula));
                
            
            boolean teste = ac.alterar(avaliacao);
            
            
            if(teste ){
                List lista = ac.pesquisar_avaliacao("");
                req.setAttribute("avaliacaos", lista);
                redirecionar(req, resp, "avaliacao.jsp");
            }
            
        }else if(acao.equals("B")){//B de Buscar
            String codavaliacao = req.getParameter("id");
            avaliacao = ac.procurar_codigo(Integer.parseInt(codavaliacao));
            
            req.setAttribute("avaliacao", avaliacao);
            
            redirecionar(req, resp, "editavaliacao.jsp");
            
        }
        else if(acao.equals("D")){//D de DELETAR
            String codavaliacao = req.getParameter("id");
            boolean teste = ac.excluir(Integer.parseInt(codavaliacao));
            if(teste){
                List lista = ac.pesquisar_avaliacao("");
                req.setAttribute("avaliacaos", lista);
                redirecionar(req, resp, "avaliacao.jsp");
            }
            
            
        }else if(acao.equals("P")){ //P de pesquisar
            String nmavaliacao = req.getParameter("avaliacao");
            List lista = ac.pesquisar_avaliacao(nmavaliacao);
            req.setAttribute("avaliacaos", lista);
            redirecionar(req, resp, "avaliacao.jsp");
        }else if(acao.equals("C")){
            String matricula = req.getParameter("matricula");
            req.setAttribute("matricula", matricula);
            redirecionar(req, resp, "addavaliacao.jsp");
        } 
    }
    
    private void redirecionar(HttpServletRequest req, HttpServletResponse resp, String pagina) throws ServletException, IOException{
         RequestDispatcher rd = req.getRequestDispatcher(pagina);
         rd.forward(req, resp);
    }
}
