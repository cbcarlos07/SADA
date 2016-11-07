package model.dao;

import beans.Aluno;
import beans.Funcionario;
import beans.Avaliacao;
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


public class Avaliacao_DAO {
    
    private String sql;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;
    private final List list = null;
    
    public boolean inserir(Avaliacao avaliacao){
         boolean teste = false;
        conn = ConnectionFactory.getConnection();
        sql = "INSERT INTO avaliacao values(NULL,curdate(),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
       // sql = "{call sp_cargo_inserir(?,?,?,?)}";
        try {
            
            stmt = conn.prepareStatement(sql);
            //stmt = connection.prepareStatement(sql);
            //stmt.setDate(1, new Date(avaliacao.getAvaliacao_Data().getTime()));
            System.out.println("Funcionario DAO: "+avaliacao.getAvaliacao_Funcionario().getFuncionario_Codigo());
            stmt.setDouble(1, avaliacao.getAvaliacao_Altura());
            stmt.setDouble(2, avaliacao.getAvaliacao_Peso());
            stmt.setInt(3, avaliacao.getAvaliacao_Pressao_Max());
            stmt.setInt(4, avaliacao.getAvaliacao_Braco());
            stmt.setDouble(5, avaliacao.getAvaliacao_Torax());
            stmt.setDouble(6, avaliacao.getAvaliacao_Cintura());
            stmt.setDouble(7, avaliacao.getAvaliacao_Abdomen());
            stmt.setDouble(8, avaliacao.getAvaliacao_Quadril());
            stmt.setDouble(9, avaliacao.getAvaliacao_Panturrilha());
            stmt.setDouble(10, avaliacao.getAvaliacao_Culotes());
            stmt.setInt(11, avaliacao.getAvaliacao_Bat_Inicial());
            stmt.setInt(12, avaliacao.getAvaliacao_Bat_Final());
            stmt.setDouble(13, avaliacao.getAvaliacao_Coxa());
            stmt.setInt(14, avaliacao.getAvaliacao_Funcionario().getFuncionario_Codigo());
            stmt.setInt(15, avaliacao.getAvaliacao_Matricula().getMat_Codigo());
            stmt.setInt(16, avaliacao.getAvaliacao_Pressao_Min());
            stmt.execute();
            teste = true;
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Avaliacao_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    public boolean compararSeAvaliacaoExiste(String nome, int cidade){
        boolean teste = true;
        conn = ConnectionFactory.getConnection();
        sql ="SELECT * FROM avaliacao WHERE avadata = ? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            
            rs = stmt.executeQuery();
            if(rs.next()){
                teste = false;
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(Avaliacao_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teste;
    }
    
    public boolean alterar(Avaliacao avaliacao){
        
        boolean status = false;
        
        try 
        {
            
	        sql = " update avaliacao "+
	              " set avadata = ?, "+
                      "     avaaltura = ?,  "+
                      "     avapeso = ?,  "+
                      "     avapressaomax = ?,  "+  
                      "     avapressaomin = ?,  "+
                      "     avabraco = ?, "+
                      "     avatorax = ?,"+
                      "     avacintura = ?,"+
                      "     avaabdomen = ?,"+
                      "     avaquadril = ?,"+
                      "     avapanturrilha = ?, "+
                      "     avaculotes = ?, "+
                      "     avabatinicial = ?, "+
                      "     avabatfinal= ?, "+
                      "     avacoxa = ?, "+
                      "     avafuncod = ?, "+
                      "     avamatcod = ? "+   
	              " where avacod = ?";
	                
	        conn = ConnectionFactory.getConnection();
	        stmt = conn.prepareStatement(sql);
                stmt.setDate(1, new Date(avaliacao.getAvaliacao_Data().getTime()));
            stmt.setDouble(2, avaliacao.getAvaliacao_Altura());
            stmt.setDouble(3, avaliacao.getAvaliacao_Peso());
            stmt.setInt(4, avaliacao.getAvaliacao_Pressao_Max());
            stmt.setInt(5, avaliacao.getAvaliacao_Pressao_Min());
            stmt.setInt(6, avaliacao.getAvaliacao_Braco());
            stmt.setDouble(7, avaliacao.getAvaliacao_Torax());
            stmt.setDouble(8, avaliacao.getAvaliacao_Cintura());
            stmt.setDouble(9, avaliacao.getAvaliacao_Abdomen());
            stmt.setDouble(10, avaliacao.getAvaliacao_Quadril());
            stmt.setDouble(11, avaliacao.getAvaliacao_Panturrilha());
            stmt.setDouble(12, avaliacao.getAvaliacao_Culotes());
            stmt.setInt(13, avaliacao.getAvaliacao_Bat_Inicial());
            stmt.setInt(14, avaliacao.getAvaliacao_Bat_Final());
            stmt.setDouble(15, avaliacao.getAvaliacao_Coxa());
            stmt.setInt(16, avaliacao.getAvaliacao_Funcionario().getFuncionario_Codigo());
            stmt.setInt(17, avaliacao.getAvaliacao_Matricula().getMat_Codigo());
            stmt.setInt(18, avaliacao.getAvaliacao_Codigo());
	        stmt.execute();
	        status = true;
	        conn.close();
              
        }catch (Exception e){
            
            Logger.getLogger(Avaliacao_DAO.class.getName()).log(Level.SEVERE, null, e);
            status = false;
        }
     
       return status;
    }
    
    public boolean excluir(int codigo){
          boolean teste = false;     
          
           try
           {
             sql = " delete from avaliacao "+
                   " where avacod = ?";
           
             conn = ConnectionFactory.getConnection();             
             stmt = conn.prepareStatement(sql);
             stmt.setInt(1, codigo);
             stmt.execute();
            teste = true; 
           }catch(Exception ex){
               Logger.getLogger(Avaliacao_DAO.class.getName()).log(Level.SEVERE, null, ex);
           }
           return teste;
    }
    
    public List<Avaliacao> pesquisar_avaliacao(String nome){
        Avaliacao avaliacao;
       // System.out.println("Pesquisar avaliacao");
        List listar = new ArrayList();
        conn = ConnectionFactory.getConnection();
        try {
            if(!nome.equals("")){
                sql = "{CALL sp_avaliacao_pesquisa(?)}";
                stmt = conn.prepareStatement(sql); 
                stmt.setString(1, nome);
                
            }else{
                sql = "SELECT * FROM V_AVALIACAO";
                stmt = conn.prepareStatement(sql); 
                
            }
                
            
            
            rs = stmt.executeQuery();
        
            while (rs.next()) {
                avaliacao = new Avaliacao();
                
                avaliacao.setAvaliacao_Codigo(rs.getInt("CODIGO"));
                avaliacao.setAvaliacao_Data(rs.getDate("DATA"));
                avaliacao.setAvaliacao_Peso(rs.getDouble("PESO"));
                avaliacao.setAvaliacao_Altura(rs.getDouble("ALTURA"));
                avaliacao.setAvaliacao_Pressao_Max(rs.getInt("PRESSAO_MAX"));
                avaliacao.setAvaliacao_Pressao_Min(rs.getInt("PRESSAO_MIN"));
                avaliacao.setAvaliacao_Braco(rs.getInt("BRACO"));
                avaliacao.setAvaliacao_Torax(rs.getDouble("TORAX"));
                avaliacao.setAvaliacao_Cintura(rs.getDouble("CINTURA"));
                avaliacao.setAvaliacao_Abdomen(rs.getDouble("ABDOMEN"));
                avaliacao.setAvaliacao_Quadril(rs.getDouble("QUADRIL"));
                avaliacao.setAvaliacao_Panturrilha(rs.getDouble("PANTURRILHA"));
                avaliacao.setAvaliacao_Culotes(rs.getDouble("CULOTES"));
                avaliacao.setAvaliacao_Bat_Inicial(rs.getInt("BATIMENTO_INICIAL"));
                avaliacao.setAvaliacao_Bat_Final(rs.getInt("BATIMENTO_FINAL"));
                avaliacao.setAvaliacao_Coxa(rs.getDouble("COXA"));
                avaliacao.setAvaliacao_Funcionaio(new Funcionario());
                avaliacao.getAvaliacao_Funcionario().setFuncionario_Codigo(rs.getInt("COD_FUNC"));
                avaliacao.getAvaliacao_Funcionario().setFuncionario_Nome(rs.getString("FUNCIONARIO"));
                avaliacao.setAvaliacao_Matricula(new Matricula());
                avaliacao.getAvaliacao_Matricula().setMat_Codigo(rs.getInt("MATRICULA"));
                avaliacao.getAvaliacao_Matricula().setMat_Aluno(new Aluno());
                avaliacao.getAvaliacao_Matricula().getMat_Aluno().setAluno_Nome("ALUNO");
                
                listar.add(avaliacao);

            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Avaliacao_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listar;
    }//fim do metodo pesquisarPorNome
    
    
    public Integer gerar_codigo(){
        
        int aux = 0;
        
          sql = " select " +
		  		" coalesce(max(avacod), 0)  as codigo " +
		  		" from avaliacao ";
          
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
    
    
    public Avaliacao procurar_codigo(int codigo){
       
       
       Avaliacao avaliacao = new Avaliacao();
       
       sql = "{call sp_avaliacao_pesquisa(?)}";
                     
       try
       {            
           	conn = ConnectionFactory.getConnection();
           	stmt = conn.prepareStatement(sql);
                stmt.setInt(1, codigo);
           	rs = stmt.executeQuery();  

	        while (rs.next())
	        {
	        avaliacao.setAvaliacao_Codigo(rs.getInt("CODIGO"));
                avaliacao.setAvaliacao_Data(rs.getDate("DATA"));
                avaliacao.setAvaliacao_Peso(rs.getDouble("PESO"));
                avaliacao.setAvaliacao_Altura(rs.getDouble("ALTURA"));
                avaliacao.setAvaliacao_Pressao_Max(rs.getInt("PRESSAO_MAX"));
                avaliacao.setAvaliacao_Pressao_Min(rs.getInt("PRESSAO_MIN"));
                avaliacao.setAvaliacao_Braco(rs.getInt("BRACO"));
                avaliacao.setAvaliacao_Torax(rs.getDouble("TORAX"));
                avaliacao.setAvaliacao_Cintura(rs.getDouble("CINTURA"));
                avaliacao.setAvaliacao_Abdomen(rs.getDouble("ABDOMEN"));
                avaliacao.setAvaliacao_Quadril(rs.getDouble("QUADRIL"));
                avaliacao.setAvaliacao_Panturrilha(rs.getDouble("PANTURRILHA"));
                avaliacao.setAvaliacao_Culotes(rs.getDouble("CULOTES"));
                avaliacao.setAvaliacao_Bat_Inicial(rs.getInt("BATIMENTO_INICIAL"));
                avaliacao.setAvaliacao_Bat_Final(rs.getInt("BATIMENTO_FINAL"));
                avaliacao.setAvaliacao_Coxa(rs.getDouble("COXA"));
                avaliacao.setAvaliacao_Funcionaio(new Funcionario());
                avaliacao.getAvaliacao_Funcionario().setFuncionario_Codigo(rs.getInt("COD_FUNC"));
                avaliacao.getAvaliacao_Funcionario().setFuncionario_Nome(rs.getString("FUNCIONARIO"));
                avaliacao.getAvaliacao_Matricula().setMat_Codigo(rs.getInt("MATRICULA"));
                avaliacao.getAvaliacao_Matricula().setMat_Aluno(new Aluno());
                avaliacao.getAvaliacao_Matricula().getMat_Aluno().setAluno_Nome("ALUNO");
                
	        }

               conn.close();
         
       }catch(Exception e){
            e.printStackTrace();
            
       }

       return avaliacao;
    }
    /*
    public Avaliacao procurar_descricao(String descricao){
       
       boolean encontrou = false;
       Avaliacao avaliacao = new Avaliacao();
       
       sql = " select * "+
             " from avaliacao " +
             " where avadata = ? "
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
              
              
                avaliacao.setMat_Codigo(rs.getInt("avacod"));
                avaliacao.setAvaliacao_Nome(rs.getString("avadata"));
                avaliacao.setAvaliacao_Cpf(rs.getString("avadata"));
                avaliacao.setAvaliacao_Sexo(rs.getString("avapeso"));
                avaliacao.setAvaliacao_Fone(rs.getString("matavacod"));
                avaliacao.setAvaliacao_Rua(rs.getString("avapressaomin"));
                avaliacao.setAvaliacao_Casa(rs.getString("alucasa"));
                avaliacao.setAvaliacao_Email(rs.getString("aluemail"));
                avaliacao.setBairro_Codigo(rs.getInt("alubaicod"));
                avaliacao.setBairro_Descricao(rs.getString("bainome"));
            }

            if (!encontrou)
            {
                avaliacao = null;
                System.out.println("não encontrou");
            } 
            
            else 
            {
                System.out.println("encontrou");
            }
         
       }catch(Exception e){
            e.printStackTrace();
       }

       return avaliacao;
    }
    */
}
