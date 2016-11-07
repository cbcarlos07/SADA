package model.dao;

import beans.Atividade;
import beans.Funcionario;
import beans.Turma;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionFactory;


public class Turma_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private List list = null;
    
    public boolean inserir(Turma turma){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO turma VALUES (NULL,?,?,?,?,?,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            Time hora =  turma.getTurma_Horario();
            //System.out.println("Horaio no dao antes de inserir: "+turma.getTurma_Horario()+" :35");
            System.out.println("Funcionario :38 dao: "+turma.getFuncionario().getFuncionario_Codigo());
            stmt.setTime(1, turma.getTurma_Horario());
            stmt.setString(2, turma.getTurma_Observacao());
            stmt.setDate(3, new java.sql.Date(turma.getTurma_Data().getTime()));
            stmt.setString(4, turma.getTurma_Descricao());
            stmt.setInt(5, turma.getFuncionario().getFuncionario_Codigo());
            stmt.setInt(6, turma.getAtividade().getAtividade_Codigo());
            
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Turma_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeTurmaExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM turma WHERE turdescricao = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Turma_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Turma turma){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update turma set "+
	              " turhorario = ?, "+
                      " turobservacao = ?, "+  
                      " turdata = ?, "+
                      " turdescricao = ?, "+  
                      " turfuncod = ?, "+  
                      " turaticod = ? "+  
	              " where turcod = ?";
	           
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
	        stmt.setTime(1, turma.getTurma_Horario());
                stmt.setString(2, turma.getTurma_Observacao());
                stmt.setDate(3, new java.sql.Date(turma.getTurma_Data().getTime()));
                stmt.setString(4, turma.getTurma_Descricao());
                stmt.setInt(5, turma.getFuncionario().getFuncionario_Codigo());
                stmt.setInt(6, turma.getAtividade().getAtividade_Codigo());
                stmt.setInt(7, turma.getTurma_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Turma_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from turma "+
                   " where turcod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Turma_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Turma> pesquisar_turma(String nome){
        Turma turma;
       // System.out.println("Pesquisar turma");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = sql = " SELECT * FROM TURMA T  " +
                "INNER JOIN FUNCIONARIO F ON (F.FUNCOD = T.TURFUNCOD)  " +
                "INNER JOIN ATIVIDADE A   ON (A.ATICOD = T.TURATICOD) " +
                "WHERE T.TURDESCRICAO LIKE ? ";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, "%" + nome + "%");
                
            }else{
                sql = " SELECT * FROM TURMA T  " +
                "INNER JOIN FUNCIONARIO F ON (F.FUNCOD = T.TURFUNCOD)  " +
                "INNER JOIN ATIVIDADE A   ON (A.ATICOD = T.TURATICOD)";
                stmt = conn.prepareStatement(sql); 
                
            }
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                turma = new Turma();
                turma.setTurma_Codigo(rs.getInt("turcod"));
                turma.setTurma_Horario(rs.getTime("turhorario"));
                turma.setTurma_Observacao(rs.getString("turobservacao"));
                turma.setTurma_Data(rs.getDate("turdata"));
                turma.setTurma_Descricao(rs.getString("turdescricao"));
                turma.setFuncionario(new Funcionario());
                turma.getFuncionario().setFuncionario_Codigo(rs.getInt("turfuncod"));
                turma.getFuncionario().setFuncionario_Nome(rs.getString("funnome"));
                turma.setAtividade(new Atividade());
                turma.getAtividade().setAtividade_Codigo(rs.getInt("turaticod"));
                turma.getAtividade().setAtividade_Nome(rs.getString("atinome"));
                listar.add(turma);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Turma_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(turcod), 0) + 1 as codigo " +
		  		" from turma ";
          
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
    
    
    public Turma procurar_codigo(int codigo){
       
       
       Turma turma = new Turma();
       
       sql = " select * "+
             " from turma T" +
             " INNER JOIN FUNCIONARIO F ON (F.FUNCOD = T.TURFUNCOD) "+
             " INNER JOIN ATIVIDADE A   ON (A.ATICOD = T.TURATICOD) " + 
             " where turcod = ? ";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        if (rs.next())
	        {
	         
                    turma.setTurma_Codigo(rs.getInt("turcod"));
                    turma.setTurma_Horario(rs.getTime("turhorario"));
                    turma.setTurma_Observacao(rs.getString("turobservacao"));
                    turma.setTurma_Data(rs.getDate("turdata"));
                    turma.setTurma_Descricao(rs.getString("turdescricao"));
                    turma.setFuncionario(new Funcionario());
                    turma.getFuncionario().setFuncionario_Codigo(rs.getInt("turfuncod"));
                    turma.getFuncionario().setFuncionario_Nome(rs.getString("funnome"));
                    turma.setAtividade(new Atividade());
                    turma.getAtividade().setAtividade_Codigo(rs.getInt("turaticod"));
                    turma.getAtividade().setAtividade_Nome(rs.getString("atinome"));
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return turma;
    }
    
    public Turma procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Turma turma = new Turma();
       
       sql = " select * "+
             " from turma " +
             " where turdescricao = ? "
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
              
              turma.setTurma_Codigo(rs.getInt("turcod"));
              turma.setTurma_Descricao(rs.getString("turdescricao"));
            }

            if (!encontrou)
            {
                turma = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return turma;
    }
    
}
