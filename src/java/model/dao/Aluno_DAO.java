package model.dao;

import beans.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionFactory;


public class Aluno_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private List list = null;
    
    public boolean inserir(Aluno aluno){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO aluno values(NULL,?,?,?,?,?,?,?,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getAluno_Nome());
            stmt.setString(2, aluno.getAluno_Cpf());
            stmt.setString(3, aluno.getAluno_Sexo());
            stmt.setString(4, aluno.getAluno_Fone());
            stmt.setString(5, aluno.getAluno_Rua());
            stmt.setString(6, aluno.getAluno_Casa());
            stmt.setString(7, aluno.getAluno_Email());
            stmt.setInt(8, aluno.getBairro_Codigo());
            
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Aluno_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeAlunoExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM aluno WHERE alunome = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Aluno_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Aluno aluno){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update aluno "+
	              " set alunome = ?, "+
                      "     alucpf = ?,  "+
                      "     alusexo = ?,  "+
                      "     alufone = ?,  "+  
                      "     alurua = ?,  "+
                      "     alucasa = ?,  "+  
                      "     aluemail = ?,  "+  
                      "     alubaicod = ? "+  
	              " where alucod = ?";
	                
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
                stmt.setString(1, aluno.getAluno_Nome());
                stmt.setString(2, aluno.getAluno_Cpf());
                stmt.setString(3, aluno.getAluno_Sexo());
                stmt.setString(4, aluno.getAluno_Fone());
                stmt.setString(5, aluno.getAluno_Rua());
                stmt.setString(6, aluno.getAluno_Casa());
                stmt.setString(7, aluno.getAluno_Email());
                stmt.setInt(8, aluno.getBairro_Codigo());
                stmt.setInt(9, aluno.getAluno_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Aluno_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from aluno "+
                   " where alucod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Aluno_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Aluno> pesquisar_aluno(String nome){
        Aluno aluno;
       // System.out.println("Pesquisar aluno");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = " select * from aluno "
                        + "inner join bairro on baicod = alubaicod"+
	                " where alunome LIKE ? ";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, "%" + nome + "%");
                
            }else{
                sql = " select * from aluno "
                        + "inner join bairro on baicod = alubaicod";
                stmt = conn.prepareStatement(sql); 
                
            }
                
            
            
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                aluno = new Aluno();
                aluno.setAluno_Codigo(rs.getInt("alucod"));
                aluno.setAluno_Nome(rs.getString("alunome"));
                aluno.setAluno_Cpf(rs.getString("alucpf"));
                aluno.setAluno_Sexo(rs.getString("alusexo"));
                aluno.setAluno_Fone(rs.getString("alufone"));
                aluno.setAluno_Rua(rs.getString("alurua"));
                aluno.setAluno_Casa(rs.getString("alucasa"));
                aluno.setAluno_Email(rs.getString("aluemail"));
                aluno.setBairro_Codigo(rs.getInt("alubaicod"));
                aluno.setBairro_Descricao(rs.getString("bainome"));
                
                listar.add(aluno);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Aluno_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(alucod), 0)  as codigo " +
		  		" from aluno ";
          
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
    
    
    public Aluno procurar_codigo(int codigo){
       
       
       Aluno aluno = new Aluno();
       
       sql = " select * "+             
             " from aluno " +
             " inner join bairro on baicod = alubaicod"+  
             " where alucod = ? ";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        while (rs.next())
	        {
	         
	          
	          
                aluno.setAluno_Codigo(rs.getInt("alucod"));
                aluno.setAluno_Nome(rs.getString("alunome"));
                aluno.setAluno_Cpf(rs.getString("alucpf"));
                aluno.setAluno_Sexo(rs.getString("alusexo"));
                aluno.setAluno_Fone(rs.getString("alufone"));
                aluno.setAluno_Rua(rs.getString("alurua"));
                aluno.setAluno_Casa(rs.getString("alucasa"));
                aluno.setAluno_Email(rs.getString("aluemail"));
                aluno.setBairro_Codigo(rs.getInt("alubaicod"));
                aluno.setBairro_Descricao(rs.getString("bainome"));
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return aluno;
    }
    
    public Aluno procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Aluno aluno = new Aluno();
       
       sql = " select * "+
             " from aluno " +
             " where alunome = ? "
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
              
              
                aluno.setAluno_Codigo(rs.getInt("alucod"));
                aluno.setAluno_Nome(rs.getString("alunome"));
                aluno.setAluno_Cpf(rs.getString("alunome"));
                aluno.setAluno_Sexo(rs.getString("alusexo"));
                aluno.setAluno_Fone(rs.getString("alufone"));
                aluno.setAluno_Rua(rs.getString("alurua"));
                aluno.setAluno_Casa(rs.getString("alucasa"));
                aluno.setAluno_Email(rs.getString("aluemail"));
                aluno.setBairro_Codigo(rs.getInt("alubaicod"));
                aluno.setBairro_Descricao(rs.getString("bainome"));
            }

            if (!encontrou)
            {
                aluno = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return aluno;
    }
    
}
