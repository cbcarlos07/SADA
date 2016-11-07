/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Atividade;
import java.util.List;
import model.dao.Atividade_DAO;

/**
 *
 * @author carlos.brito
 */
public class Atividade_Controller {
    Atividade_DAO ad = new Atividade_DAO();
    Atividade atividade = new Atividade();
    List lista;
    public List<Atividade> pesquisar_atividade(String nome){
        lista = ad.pesquisar_atividade(nome);
        return lista;
    }
    
    public boolean inserir(Atividade atividade){
        boolean teste = ad.inserir(atividade);
        return teste;
    }
    
    public boolean alterar(Atividade atividade){
       boolean teste = ad.alterar(atividade);
        return teste; 
    }
    
    public boolean excluir(int codigo){
         boolean teste = ad.excluir(codigo);
        return teste; 
    }
    public Atividade procurar_codigo(int codigo){
        atividade = ad.procurar_codigo(codigo);
        return atividade;
    }
    
  
    
}
