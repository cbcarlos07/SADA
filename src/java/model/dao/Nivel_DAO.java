package model.dao;

import beans.Nivel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionFactory;


public class Nivel_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private List list = null;
    
    public boolean inserir(Nivel nivel){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO nivel values(NULL,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            stmt.setString(1, nivel.getNivel_Descricao());
            
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Nivel_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeNivelExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM nivel WHERE nivdescricao = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Nivel_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Nivel nivel){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update nivel "+
	              " set nivdescricao = ? "+                            
	              " where nivcod = ?";
	                
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, nivel.getNivel_Descricao());                
                stmt.setInt(2, nivel.getNivel_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Nivel_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from nivel "+
                   " where nivcod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Nivel_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Nivel> pesquisar_nivel(String nome){
        Nivel nivel;
       // System.out.println("Pesquisar nivel");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = " select * from nivel "+
	                      " where nivdescricao LIKE ? ";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, "%" + nome + "%");
                
            }else{
                sql = " select * from nivel ";
                stmt = conn.prepareStatement(sql); 
                
            }
                
            
            
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                nivel = new Nivel();
                nivel.setNivel_Codigo(rs.getInt("nivcod"));
                nivel.setNivel_Descricao(rs.getString("nivdescricao"));
                
                listar.add(nivel);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Nivel_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(nivcod), 0) + 1 as codigo " +
		  		" from nivel ";
          
          try
          {            
              conn = ConnectionFactory.getConnection();
              stmt = conn.prepareStatement(sql);
              rs = stmt.executeQuery(sql);

               while (rs.next()){
            
                    aux = Integer.parseInt(rs.getString("codigo"));

               }
           
         
       }catch(Exception e){
            e.printStackTrace();
       }
        
       return aux;
    }
    
    
    public Nivel procurar_codigo(int codigo){
       
       
       Nivel nivel = new Nivel();
       
       sql = " select * "+
             " from nivel " +
             " where nivcod = ? ";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        while (rs.next())
	        {
	         
	          
	          nivel.setNivel_Codigo(rs.getInt("nivcod"));
	          nivel.setNivel_Descricao(rs.getString("nivdescricao"));
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return nivel;
    }
    
    public Nivel procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Nivel nivel = new Nivel();
       
       sql = " select * "+
             " from nivel " +
             " where nivdescricao = ? "
               + "and  bai_cidcod =  ? ";
                     
       try
       {            
           
           conn = ConnectionFactory.getConnection();
           stmt = conn.prepareStatement(sql);
           stmt.setString(1, descricao);
           rs = stmt.executeQuery(sql);  

            while (rs.next())
            {
              encontrou = true;
              
              nivel.setNivel_Codigo(rs.getInt("nivcod"));
              nivel.setNivel_Descricao(rs.getString("nivdescricao"));
            }

            if (!encontrou)
            {
                nivel = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return nivel;
    }
    
}
