/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Aluno;
import java.util.List;
import model.dao.Aluno_DAO;

/**
 *
 * @author carlos.brito
 */
public class Aluno_Controller {
    Aluno_DAO ad = new Aluno_DAO();
    Aluno aluno = new Aluno();
    List lista;
    public List<Aluno> pesquisar_aluno(String nome){
        lista = ad.pesquisar_aluno(nome);
        return lista;
    }
    
    public boolean inserir(Aluno aluno){
        boolean teste = ad.inserir(aluno);
        return teste;
    }
    
    public boolean alterar(Aluno aluno){
       boolean teste = ad.alterar(aluno);
        return teste; 
    }
    
    public boolean excluir(int codigo){
         boolean teste = ad.excluir(codigo);
        return teste; 
    }
    public Aluno procurar_codigo(int codigo){
        aluno = ad.procurar_codigo(codigo);
        return aluno;
    }
    public Integer gerar_codigo(){
        int i = ad.gerar_codigo();
        return i;
    }
  
    
}
