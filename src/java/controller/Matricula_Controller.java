/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Matricula;
import java.util.List;
import model.dao.Matricula_DAO;

/**
 *
 * @author Gysa
 */
public class Matricula_Controller {
    Matricula_DAO md = new Matricula_DAO();
    Matricula matricula = new Matricula();
    boolean teste;
    List lista;
    public boolean inserir(Matricula matricula){
       teste = md.inserir(matricula);
       return teste;
    }
    
    public boolean alterar(Matricula matricula){
        teste = md.alterar(matricula);
        return teste;
    }
    
    public boolean excluir(int codigo){
        teste = md.excluir(codigo);
        return teste;
    }
    
    public List<Matricula> pesquisar_matricula(String nome){
        lista = md.pesquisar_matricula(nome);
        return lista;
    }
    
    public Integer gerar_codigo(){
        int i = md.gerar_codigo();
        return i;
    }
    
    public Matricula procurar_codigo(int codigo){
        matricula = md.procurar_codigo(codigo);
        return matricula;
    }
    
    public int getAvaliacao(int codigo){
        int teste1 = md.getAvaliacao(codigo);
        return teste1;
    }
}
