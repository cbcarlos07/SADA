/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Avaliacao;
import java.util.List;
import model.dao.Avaliacao_DAO;

/**
 *
 * @author Gysa
 */
public class Avaliacao_Controller {
    Avaliacao_DAO md = new Avaliacao_DAO();
    Avaliacao avaliacao = new Avaliacao();
    boolean teste;
    List lista;
    public boolean inserir(Avaliacao avaliacao){
       teste = md.inserir(avaliacao);
       return teste;
    }
    
    public boolean alterar(Avaliacao avaliacao){
        teste = md.alterar(avaliacao);
        return teste;
    }
    
    public boolean excluir(int codigo){
        teste = md.excluir(codigo);
        return teste;
    }
    
    public List<Avaliacao> pesquisar_avaliacao(String nome){
        lista = md.pesquisar_avaliacao(nome);
        return lista;
    }
    
    public Integer gerar_codigo(){
        int i = md.gerar_codigo();
        return i;
    }
    
    public Avaliacao procurar_codigo(int codigo){
        avaliacao = md.procurar_codigo(codigo);
        return avaliacao;
    }
}
