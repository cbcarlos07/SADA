-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.41-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sada
--

CREATE DATABASE IF NOT EXISTS sada;
USE sada;

--
-- Temporary table structure for view `v_avaliacao`
--
DROP TABLE IF EXISTS `v_avaliacao`;
DROP VIEW IF EXISTS `v_avaliacao`;
CREATE TABLE `v_avaliacao` (
  `CODIGO` int(11),
  `DATA` date,
  `PESO` double(2,2),
  `ALTURA` double(2,2),
  `PRESSAO_MAX` int(10) unsigned,
  `PRESSAO_MIN` int(10) unsigned,
  `BRACO` double(2,2),
  `TORAX` double(2,2),
  `CINTURA` double(2,2),
  `ABDOMEN` double(2,2),
  `QUADRIL` double(2,2),
  `PANTURRILHA` double(2,2),
  `CULOTES` double(2,2),
  `BATIMENTO_INICIAL` int(4),
  `BATIMENTO_FINAL` int(4),
  `COXA` double(2,2),
  `COD_FUNC` int(11),
  `FUNCIONARIO` varchar(100),
  `MATRICULA` int(11),
  `ALUNO` varchar(100)
);

--
-- Temporary table structure for view `v_matricula`
--
DROP TABLE IF EXISTS `v_matricula`;
DROP VIEW IF EXISTS `v_matricula`;
CREATE TABLE `v_matricula` (
  `CODIGO` int(8),
  `MATALUCOD` int(11),
  `ALUNO` varchar(100),
  `MATFUNCOD` int(11),
  `FUNCIONARIO` varchar(100),
  `INICIO` date,
  `VENCIMENTO` date,
  `STATUS` varchar(20)
);

--
-- Temporary table structure for view `v_usuarios`
--
DROP TABLE IF EXISTS `v_usuarios`;
DROP VIEW IF EXISTS `v_usuarios`;
CREATE TABLE `v_usuarios` (
  `usucod` int(11),
  `usulogin` varchar(100),
  `ususenha` varchar(70),
  `usutipo` varchar(1),
  `usualucod` int(11),
  `usufuncod` int(11),
  `ALUNOME` varchar(100),
  `FUNNOME` varchar(100)
);

--
-- Definition of table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno` (
  `alucod` int(11) NOT NULL auto_increment,
  `alunome` varchar(100) NOT NULL,
  `alucpf` varchar(14) NOT NULL,
  `alusexo` varchar(9) NOT NULL,
  `alufone` varchar(13) NOT NULL,
  `alurua` varchar(100) NOT NULL,
  `alucasa` int(5) NOT NULL,
  `aluemail` varchar(100) NOT NULL,
  `alubaicod` int(11) NOT NULL,
  PRIMARY KEY  (`alucod`),
  UNIQUE KEY `alucpf` (`alucpf`),
  KEY `alubaicod` (`alubaicod`),
  CONSTRAINT `aluno_ibfk_1` FOREIGN KEY (`alubaicod`) REFERENCES `bairro` (`baicod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aluno`
--

/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` (`alucod`,`alunome`,`alucpf`,`alusexo`,`alufone`,`alurua`,`alucasa`,`aluemail`,`alubaicod`) VALUES 
 (21,'ALUNO 21','643.985.111-05','M','923638555','GOVERNADOR',139,'email@gmail.com',2),
 (22,'ALUNO 22','469.695.687-33','M','9231313131','nome da rua',13,'funcionario@gmailcom',2);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;


--
-- Definition of table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
CREATE TABLE `atividade` (
  `aticod` int(11) NOT NULL auto_increment,
  `atinome` varchar(100) NOT NULL,
  `atidescricao` varchar(300) NOT NULL,
  `atinivcod` int(11) NOT NULL,
  PRIMARY KEY  (`aticod`),
  UNIQUE KEY `aticod` (`aticod`),
  KEY `atinivcod` (`atinivcod`),
  CONSTRAINT `atividade_ibfk_1` FOREIGN KEY (`atinivcod`) REFERENCES `nivel` (`nivcod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atividade`
--

/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` (`aticod`,`atinome`,`atidescricao`,`atinivcod`) VALUES 
 (1,'Teste','Teste',1),
 (2,'Teste 2','Teste 2',4),
 (3,'Teste 3','Teste 3',2);
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;


--
-- Definition of table `avaliacao`
--

DROP TABLE IF EXISTS `avaliacao`;
CREATE TABLE `avaliacao` (
  `avacod` int(11) NOT NULL auto_increment,
  `avadata` date NOT NULL,
  `avaaltura` double(2,2) NOT NULL,
  `avapeso` double(2,2) NOT NULL,
  `avapressaomax` int(10) unsigned NOT NULL,
  `avabraco` double(2,2) NOT NULL,
  `avatorax` double(2,2) NOT NULL,
  `avacintura` double(2,2) NOT NULL,
  `avaabdomen` double(2,2) NOT NULL,
  `avaquadril` double(2,2) NOT NULL,
  `avapanturrilha` double(2,2) NOT NULL,
  `avaculotes` double(2,2) NOT NULL,
  `avabatinicial` int(4) NOT NULL,
  `avabatfinal` int(4) NOT NULL,
  `avacoxa` double(2,2) NOT NULL,
  `avafuncod` int(11) NOT NULL,
  `avamatcod` int(11) NOT NULL,
  `avapressaomin` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`avacod`),
  KEY `avafuncod` (`avafuncod`),
  KEY `avamatcod` (`avamatcod`),
  CONSTRAINT `avaliacao_ibfk_1` FOREIGN KEY (`avafuncod`) REFERENCES `funcionario` (`funcod`),
  CONSTRAINT `avaliacao_ibfk_2` FOREIGN KEY (`avamatcod`) REFERENCES `matricula` (`matcod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `avaliacao`
--

/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;


--
-- Definition of table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
CREATE TABLE `bairro` (
  `baicod` int(11) NOT NULL auto_increment,
  `bainome` varchar(30) NOT NULL,
  PRIMARY KEY  (`baicod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bairro`
--

/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` (`baicod`,`bainome`) VALUES 
 (2,'NOVA CIDADE'),
 (4,'SAO JOSE'),
 (5,'NOVA REPUBLICA'),
 (8,'CRESPO'),
 (9,'JORGE TEIXEIRA');
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;


--
-- Definition of table `ficha`
--

DROP TABLE IF EXISTS `ficha`;
CREATE TABLE `ficha` (
  `ficcod` int(11) NOT NULL auto_increment,
  `ficdata` date NOT NULL,
  `ficavacod` int(11) NOT NULL,
  `ficaticod` int(11) NOT NULL,
  `ficturcod` int(11) NOT NULL,
  PRIMARY KEY  (`ficcod`),
  UNIQUE KEY `ficcod` (`ficcod`),
  KEY `ficturcod` (`ficturcod`),
  KEY `ficavacod` (`ficavacod`),
  KEY `ficaticod` (`ficaticod`),
  CONSTRAINT `ficha_ibfk_1` FOREIGN KEY (`ficturcod`) REFERENCES `turma` (`turcod`),
  CONSTRAINT `ficha_ibfk_2` FOREIGN KEY (`ficavacod`) REFERENCES `avaliacao` (`avacod`),
  CONSTRAINT `ficha_ibfk_3` FOREIGN KEY (`ficaticod`) REFERENCES `atividade` (`aticod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ficha`
--

/*!40000 ALTER TABLE `ficha` DISABLE KEYS */;
/*!40000 ALTER TABLE `ficha` ENABLE KEYS */;


--
-- Definition of table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE `funcionario` (
  `funcod` int(11) NOT NULL auto_increment,
  `funnome` varchar(100) NOT NULL,
  `funcpf` varchar(14) NOT NULL,
  `funsexo` varchar(9) NOT NULL,
  `funfone` varchar(13) NOT NULL,
  `funrua` varchar(100) NOT NULL,
  `funcasa` int(5) NOT NULL,
  `funemail` varchar(100) NOT NULL,
  `funbaicod` int(11) NOT NULL,
  PRIMARY KEY  (`funcod`),
  UNIQUE KEY `funcpf` (`funcpf`),
  KEY `funbaicod` (`funbaicod`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`funbaicod`) REFERENCES `bairro` (`baicod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcionario`
--

/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` (`funcod`,`funnome`,`funcpf`,`funsexo`,`funfone`,`funrua`,`funcasa`,`funemail`,`funbaicod`) VALUES 
 (3,'FUNC. 3','469.695.687-33','F','9231313131','RUA 3',123,'FUNCIONARIO23@GMAILCOM',8),
 (4,'FUNCIONARIO 4','643.985.111-05','M','923638555','GOVERNADOR',31,'EMAIL@GMAIL.COM',2);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;


--
-- Definition of table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
CREATE TABLE `matricula` (
  `matcod` int(8) NOT NULL auto_increment,
  `matdtinicio` date NOT NULL,
  `matdtvenc` date NOT NULL,
  `matstatus` varchar(20) NOT NULL,
  `matalucod` int(11) NOT NULL,
  `matfuncod` int(11) NOT NULL,
  PRIMARY KEY  (`matcod`),
  UNIQUE KEY `matcod` (`matcod`),
  KEY `matalucod` (`matalucod`),
  KEY `matfuncod` (`matfuncod`),
  CONSTRAINT `matricula_ibfk_1` FOREIGN KEY (`matalucod`) REFERENCES `aluno` (`alucod`),
  CONSTRAINT `matricula_ibfk_2` FOREIGN KEY (`matfuncod`) REFERENCES `funcionario` (`funcod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matricula`
--

/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` (`matcod`,`matdtinicio`,`matdtvenc`,`matstatus`,`matalucod`,`matfuncod`) VALUES 
 (1,'2016-11-04','2016-11-18','I',22,3),
 (2,'2016-11-06','2016-11-07','A',21,4);
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;


--
-- Definition of table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
CREATE TABLE `nivel` (
  `nivcod` int(11) NOT NULL auto_increment,
  `nivdescricao` varchar(70) NOT NULL,
  PRIMARY KEY  (`nivcod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nivel`
--

/*!40000 ALTER TABLE `nivel` DISABLE KEYS */;
INSERT INTO `nivel` (`nivcod`,`nivdescricao`) VALUES 
 (1,'Administrador.'),
 (2,'Aluno.'),
 (4,'Funcionario');
/*!40000 ALTER TABLE `nivel` ENABLE KEYS */;


--
-- Definition of table `turma`
--

DROP TABLE IF EXISTS `turma`;
CREATE TABLE `turma` (
  `turcod` int(11) NOT NULL auto_increment,
  `turhorario` time NOT NULL,
  `turobservacao` varchar(300) default NULL,
  `turdata` date default NULL,
  `turdescricao` varchar(80) NOT NULL,
  `turfuncod` int(11) NOT NULL,
  `turaticod` int(11) NOT NULL,
  PRIMARY KEY  (`turcod`),
  UNIQUE KEY `turcod` (`turcod`),
  KEY `turfuncod` (`turfuncod`),
  KEY `turaticod` (`turaticod`),
  CONSTRAINT `turma_ibfk_1` FOREIGN KEY (`turfuncod`) REFERENCES `funcionario` (`funcod`),
  CONSTRAINT `turma_ibfk_2` FOREIGN KEY (`turaticod`) REFERENCES `atividade` (`aticod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `turma`
--

/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` (`turcod`,`turhorario`,`turobservacao`,`turdata`,`turdescricao`,`turfuncod`,`turaticod`) VALUES 
 (2,'05:00:00','OBSERV.','2016-10-21','TURMA A',4,3),
 (3,'01:00:00','TURMA','2016-11-06','TURMA B',3,2);
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `usucod` int(11) NOT NULL auto_increment,
  `usulogin` varchar(100) NOT NULL,
  `ususenha` varchar(70) NOT NULL,
  `usutipo` varchar(1) NOT NULL,
  `usualucod` int(11) default NULL,
  `usufuncod` int(11) default NULL,
  PRIMARY KEY  (`usucod`),
  KEY `usualucod` (`usualucod`),
  KEY `usufuncod` (`usufuncod`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`usualucod`) REFERENCES `aluno` (`alucod`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`usufuncod`) REFERENCES `funcionario` (`funcod`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`usucod`,`usulogin`,`ususenha`,`usutipo`,`usualucod`,`usufuncod`) VALUES 
 (1,'ADMIN','73acd9a5972130b75066c82595a1fae3','1',NULL,NULL),
 (2,'pessoa','202cb962ac59075b964b07152d234b70','1',21,NULL),
 (3,'funcionario','202cb962ac59075b964b07152d234b70','1',22,NULL),
 (4,'FUNC','202cb962ac59075b964b07152d234b70','1',NULL,3),
 (5,'EMAIL','202cb962ac59075b964b07152d234b70','1',NULL,4);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


--
-- Definition of procedure `SP_AVALIACAO_PESQUISA`
--

DROP PROCEDURE IF EXISTS `SP_AVALIACAO_PESQUISA`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`sada`@`localhost` PROCEDURE `SP_AVALIACAO_PESQUISA`(PARAMETRO VARCHAR(70))
BEGIN
DECLARE TEXTO VARCHAR(70);
SET TEXTO = CONCAT('$',PARAMETRO, '%');

SELECT
     A.AVACOD          CODIGO
     ,A.AVADATA         DATA
     ,A.AVAPESO         PESO
     ,A.AVAALTURA       ALTURA
     ,A.AVAPRESSAOMAX   PRESSAO_MAX
     ,A.AVAPRESSAOMIN   PRESSAO_MIN
     ,A.AVABRACO        BRACO
     ,A.AVATORAX        TORAX
     ,A.AVACINTURA      CINTURA
     ,A.AVAABDOMEN      ABDOMEN
     ,A.AVAQUADRIL      QUADRIL
     ,A.AVAPANTURRILHA  PANTURRILHA
     ,A.AVACULOTES      CULOTES
     ,A.AVABATINICIAL   BATIMENTO_INICIAL
     ,A.AVABATFINAL     BATIMENTO_FINAL
     ,A.AVACOXA         COXA
     ,A.AVAFUNCOD       COD_FUNC
     ,F.FUNNOME         FUNCIONARIO
     ,A.AVAMATCOD       MATRICULA
     ,AL.ALUNOME         ALUNO
FROM AVALIACAO A
     INNER JOIN MATRICULA M   ON M.MATCOD  = AVAMATCOD
     INNER JOIN FUNCIONARIO F ON F.FUNCOD  = AVAFUNCOD
     INNER JOIN ALUNO AL      ON AL.ALUCOD = M.MATALUCOD
WHERE
     A.AVACOD = PARAMETRO;


END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_matricula_pesquisa`
--

DROP PROCEDURE IF EXISTS `sp_matricula_pesquisa`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`sada`@`localhost` PROCEDURE `sp_matricula_pesquisa`(PARAMETRO VARCHAR(70))
BEGIN
DECLARE TEXTO VARCHAR(70);
SET TEXTO = CONCAT('%',PARAMETRO,'%');
SELECT
   M.MATCOD CODIGO
  ,M.MATALUCOD
  ,A.ALUNOME ALUNO
  ,M.MATFUNCOD
  ,F.FUNNOME FUNCIONARIO
  ,M.MATDTINICIO INICIO
  ,M.MATDTVENC VENCIMENTO
  ,M.MATSTATUS STATUS
FROM
   MATRICULA M
   INNER JOIN ALUNO A        ON A.ALUCOD = M.MATALUCOD
   INNER JOIN FUNCIONARIO F  ON F.FUNCOD = M.MATFUNCOD
WHERE
   M.MATCOD = PARAMETRO OR
  (A.ALUNOME LIKE TEXTO OR F.FUNNOME LIKE TEXTO);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_matricula_pesquisa_codigo`
--

DROP PROCEDURE IF EXISTS `sp_matricula_pesquisa_codigo`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`sada`@`localhost` PROCEDURE `sp_matricula_pesquisa_codigo`(PARAMETRO INT)
BEGIN

SELECT
   M.MATCOD CODIGO
  ,M.MATALUCOD
  ,A.ALUNOME ALUNO
  ,M.MATFUNCOD
  ,F.FUNNOME FUNCIONARIO
  ,M.MATDTINICIO INICIO
  ,M.MATDTVENC VENCIMENTO
  ,M.MATSTATUS STATUS
FROM
   MATRICULA M
   INNER JOIN ALUNO A        ON A.ALUCOD = M.MATALUCOD
   INNER JOIN FUNCIONARIO F  ON F.FUNCOD = M.MATFUNCOD
WHERE
   M.MATCOD = PARAMETRO ;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_usuario_inserir`
--

DROP PROCEDURE IF EXISTS `sp_usuario_inserir`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`sada`@`localhost` PROCEDURE `sp_usuario_inserir`(
login VARCHAR(100),
senha VARCHAR(70),
tipo  VARCHAR(1),
aluno INT
)
BEGIN
INSERT INTO usuario (usulogin, ususenha, usutipo, usualucod)
VALUES (login, MD5(senha), tipo, aluno);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_usuario_inserir_func`
--

DROP PROCEDURE IF EXISTS `sp_usuario_inserir_func`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`sada`@`localhost` PROCEDURE `sp_usuario_inserir_func`(
login VARCHAR(100),
senha VARCHAR(70),
tipo  VARCHAR(1),
funcionario INT
)
BEGIN
INSERT INTO usuario (usulogin, ususenha, usutipo, usufuncod)
VALUES (login, MD5(senha), tipo, funcionario);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_usuario_login`
--

DROP PROCEDURE IF EXISTS `sp_usuario_login`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
(nil) $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of view `v_avaliacao`
--

DROP TABLE IF EXISTS `v_avaliacao`;
DROP VIEW IF EXISTS `v_avaliacao`;
CREATE ALGORITHM=UNDEFINED DEFINER=`sada`@`localhost` SQL SECURITY DEFINER VIEW `v_avaliacao` AS select `a`.`avacod` AS `CODIGO`,`a`.`avadata` AS `DATA`,`a`.`avapeso` AS `PESO`,`a`.`avaaltura` AS `ALTURA`,`a`.`avapressaomax` AS `PRESSAO_MAX`,`a`.`avapressaomin` AS `PRESSAO_MIN`,`a`.`avabraco` AS `BRACO`,`a`.`avatorax` AS `TORAX`,`a`.`avacintura` AS `CINTURA`,`a`.`avaabdomen` AS `ABDOMEN`,`a`.`avaquadril` AS `QUADRIL`,`a`.`avapanturrilha` AS `PANTURRILHA`,`a`.`avaculotes` AS `CULOTES`,`a`.`avabatinicial` AS `BATIMENTO_INICIAL`,`a`.`avabatfinal` AS `BATIMENTO_FINAL`,`a`.`avacoxa` AS `COXA`,`a`.`avafuncod` AS `COD_FUNC`,`f`.`funnome` AS `FUNCIONARIO`,`a`.`avamatcod` AS `MATRICULA`,`al`.`alunome` AS `ALUNO` from (((`avaliacao` `A` join `matricula` `M` on((`m`.`matcod` = `a`.`avamatcod`))) join `funcionario` `F` on((`f`.`funcod` = `a`.`avafuncod`))) join `aluno` `AL` on((`al`.`alucod` = `m`.`matalucod`)));

--
-- Definition of view `v_matricula`
--

DROP TABLE IF EXISTS `v_matricula`;
DROP VIEW IF EXISTS `v_matricula`;
CREATE ALGORITHM=UNDEFINED DEFINER=`sada`@`localhost` SQL SECURITY DEFINER VIEW `v_matricula` AS select `m`.`matcod` AS `CODIGO`,`m`.`matalucod` AS `MATALUCOD`,`a`.`alunome` AS `ALUNO`,`m`.`matfuncod` AS `MATFUNCOD`,`f`.`funnome` AS `FUNCIONARIO`,`m`.`matdtinicio` AS `INICIO`,`m`.`matdtvenc` AS `VENCIMENTO`,`m`.`matstatus` AS `STATUS` from ((`matricula` `M` join `aluno` `A` on((`a`.`alucod` = `m`.`matalucod`))) join `funcionario` `F` on((`f`.`funcod` = `m`.`matfuncod`)));

--
-- Definition of view `v_usuarios`
--

DROP TABLE IF EXISTS `v_usuarios`;
DROP VIEW IF EXISTS `v_usuarios`;
CREATE ALGORITHM=UNDEFINED DEFINER=`sada`@`localhost` SQL SECURITY DEFINER VIEW `v_usuarios` AS select `u`.`usucod` AS `usucod`,`u`.`usulogin` AS `usulogin`,`u`.`ususenha` AS `ususenha`,`u`.`usutipo` AS `usutipo`,`u`.`usualucod` AS `usualucod`,`u`.`usufuncod` AS `usufuncod`,`a`.`alunome` AS `ALUNOME`,`f`.`funnome` AS `FUNNOME` from ((`usuario` `U` left join `aluno` `A` on((`a`.`alucod` = `u`.`usualucod`))) left join `funcionario` `F` on((`f`.`funcod` = `u`.`usufuncod`)));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
