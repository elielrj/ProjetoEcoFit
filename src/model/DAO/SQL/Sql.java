package model.DAO.SQL;

public final class SQL  {

        public static  String BAIRRO_CREATE = "INSERT INTO bairro(nome,status) VALUES(?,?)";
        public static  String BAIRRO_RETRIVE_ALL =  "SELECT id,nome,status FROM bairro";
        public static  String BAIRRO_RETRIVE_ONE_ID =  "SELECT id,nome,status FROM bairro WHERE bairro.id=?";
        public static  String BAIRRO_UPDATE =  "UPDATE bairro SET nome=?,status=?  WHERE id=?";
        public static  String BAIRRO_DELETE =  "DELETE FROM bairro WHERE id = ?";

        public static  String CIDADE_CREATE = "INSERT INTO cidade(nome,status) VALUES(?,?)";
        public static  String CIDADE_RETRIVE_ALL =  "SELECT id,nome,status FROM cidade";
        public static  String CIDADE_RETRIVE_ONE_ID =   "SELECT id,nome,status FROM cidade WHERE cidade.id = ?";
        public static  String CIDADE_UPDATE =   "UPDATE cidade SET nome=?,status=? WHERE id = ?";
        public static  String CIDADE_DELETE =   "DELETE FROM cidade WHERE id=?";
}

