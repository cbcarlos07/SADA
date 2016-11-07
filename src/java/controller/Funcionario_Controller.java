/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Funcionario;
import java.util.List;
import model.dao.Funcionario_DAO;

/**
 *
 * @author carlos.brito
 */
public class Funcionario_Controller {
    Funcionario_DAO ad = new Funcionario_DAO();
    Funcionario funcionario = new Funcionario();
    List lista;
    public List<Funcionario> pesquisar_funcionario(String nome){
        lista = ad.pesquisar_funcionario(nome);
        return lista;
    }
    
    public boolean inserir(Funcionario funcionario){
        boolean teste = ad.inserir(funcionario);
        return teste;
    }
    
    public boolean alterar(Funcionario funcionario){
       boolean teste = ad.alterar(funcionario);
        return teste; 
    }
    
    public boolean excluir(int codigo){
         boolean teste = ad.excluir(codigo);
        return teste; 
    }
    public Funcionario procurar_codigo(int codigo){
        funcionario = ad.procurar_codigo(codigo);
        return funcionario;
    }
    public Integer gerar_codigo(){
        int i = ad.gerar_codigo();
        return i;
    }
  
    
}
