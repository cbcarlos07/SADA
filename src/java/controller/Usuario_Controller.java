/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Usuario;
import java.util.List;
import model.dao.Usuario_DAO;

/**
 *
 * @author carlos.brito
 */
public class Usuario_Controller {
    Usuario_DAO ud = new Usuario_DAO();
    Usuario usuario = new Usuario();
    
    public boolean efetuarLogin (String login, String senha){
        boolean teste = ud.efetuarLogin(login, senha);
        return teste;
    }
    public boolean inserirUserAluno(Usuario user){
        boolean teste = ud.inserirUserAluno(user);
        return teste;
    }
    
    public Usuario pesquisarUserAluno(int codigo){
        usuario = ud.pesquisarUserAluno(codigo);
        return usuario;
    }
    
    public boolean inserirUserFunc(Usuario user){
        boolean teste = ud.inserirUserFunc(user);
        return teste;
    }
    
    public Usuario pesquisarUserFunc(int codigo){
        usuario = ud.pesquisarUserFunc(codigo);
        return usuario;
    }
    
    public int getCodigo(String login, String senha){
        int i = ud.getCodigo(login, senha);
        return i;
    }
    
    public List<Usuario> listarTodos(String login){
        List lista = ud.listarTodos(login);
        return lista;
    }
}
