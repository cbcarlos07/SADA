/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Turma;
import java.util.List;
import model.dao.Turma_DAO;

/**
 *
 * @author carlos.brito
 */
public class Turma_Controller {
    Turma_DAO bd = new Turma_DAO();
    Turma turma = new Turma();
    List lista;
    public List<Turma> pesquisar_turma(String nome){
        lista = bd.pesquisar_turma(nome);
        return lista;
    }
    
    public boolean inserir(Turma turma){
        boolean teste = bd.inserir(turma);
        return teste;
    }
    
    public boolean alterar(Turma turma){
       boolean teste = bd.alterar(turma);
        return teste; 
    }
    
    public boolean excluir(int codigo){
         boolean teste = bd.excluir(codigo);
        return teste; 
    }
    public Turma procurar_codigo(int codigo){
        turma = bd.procurar_codigo(codigo);
        return turma;
    }
    
}
