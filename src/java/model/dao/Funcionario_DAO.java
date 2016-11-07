package model.dao;

import beans.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionFactory;


public class Funcionario_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private List list = null;
    
    public boolean inserir(Funcionario funcionario){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO funcionario VALUES (NULL,?,?,?,?,?,?,?,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getFuncionario_Nome());
            stmt.setString(2, funcionario.getFuncionario_Cpf());
            stmt.setString(3, funcionario.getFuncionario_Sexo());
            stmt.setString(4, funcionario.getFuncionario_Fone());
            stmt.setString(5, funcionario.getFuncionario_Rua());
            stmt.setString(6, funcionario.getFuncionario_Casa());
            stmt.setString(7, funcionario.getFuncionario_Email());
            stmt.setInt(8, funcionario.getBairro_Codigo());
            
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeFuncionarioExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM funcionario WHERE funnome = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Funcionario funcionario){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update funcionario "+
	              " set funnome = ?, "+
                      "     funcpf = ?,  "+
                      "     funsexo = ?,  "+
                      "     funfone = ?,  "+  
                      "     funrua = ?,  "+
                      "     funcasa = ?,  "+  
                      "     funemail = ?,  "+  
                      "     funbaicod = ? "+  
	              " where funcod = ?";
	                
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
                stmt.setString(1, funcionario.getFuncionario_Nome());
                stmt.setString(2, funcionario.getFuncionario_Cpf());
                stmt.setString(3, funcionario.getFuncionario_Sexo());
                stmt.setString(4, funcionario.getFuncionario_Fone());
                stmt.setString(5, funcionario.getFuncionario_Rua());
                stmt.setString(6, funcionario.getFuncionario_Casa());
                stmt.setString(7, funcionario.getFuncionario_Email());
                stmt.setInt(8, funcionario.getBairro_Codigo());
                stmt.setInt(9, funcionario.getFuncionario_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Funcionario_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from funcionario "+
                   " where funcod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Funcionario_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Funcionario> pesquisar_funcionario(String nome){
        Funcionario funcionario;
       // System.out.println("Pesquisar funcionario");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = " select * from funcionario "
                        + "inner join bairro on baicod = funbaicod"+
	                " where funnome LIKE ? ";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, "%" + nome + "%");
                
            }else{
                sql = " select * from funcionario "
                        + "inner join bairro on baicod = funbaicod";
                stmt = conn.prepareStatement(sql); 
                
            }
                
            
            
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setFuncionario_Codigo(rs.getInt("funcod"));
                funcionario.setFuncionario_Nome(rs.getString("funnome"));
                funcionario.setFuncionario_Cpf(rs.getString("funcpf"));
                funcionario.setFuncionario_Sexo(rs.getString("funsexo"));
                funcionario.setFuncionario_Fone(rs.getString("funfone"));
                funcionario.setFuncionario_Rua(rs.getString("funrua"));
                funcionario.setFuncionario_Casa(rs.getString("funcasa"));
                funcionario.setFuncionario_Email(rs.getString("funemail"));
                funcionario.setBairro_Codigo(rs.getInt("funbaicod"));
                funcionario.setBairro_Descricao(rs.getString("bainome"));
                
                listar.add(funcionario);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Funcionario_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(funcod), 0)  as codigo " +
		  		" from funcionario ";
          
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
    
    
    public Funcionario procurar_codigo(int codigo){
       
       
       Funcionario funcionario = new Funcionario();
       
       sql = " select * "+             
             " from funcionario " +
             " inner join bairro on baicod = funbaicod"+  
             " where funcod = ? ";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        while (rs.next())
	        {
	         
	          
	          
                funcionario.setFuncionario_Codigo(rs.getInt("funcod"));
                funcionario.setFuncionario_Nome(rs.getString("funnome"));
                funcionario.setFuncionario_Cpf(rs.getString("funcpf"));
                funcionario.setFuncionario_Sexo(rs.getString("funsexo"));
                funcionario.setFuncionario_Fone(rs.getString("funfone"));
                funcionario.setFuncionario_Rua(rs.getString("funrua"));
                funcionario.setFuncionario_Casa(rs.getString("funcasa"));
                funcionario.setFuncionario_Email(rs.getString("funemail"));
                funcionario.setBairro_Codigo(rs.getInt("funbaicod"));
                funcionario.setBairro_Descricao(rs.getString("bainome"));
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return funcionario;
    }
    
    public Funcionario procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Funcionario funcionario = new Funcionario();
       
       sql = " select * "+
             " from funcionario " +
             " where funnome = ? "
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
              
              
                funcionario.setFuncionario_Codigo(rs.getInt("funcod"));
                funcionario.setFuncionario_Nome(rs.getString("funnome"));
                funcionario.setFuncionario_Cpf(rs.getString("funnome"));
                funcionario.setFuncionario_Sexo(rs.getString("funsexo"));
                funcionario.setFuncionario_Fone(rs.getString("funfone"));
                funcionario.setFuncionario_Rua(rs.getString("funrua"));
                funcionario.setFuncionario_Casa(rs.getString("funcasa"));
                funcionario.setFuncionario_Email(rs.getString("funemail"));
                funcionario.setBairro_Codigo(rs.getInt("funbaicod"));
                funcionario.setBairro_Descricao(rs.getString("bainome"));
            }

            if (!encontrou)
            {
                funcionario = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return funcionario;
    }
    
}
