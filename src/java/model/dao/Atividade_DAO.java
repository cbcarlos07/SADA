package model.dao;





import beans.Atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionFactory;


public class Atividade_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private List list = null;
    
    public boolean inserir(Atividade atividade){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO atividade values(NULL,?,?,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            stmt.setString(1, atividade.getAtividade_Nome());
            stmt.setString(2, atividade.getAtividade_Descricao());
            stmt.setInt(3, atividade.getNivel_Codigo());
            
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Atividade_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeAtividadeExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM atividade WHERE atinome = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Atividade_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Atividade atividade){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update atividade "+
	              " set atinome = ?, "+
                      "     atidescricao = ?,  "+
                      "     atinivcod = ? "+  
	              " where aticod = ?";
	                
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
                stmt.setString(1, atividade.getAtividade_Nome());                
	        stmt.setString(2, atividade.getAtividade_Descricao());                
                stmt.setInt(3, atividade.getNivel_Codigo());
                stmt.setInt(4, atividade.getAtividade_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Atividade_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from atividade "+
                   " where aticod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Atividade_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Atividade> pesquisar_atividade(String nome){
        Atividade atividade;
       // System.out.println("Pesquisar atividade");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = " select * from atividade "
                        + "inner join nivel on nivcod = atinivcod"+
	                " where atinome LIKE ? ";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, "%" + nome + "%");
                
            }else{
                sql = " select * from atividade "
                        + "inner join nivel on nivcod = atinivcod";
                stmt = conn.prepareStatement(sql); 
                
            }
                
            
            
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                atividade = new Atividade();
                atividade.setAtividade_Codigo(rs.getInt("aticod"));
                atividade.setAtividade_Nome(rs.getString("atinome"));
                atividade.setAtividade_Descricao(rs.getString("atinome"));
                atividade.setNivel_Codigo(rs.getInt("nivcod"));
                atividade.setNivel_Descricao(rs.getString("nivdescricao"));
                
                listar.add(atividade);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Atividade_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(aticod), 0) + 1 as codigo " +
		  		" from atividade ";
          
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
    
    
    public Atividade procurar_codigo(int codigo){
       
       
       Atividade atividade = new Atividade();
       
       sql = " select * "+             
             " from atividade " +
             " inner join nivel on nivcod = atinivcod"+  
             " where aticod = ? ";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        while (rs.next())
	        {
	         
	          
	          atividade.setAtividade_Codigo(rs.getInt("aticod"));
                  atividade.setAtividade_Nome(rs.getString("atinome"));
	          atividade.setAtividade_Descricao(rs.getString("atidescricao"));
                  atividade.setNivel_Codigo(rs.getInt("nivcod"));
                  atividade.setNivel_Descricao(rs.getString("nivdescricao"));
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return atividade;
    }
    
    public Atividade procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Atividade atividade = new Atividade();
       
       sql = " select * "+
             " from atividade " +
             " where atinome = ? "
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
              
              atividade.setAtividade_Codigo(rs.getInt("aticod"));
              atividade.setAtividade_Descricao(rs.getString("atinome"));
            }

            if (!encontrou)
            {
                atividade = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return atividade;
    }
    
}
