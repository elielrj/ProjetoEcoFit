package model.DAO.SQL;

public final class SQL  {

        public static  String BAIRRO_CREATE = "INSERT INTO bairro(nome,status,cidadeid) VALUES(?,?,?)";
        public static  String BAIRRO_RETRIVE_ALL =  "SELECT id,nome,status,cidadeid FROM bairro";
        public static  String BAIRRO_RETRIVE_ONE_ID =  "SELECT id,nome,status,cidadeid FROM bairro WHERE bairro.id=?";
        public static  String BAIRRO_RETRIVE_ID_CITY =  "SELECT id,nome,status,cidadeid FROM bairro WHERE bairro.cidadeid=?";
        public static  String BAIRRO_UPDATE =  "UPDATE bairro SET nome=?,status=?,cidadeid=?  WHERE id=?";
        public static  String BAIRRO_DELETE =  "DELETE FROM bairro WHERE id = ?";

        public static  String CIDADE_CREATE = "INSERT INTO cidade(nome,status) VALUES(?,?)";
        public static  String CIDADE_RETRIVE_ALL =  "SELECT id,nome,status FROM cidade";
        public static  String CIDADE_RETRIVE_ONE_ID =   "SELECT id,nome,status FROM cidade WHERE cidade.id = ?";
        public static  String CIDADE_UPDATE =   "UPDATE cidade SET nome=?,status=? WHERE id = ?";
        public static  String CIDADE_DELETE =   "DELETE FROM cidade WHERE id=?";
        
        public static  String ENDERECO_CREATE = "INSERT INTO endereco(logradouro,numero,bairroId,cep,status) VALUES(?,?,?,?,?)";
        public static  String ENDERECO_RETRIVE_ALL = "SELECT id,logradouro,numero,bairroId, cep,status FROM endereco";
        public static  String ENDERECO_RETRIVE_ID = "SELECT  id,logradouro,numero,bairroId, cep,status FROM endereco  WHERE id=?";
        public static  String ENDERECO_RETRIVE_LOGRADOURO_AND_CEP = "SELECT  id,logradouro,numero,bairroId, cep,status FROM endereco  WHERE logradouro=? and cep=?";
        public static  String ENDERECO_UPDATE = "UPDATE endereco SET logradouro=?,numero=?,bairroId=?,cep=?,status=? WHERE id=?";
        public static  String ENDERECO_DELETE = "DELETE FROM endereco WHERE id = ?";
        
        public static  String ESTOQUE_CREATE = "INSERT INTO estoque(produtoid,quantidade) VALUES(?,?)";
        public static  String ESTOQUE_RETRIVE_ALL =  "SELECT id,produtoid,quantidade FROM estoque";
        public static  String ESTOQUE_RETRIVE_ONE_ID =  "SELECT id,produtoid,quantidade FROM estoque WHERE estoque.id=?";
        public static  String ESTOQUE_RETRIVE_ONE_ID_PRODUTO_DO_ESTOQUE =  "SELECT id,produtoid,quantidade FROM estoque WHERE estoque.produtoid=?";
        public static  String ESTOQUE_UPDATE =  "UPDATE estoque SET produtoid=?,quantidade=?  WHERE id=?";
        public static  String ESTOQUE_DELETE =  "DELETE FROM estoque WHERE id = ?";
}

