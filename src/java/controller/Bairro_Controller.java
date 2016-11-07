/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Bairro;
import java.util.List;
import model.dao.Bairro_DAO;

/**
 *
 * @author carlos.brito
 */
public class Bairro_Controller {
    Bairro_DAO bd = new Bairro_DAO();
    Bairro bairro = new Bairro();
    List lista;
    public List<Bairro> pesquisar_bairro(String nome){
        lista = bd.pesquisar_bairro(nome);
        return lista;
    }
    
    public boolean inserir(Bairro bairro){
        boolean teste = bd.inserir(bairro);
        return teste;
    }
    
    public boolean alterar(Bairro bairro){
       boolean teste = bd.alterar(bairro);
        return teste; 
    }
    
    public boolean excluir(int codigo){
         boolean teste = bd.excluir(codigo);
        return teste; 
    }
    public Bairro procurar_codigo(int codigo){
        bairro = bd.procurar_codigo(codigo);
        return bairro;
    }
    
  
    
}
