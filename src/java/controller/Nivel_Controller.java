/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Nivel;
import java.util.List;
import model.dao.Nivel_DAO;

/**
 *
 * @author carlos.brito
 */
public class Nivel_Controller {
    Nivel_DAO bd = new Nivel_DAO();
    Nivel nivel = new Nivel();
    List lista;
    public List<Nivel> pesquisar_nivel(String nome){
        lista = bd.pesquisar_nivel(nome);
        return lista;
    }
    
    public boolean inserir(Nivel nivel){
        boolean teste = bd.inserir(nivel);
        return teste;
    }
    
    public boolean alterar(Nivel nivel){
       boolean teste = bd.alterar(nivel);
        return teste; 
    }
    
    public boolean excluir(int codigo){
         boolean teste = bd.excluir(codigo);
        return teste; 
    }
    public Nivel procurar_codigo(int codigo){
        nivel = bd.procurar_codigo(codigo);
        return nivel;
    }
    
}
