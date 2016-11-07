/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import beans.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.connection.ConnectionFactory;




/**
 *
 * @author Brito
 */
public class Usuario_DAO {
    Connection connection = null;
    PreparedStatement stmt = null;    
    ResultSet resultSet = null;
    String sql = "";
    Usuario usuario = null;
    //inicio do metodo inserir
    public boolean inserirUserAluno(Usuario user){
        boolean teste = false;
        connection = ConnectionFactory.getConnection();
        //sql = "INSERT INTO usuarios values(NULL,?,?,?,?)";
        System.out.println("Chamou o metodo inserir usuario");
        sql = "{call sp_usuario_inserir(?,?,?,?)}";
        try {
            
            stmt = connection.prepareCall(sql);
            //stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, user.getUsuario_Login());
            stmt.setString(2, user.getUsuario_Senha());
            stmt.setString(3, user.getUsuario_Tipo());
            stmt.setInt(4, user.getUsuario_Aluno());
            
            
            stmt.execute();
            teste = true;
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }  

public boolean inserirUserFunc(Usuario user){
        boolean teste = false;
        connection = ConnectionFactory.getConnection();
        //sql = "INSERT INTO usuarios values(NULL,?,?,?,?)";
        System.out.println("Chamou o metodo inserir usuario");
        sql = "{call sp_usuario_inserir_func(?,?,?,?)}";
        try {
            
            stmt = connection.prepareCall(sql);
            //stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, user.getUsuario_Login());
            stmt.setString(2, user.getUsuario_Senha());
            stmt.setString(3, user.getUsuario_Tipo());
            System.out.println("Codigo do funcionario: "+user.getUsuario_Funcionario());
            stmt.setInt(4, user.getUsuario_Funcionario());
            
            
            stmt.execute();
            teste = true;
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }

//FIM DO METODO INSERIR
    
//inicio do metodo alterar
    public boolean alterar (Usuario user){
        boolean teste = false;
        connection = ConnectionFactory.getConnection();
        sql ="{call sp_usuario_alterar(?,?,?,?,?)}";
        try {
            stmt =  connection.prepareStatement(sql);
             stmt.setString(1, user.getUsuario_Login());
            stmt.setString(2, user.getUsuario_Senha());
            stmt.setString(3, user.getUsuario_Tipo());
            stmt.setInt(4, user.getUsuario_Aluno());
            stmt.setInt(5, user.getUsuario_Funcionario());
            stmt.setInt(6, user.getUsuario_Codigo());
            stmt.execute();  
            teste = true;
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }//fim do método alterar
    
    //inicio do metodo excluir
    public boolean excluir(int codigo){
        boolean teste = false;
        connection= ConnectionFactory.getConnection();
        sql="{call sp_usuario_excluir(?)}";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1,codigo);
            stmt.execute();
            teste = true;
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }// FIM DO METODO EXCLUIR
    
     //inicio do metodo pesquisaPorCodigo
    public Usuario pesquisarPorCodigo(int codigo){
        
        connection = ConnectionFactory.getConnection();
        sql ="{call sp_usuario_pesquisar_codigo(?)}";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                usuario = new Usuario();
                usuario.setUsuario_Codigo(resultSet.getInt("usucod"));                
                usuario.setUsuario_Login(resultSet.getString("usulogin"));
                usuario.setUsuario_Senha(resultSet.getString("ususenha"));
                usuario.setUsuario_Tipo(resultSet.getString("usutipo"));
                usuario.setUsuario_Aluno(resultSet.getInt("usualucod"));
                usuario.setUsuario_Funcionario(resultSet.getInt("usufunccod"));
                
                connection.close();
            }
            else{
                JOptionPane.showMessageDialog(null,"Código invalido");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }//fim do metodo pesquisarPorCodigo
    
    public Usuario pesquisarUserAluno(int codigo){
        
        connection = ConnectionFactory.getConnection();
        sql ="SELECT * FROM usuario WHERE usualucod = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                usuario = new Usuario();
                usuario.setUsuario_Codigo(resultSet.getInt("usucod"));                
                usuario.setUsuario_Login(resultSet.getString("usulogin"));
                usuario.setUsuario_Senha(resultSet.getString("ususenha"));
                usuario.setUsuario_Tipo(resultSet.getString("usutipo"));
                usuario.setUsuario_Aluno(resultSet.getInt("usualucod"));
                usuario.setUsuario_Funcionario(resultSet.getInt("usufuncod"));
                
                connection.close();
            }
            else{
                JOptionPane.showMessageDialog(null,"Código invalido");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

public Usuario pesquisarUserFunc(int codigo){
        
        connection = ConnectionFactory.getConnection();
        sql ="SELECT * FROM usuario WHERE usufuncod = ?";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, codigo);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                usuario = new Usuario();
                usuario.setUsuario_Codigo(resultSet.getInt("usucod"));                
                usuario.setUsuario_Login(resultSet.getString("usulogin"));
                usuario.setUsuario_Senha(resultSet.getString("ususenha"));
                usuario.setUsuario_Tipo(resultSet.getString("usutipo"));
                usuario.setUsuario_Aluno(resultSet.getInt("usualucod"));
                usuario.setUsuario_Funcionario(resultSet.getInt("usufuncod"));
                
                connection.close();
            }
            else{
                JOptionPane.showMessageDialog(null,"Código invalido");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
//fim do metodo pesquisarPorCodigo
    
     public boolean efetuarLogin(String login, String senha){
        boolean teste = false;
        connection = ConnectionFactory.getConnection();
        String l, s;
        
            sql =  "{call sp_usuario_login(?,?)}";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
           // System.out.println("Login: "+login+" Senha: "+senha);
            stmt.setString(2, senha);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                    teste = true;
                    connection.close();
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }// fim do método Login
     
     public int getCodigo(String login, String senha){
        int i = 0;
        connection = ConnectionFactory.getConnection();
        String l, s;
        
            sql =  "SELECT * FROM usuario WHERE usulogin = ? and ususenha = MD5(?)";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
           // System.out.println("Login: "+login+" Senha: "+senha);
            stmt.setString(2, senha);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                    i = resultSet.getInt("usufuncod");
                    connection.close();
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }// fim do método Login
     /*
     * metodo de comparar login
     */
    public boolean compararLogin(String login){
        boolean teste = true;
        String comparar;
        connection = ConnectionFactory.getConnection();
        sql = "{call sp_usuario_compararlogin(?)}";
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, login);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                comparar = resultSet.getString("login");
                if(comparar.equals(login)){
                    teste = false;
                }
            }
        }catch(SQLException ex){
            System.out.println("Erro:  "+ex.getMessage());
        }
        
        return teste;
    }
    
    //fim do metodo comparar login
    /*
    esse metodo ira comparar se a senha ja foi configurada antes
    */
    public boolean compararSenha(int id, String senha){
        boolean teste = true;
        String comparar;
        connection = ConnectionFactory.getConnection();
        sql = "{call sp_usuario_compararSenha(?,?)}";
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, senha);
            resultSet = stmt.executeQuery();
            if(resultSet.next()){
                teste = false;
            }
        }catch(SQLException ex){
            System.out.println("Erro:  "+ex.getMessage());
        }
        
        return teste;
    }
    
    public List<Usuario> pesquisarPorNome(String nome){
        String name = "%"+nome+"%";
        List listar = new ArrayList();
        connection = ConnectionFactory.getConnection();
        try {
            if(nome.equals("")){
                sql = " select * from usuarios ";
                stmt = connection.prepareStatement(sql);
            }
            else{
                sql = " select * from usuarios  where usulogin LIKE ?";
                stmt = connection.prepareStatement(sql); 
                stmt.setString(1, "%" + nome + "%");
            } 
            
            resultSet = stmt.executeQuery();
        
            while (resultSet.next()) {
                usuario = new Usuario();
                usuario.setUsuario_Codigo(resultSet.getInt("usucod"));                
                usuario.setUsuario_Login(resultSet.getString("usulogin"));
                usuario.setUsuario_Senha(resultSet.getString("ususenha"));
                usuario.setUsuario_Tipo(resultSet.getString("usutipo"));
                usuario.setUsuario_Aluno(resultSet.getInt("usualucod"));
                usuario.setUsuario_Funcionario(resultSet.getInt("usufunccod"));  
                listar.add(usuario);

            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    
   
    //inicio do metodo listarTodos
        public List<Usuario> listarTodos(String login){
        connection = ConnectionFactory.getConnection();
        
        List<Usuario> listar = new ArrayList<Usuario>();
        try {
            if(login.equals("")){
                sql ="select * from v_usuarios";
                stmt = connection.prepareStatement(sql);
            }else{
                sql ="SELECT U.*, A.ALUNOME, F.FUNNOME FROM USUARIO U " +
                        "LEFT JOIN ALUNO A ON (ALUCOD = USUALUCOD) " +
                        "LEFT JOIN FUNCIONARIO F ON (FUNCOD = USUFUNCOD) " +
                        "WHERE USULOGIN LIKE ?";
                stmt = connection.prepareStatement(sql);
                stmt.setString(1, "%"+login+"%");
            }
            
            resultSet = stmt.executeQuery();
            while(resultSet.next()){
                usuario = new Usuario();
                usuario.setUsuario_Codigo(resultSet.getInt("usucod"));                
                usuario.setUsuario_Login(resultSet.getString("usulogin"));
                usuario.setUsuario_Senha(resultSet.getString("ususenha"));
                usuario.setUsuario_Tipo(resultSet.getString("usutipo"));
                usuario.setStr_usuario_Aluno(resultSet.getString("alunome"));
                usuario.setStr_usuario_Func(resultSet.getString("funnome"));  
                listar.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo listarTodos
     public int codigoGeral(){
         int codigo = 0;
         sql = "select * from usuarios";
         connection = ConnectionFactory.getConnection();
        try {
            stmt = connection.prepareStatement(sql);
            resultSet = stmt.executeQuery();
            if(resultSet.last()){
                codigo = resultSet.getInt("usu_id");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return codigo;
     }

    
    
}//fim dA CLASSE



    
       


