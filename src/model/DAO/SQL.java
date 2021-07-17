package model.DAO;

public final class SQL {

    public static String BAIRRO_CREATE = "INSERT INTO bairro(nome,status,cidadeid) VALUES(?,?,?)";
    public static String BAIRRO_RETRIVE_ALL = "SELECT id,nome,status,cidadeid FROM bairro";
    public static String BAIRRO_RETRIVE_ONE_ID = "SELECT id,nome,status,cidadeid FROM bairro WHERE bairro.id=?";
    public static String BAIRRO_RETRIVE_ID_CITY = "SELECT id,nome,status,cidadeid FROM bairro WHERE bairro.cidadeid=?";
    public static String BAIRRO_UPDATE = "UPDATE bairro SET nome=?,status=?,cidadeid=?  WHERE id=?";
    public static String BAIRRO_DELETE = "DELETE FROM bairro WHERE id=?";

    public static String CIDADE_CREATE = "INSERT INTO cidade(nome,status) VALUES(?,?)";
    public static String CIDADE_RETRIVE_ALL = "SELECT id,nome,status FROM cidade";
    public static String CIDADE_RETRIVE_ONE_ID = "SELECT id,nome,status FROM cidade WHERE cidade.id=?";
    public static String CIDADE_UPDATE = "UPDATE cidade SET nome=?,status=? WHERE id=?";
    public static String CIDADE_DELETE = "DELETE FROM cidade WHERE id=?";
    
    public static String COMPRA_CREATE = "INSERT INTO compra(datacompra,hora,datavencimento,observacao,valordesconto,valortotal,status,fornecedorid ) VALUES(?,?,?,?,?,?,?,?,?)";
    public static String COMPRA_RETRIVE_ALL = "SELECT id,datacompra,hora,datavencimento,observacao,valordesconto,valortotal,status,fornecedorid FROM compra";
    public static String COMPRA_RETRIVE_ONE_ID = "SELECT id,datacompra,hora,datavencimento,observacao,valordesconto,valortotal,status,fornecedorid FROM compra WHERE compra.id=?";
    public static String COMPRA_UPDATE = "UPDATE compra SET data=?,hora=?,datavencimento=  ?,observacao=?,valordesconto=?,valortotal=?,status=?,fornecedorid=? WHERE id=?";
    public static String COMPRA_DELETE = "DELETE FROM compra WHERE id =?";

    public static String ENDERECO_CREATE = "INSERT INTO endereco(logradouro,numero,bairroid,cep,status) VALUES(?,?,?,?,?)";
    public static String ENDERECO_RETRIVE_ALL = "SELECT id,logradouro,numero,bairroid,cep,status FROM endereco";
    public static String ENDERECO_RETRIVE_ID = "SELECT  id,logradouro,numero,bairroid,cep,status FROM endereco  WHERE id=?";
    public static String ENDERECO_RETRIVE_NUMERO_AND_LOGRADOURO_AND_BAIRRO = "SELECT  id FROM endereco WHERE numero=? and logradouro=? and bairroid=?";
    public static String ENDERECO_UPDATE = "UPDATE endereco SET logradouro=?,numero=?,bairroid=?,cep=?,status=? WHERE endereco.id=?";
    public static String ENDERECO_DELETE = "DELETE FROM endereco WHERE id=?";

    public static String ESTOQUE_CREATE = "INSERT INTO estoque(produtoid,quantidade) VALUES(?,?)";
    public static String ESTOQUE_RETRIVE_ALL = "SELECT id,produtoid,quantidade FROM estoque";
    public static String ESTOQUE_RETRIVE_ONE_ID = "SELECT id,produtoid,quantidade FROM estoque WHERE estoque.id=?";
    public static String ESTOQUE_RETRIVE_ONE_ID_PRODUTO_DO_ESTOQUE = "SELECT id,produtoid,quantidade FROM estoque WHERE estoque.produtoid=?";
    public static String ESTOQUE_RETRIVE_ONE_ID_DO_ESTOQUE = "SELECT id FROM estoque WHERE estoque.produtoid=?";
    public static String ESTOQUE_UPDATE = "UPDATE estoque SET produtoid=?,quantidade=? WHERE estoque.id=?";
    public static String ESTOQUE_DELETE = "DELETE FROM estoque WHERE id=?";

    public static String FORNECEFOR_CREATE = "INSERT INTO fornecedor(razaosocial,cnpj,inscricaoestadual,telefone1,telefone2,email,observacao,status,enderecoid,complemento) VALUES (?,?,?,?,?,?,?,?,?,?)";
    public static String FORNECEFOR_RETRIVE_ALL = "SELECT id,razaosocial,cnpj,inscricaoestadual,telefone1,telefone2,email,observacao,status,enderecoid,complemento FROM fornecedor";
    public static String FORNECEFOR_RETRIVE_ONE_ID = "SELECT id,razaosocial,cnpj,inscricaoestadual,telefone1,telefone2,email,observacao,status,enderecoid,complemento  FROM fornecedor WHERE  fornecedor.id=?";
    public static String FORNECEFOR_UPDATE = "UPDATE fornecedor SET razaosocial =?,cnpj=?,inscricaoestadual=?,telefone1=?,telefone2=?,email=?,observacao=?,status=?,enderecoid=?,complemento=? WHERE id =?";
    public static String FORNECEFOR_DELETE = "DELETE FROM fornecedor WHERE id=?";
    
    public static String ITEM_DE_COMPRA_CREATE = "INSERT INTO itemdecompra(quantidade,produtoid,subtotal,compraid) VALUES (?,?,?,?)";
    public static String ITEM_DE_COMPRA_RETRIVE_ALL = "SELECT id,quantidade,produtoid,subtotal,compraid FROM itemdecompra";
    public static String ITEM_DE_COMPRA_RETRIVE_ONE_ID = "SELECT id,quantidade,produtoid,subtotal,compraid FROM itemdecompra WHERE itemdecompra.id=?";
    public static String ITEM_DE_COMPRA_UPDATE = "UPDATE itemdecompra SET quantidade=?,produtoId=?,subtotal=?,compraId=? WHERE id=?";
    public static String ITEM_DE_COMPRA_DELETE = "DELETE FROM itemdecompra WHERE id=?";

    public static String ITEM_DE_VENDA_CREATE = "INSERT INTO itemdevenda( quantidade,vendaid,produtoid,subtotal) VALUES(?,?,?,?)";
    public static String ITEM_DE_VENDA_RETRIVE_ALL = "SELECT id,quantidade,vendaid,produtoid,subtotal FROM itemdevenda WHERE order bay id";
    public static String ITEM_DE_VENDA_RETRIVE_ONE_ID = "SELECT id,quantidade,vendaid,produtoid,subtotal FROM itemdevenda WHERE itemdevenda.id=?";
    public static String ITEM_DE_VENDA_UPDATE = "UPDATE itemdevenda SET quantidade=?,vendaid=?,produtoid=?,subtotal=? WHERE itemdevenda.id=?";
    public static String ITEM_DE_VENDA_DELETE = "DELETE FROM itemdevenda WHERE id=?";
    public static String ITEM_DE_VENDA_DELETE_TODOS_ID_VENDA = "SELECT id,quantidade,vendaid,produtoid,subtotal FROM itemdevenda WHERE itemdevenda.vendaid=?";
    public static String ITEM_DE_VENDA_RETRIVE_ALL_POR_VENDAID = "SELECT id,quantidade,vendaid,produtoid,subtotal FROM itemdevenda WHERE itemdevenda.vendaid=?";
    
    public static String PAGAR_CREATE = "INSERT INTO pagar(datapagamento,hora,valordesconto,valoracrescimo,valorpago,observacao,compraid,status) VALUES(?,?,?,?,?,?,?,?)";
    public static String PAGAR_RETRIVE_ALL = "SELECT id,datapagamento,hora,valordesconto,valoracrescimo,valorpago,observacao,compraid,status FROM pagar";
    public static String PAGAR_RETRIVE_ONE_ID = "SELECT id,datapagamento,hora,valordesconto,valoracrescimo,valorpago,observacao,compraid,status FROM pagar WHERE id=?";
    public static String PAGAR_UPDATE = "UPDATE pagar SET datapagamento=?,hora=?,valordesconto=?,valoracrescimo =?,valorpago=?,observacao=?,compraid=?,status=?WHERE id=?";
    public static String PAGAR_DELETE = "DELETE FROM pagar WHERE id=?";
    
    public static String PESSOA_FISICA_CREATE = "INSERT INTO pessoafisica(nome,rg,cpf,datanascimento,telefone1,telefone2,email,observacao,status,enderecoid,tipo,complemento) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    public static String PESSOA_FISICA_RETRIVE_ALL = "SELECT id,nome,rg,cpf,datanascimento,telefone1,telefone2,email,observacao,status,enderecoId,tipo,complemento FROM pessoafisica";
    public static String PESSOA_FISICA_RETRIVE_ALL_ALUNO = "SELECT id,nome,rg,cpf,datanascimento,telefone1,telefone2,email,observacao,status,enderecoid,tipo,complemento FROM pessoafisica where pessoafisica.tipo = 'aluno'";
    public static String PESSOA_FISICA_RETRIVE_ALL_PERSONAL = "SELECT id,nome,rg,cpf,datanascimento,telefone1,telefone2,email,observacao,status,enderecoid,tipo,complemento FROM pessoafisica where pessoafisica.tipo = 'personal'";
    public static String PESSOA_FISICA_RETRIVE_ONE_ID = "SELECT id,nome,rg,cpf,datanascimento,telefone1,telefone2,email,observacao,status,enderecoid,tipo,complemento FROM pessoafisica WHERE  pessoafisica.id=?";
    public static String PESSOA_FISICA_UPDATE = "UPDATE pessoafisica SET nome=?,rg=?,cpf=?,datanascimento=?,telefone1=?,telefone2=?,email=?,observacao=?,status=?,enderecoid=?,tipo=?,complemento=? WHERE pessoafisica.id=?";
    public static String PESSOA_FISICA_DELETE = "DELETE FROM pessoafisica WHERE id=?";
    
    public static String PRODUTO_CREATE = "INSERT INTO produto(descricao,unidadedecompra,unidadedevenda,correlacaounidade,valor,codigodebarras,status,observacao) VALUES(?,?,?,?,?,?,?,?)";
    public static String PRODUTO_RETRIVE_ALL = "SELECT id,descricao,unidadedecompra,unidadedevenda,correlacaounidade,valor,codigodebarras,status,observacao  FROM produto";
    public static String PRODUTO_RETRIVE_ONE_ID = "SELECT id,descricao,unidadedecompra,unidadedevenda,correlacaounidade,valor,codigodebarras,status,observacao FROM produto WHERE id=?";
    public static String PRODUTO_RETRIVE_ONE_COD_BARRAS = "SELECT id,descricao,unidadedecompra,unidadedevenda,correlacaounidade,valor,codigodebarras,status,observacao FROM produto WHERE produto.codigodebarras=?";
    public static String PRODUTO_RETRIVE_ONE_COD_BARRAS_SO = "SELECT codigodebarras FROM produto WHERE produto.codigodebarras=?";
    public static String PRODUTO_UPDATE = "UPDATE produto SET descricao=?,unidadedecompra=?,unidadedevenda=?,correlacaounidade=?,valor=?,codigodebarras=?,status=?,observacao=? WHERE produto.id=?";
    public static String PRODUTO_DELETE = "DELETE FROM produto WHERE id=?";
    
    public static String RECEBER_CREATE = "INSERT INTO receber(datarecebimento,hora,valordesconto,valoracrescimo,valorrecebido,observacao,vendaid) VALUES(?,?,?,?,?,?,?)";
    public static String RECEBER_RETRIVE_ALL = "SELECT id,data,hora,valorDeDescontoNegociado,valorDeAcrescimo,valorRecebido,observacao,vendaId FROM receber";
    public static String RECEBER_RETRIVE_ONE_ID = "SELECT id,data,hora,valorDeDescontoNegociado,valorDeAcrescimo,valorRecebido,observacao,vendaId FROM receber WHERE receber.id =?";
    public static String RECEBER_UPDATE = "UPDATE receber SET data =?,hora =?,valorDeDescontoNegociado =?,valorDeAcrescimo =?,valorRecebido =?,observacao=?,vendaId =? WHERE id=?";
    public static String RECEBER_DELETE = "DELETE FROM receber WHERE id =?";
    
    public static String VENDA_CREATE = "INSERT INTO venda(datavenda,hora,datavencimento,observacao,valordesconto,valortotal,status,pessoafisicaid,usercaixa) VALUES(?,?,?,?,?,?,?,?,?)";
    public static String VENDA_RETRIVE_ALL = "SELECT id,datavenda,hora,datavencimento,observacao,valordesconto,valortotal,status,pessoafisicaid,usercaixa FROM venda";
    public static String VENDA_RETRIVE_ONE_ID = "SELECT id,datavenda,hora,datavencimento,observacao,valordesconto,valortotal,status,pessoafisicaid,usercaixa FROM venda WHERE venda.id=?";
    public static String VENDA_RETRIVE_VENDA_OBJ = "SELECT id FROM venda WHERE venda.valortotal=? and venda.pessoafisicaid=? and venda.datavenda=?";
    public static String VENDA_UPDATE = "UPDATE venda SET datavenda=?,hora=?,datavencimento= ?,observacao=?,valordesconto=?,valortotal =?,status=?,pessoafisicaid=?,usercaixa=? WHERE id=?";
    public static String VENDA_DELETE = "DELETE FROM venda WHERE id=?";
   
    public static String CONTA_A_PAGAR_CREATE = "INSERT INTO contaapagar(compraid,valor,status) VALUES(?,?,?)";
    public static String CONTA_A_PAGAR_RETRIVE_ALL = "SELECT id,compraid,valor,status FROM contaapagar";
    public static String CONTA_A_PAGAR_RETRIVE_ONE_ID = "SELECT id,compraid,valor,status FROM contaapagar WHERE contaapagar.id=?";
    public static String CONTA_A_PAGAR_RETRIVE_ONE_ID_DA_COMPRA = "SELECT id,compraid,valor,status FROM contaapagar WHERE contaapagar.compraid=?";
    public static String CONTA_A_PAGAR_UPDATE = "UPDATE contaapagar SET compraid=?,valor=?,status=? WHERE id=?";
    public static String CONTA_A_PAGAR_DELETE = "DELETE FROM contaapagar WHERE id=?";
    
    public static String CONTA_A_RECEBER_CREATE = "INSERT INTO contaareceber(vendaid,valor,status) VALUES(?,?,?)";
    public static String CONTA_A_RECEBER_RETRIVE_ALL = "SELECT id,vendaid,valor,status FROM contaareceber";
    public static String CONTA_A_RECEBER_RETRIVE_ONE_ID = "SELECT id,vendaid,valor,status FROM contaareceber WHERE contaareceber.id=?";
    public static String CONTA_A_RECEBER_RETRIVE_ONE_ID_DA_VENDA = "SELECT id,vendaid,valor,status FROM contaareceber WHERE contaareceber.vendaid=?";
    public static String CONTA_A_RECEBER_UPDATE = "UPDATE contaareceber SET vendaid=?,valor=?,status=? WHERE id=?";
    public static String CONTA_A_RECEBER_DELETE = "DELETE FROM contaareceber WHERE id=?";


    




    
}
