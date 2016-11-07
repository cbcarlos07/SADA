package model.dao;

import beans.Aluno;
import beans.Funcionario;
import beans.Matricula;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connection.ConnectionFactory;


public class Matricula_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private final List list = null;
    
    public boolean inserir(Matricula matricula){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO matricula values(NULL,?,?,?,?,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            stmt.setDate(1, new Date(matricula.getMat_Data_Inicio().getTime()));
            stmt.setDate(2, new Date(matricula.getMat_Date_Vencimento().getTime()));
            stmt.setString(3, matricula.getMat_Status());
            stmt.setInt(4, matricula.getMat_Aluno().getAluno_Codigo());
            stmt.setInt(5, matricula.getMat_Funcionario().getFuncionario_Codigo());
            
            
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Matricula_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeMatriculaExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM matricula WHERE matdtinicio = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Matricula_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Matricula matricula){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update matricula "+
	              " set matdtinicio = ?, "+
                      "     matdtvenc = ?,  "+
                      "     matstatus = ?,  "+
                      "     matalucod = ?,  "+  
                      "     matfuncod = ?  "+
	              " where matcod = ?";
	                
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
                stmt.setDate(1, new Date(matricula.getMat_Data_Inicio().getTime()));
                stmt.setDate(2, new Date(matricula.getMat_Date_Vencimento().getTime()));
                stmt.setString(3, matricula.getMat_Status());
                stmt.setInt(4, matricula.getMat_Aluno().getAluno_Codigo());
                stmt.setInt(5, matricula.getMat_Funcionario().getFuncionario_Codigo());
                stmt.setInt(6, matricula.getMat_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Matricula_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from matricula "+
                   " where matcod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Matricula_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Matricula> pesquisar_matricula(String nome){
        Matricula matricula;
       // System.out.println("Pesquisar matricula");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = "{CALL sp_matricula_pesquisa(?)}";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, nome);
                
            }else{
                sql = "SELECT * FROM V_MATRICULA";
                stmt = conn.prepareStatement(sql); 
                
            }
                
            
            
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                matricula = new Matricula();
                
                matricula.setMat_Codigo(rs.getInt("CODIGO"));
                matricula.setMat_Data_Inicio(rs.getDate("INICIO"));
                matricula.setMat_Date_Vencimento(rs.getDate("VENCIMENTO"));
                matricula.setMat_Status(rs.getString("STATUS"));
                matricula.setMat_Aluno(new Aluno());
                matricula.getMat_Aluno().setAluno_Codigo(rs.getInt("MATALUCOD"));
                matricula.getMat_Aluno().setAluno_Nome(rs.getString("ALUNO"));
                matricula.setMat_Funcionario(new Funcionario());
                matricula.getMat_Funcionario().setFuncionario_Codigo(rs.getInt("MATFUNCOD"));
                matricula.getMat_Funcionario().setFuncionario_Nome(rs.getString("FUNCIONARIO"));
                
                listar.add(matricula);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Matricula_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(matcod), 0)  as codigo " +
		  		" from matricula ";
          
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
    
    
    public Matricula procurar_codigo(int codigo){
       
       
       Matricula matricula = new Matricula();
       
       sql = "{call sp_matricula_pesquisa_codigo(?)}";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        while (rs.next())
	        {
	        matricula.setMat_Codigo(rs.getInt("CODIGO"));
                matricula.setMat_Data_Inicio(rs.getDate("INICIO"));
                matricula.setMat_Date_Vencimento(rs.getDate("VENCIMENTO"));
                matricula.setMat_Status(rs.getString("STATUS"));
                matricula.setMat_Aluno(new Aluno());
                matricula.getMat_Aluno().setAluno_Codigo(rs.getInt("MATALUCOD"));
                matricula.getMat_Aluno().setAluno_Nome(rs.getString("ALUNO"));
                matricula.setMat_Funcionario(new Funcionario());
                matricula.getMat_Funcionario().setFuncionario_Codigo(rs.getInt("MATFUNCOD"));
                matricula.getMat_Funcionario().setFuncionario_Nome(rs.getString("FUNCIONARIO"));
                
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return matricula;
    }
    /*
    public Matricula procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Matricula matricula = new Matricula();
       
       sql = " select * "+
             " from matricula " +
             " where matdtinicio = ? "
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
              
              
                matricula.setMat_Codigo(rs.getInt("matcod"));
                matricula.setMatricula_Nome(rs.getString("matdtinicio"));
                matricula.setMatricula_Cpf(rs.getString("matdtinicio"));
                matricula.setMatricula_Sexo(rs.getString("matstatus"));
                matricula.setMatricula_Fone(rs.getString("matmatcod"));
                matricula.setMatricula_Rua(rs.getString("matfuncod"));
                matricula.setMatricula_Casa(rs.getString("alucasa"));
                matricula.setMatricula_Email(rs.getString("aluemail"));
                matricula.setBairro_Codigo(rs.getInt("alubaicod"));
                matricula.setBairro_Descricao(rs.getString("bainome"));
            }

            if (!encontrou)
            {
                matricula = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return matricula;
    }
    */
    public int getAvaliacao(int codigo){
        int teste = 0;
        conn = ConnectionFactory.getConnection();
        sql = "SELECT * FROM AVALIACAO A WHERE A.AVAMATCOD = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = rs.getInt(1);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Matricula_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
}
