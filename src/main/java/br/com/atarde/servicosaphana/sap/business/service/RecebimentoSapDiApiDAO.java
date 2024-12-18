package br.com.atarde.servicosaphana.sap.business.service;


public class RecebimentoSapDiApiDAO {
	/*
	 * private ConexaoSapUtil conexaoSapUtil; private SBOErrorMessage
	 * errorMessage; private BillOfExchangeTransaction
	 * oBillOfExchangeTransaction; private Empresa empresa;
	 * 
	 * private void initObjetosNaRequisicao(Empresa model) throws
	 * TSApplicationException {
	 * 
	 * this.empresa = model;
	 * 
	 * if (!TSUtil.isEmpty(this.empresa)) {
	 * 
	 * this.conexaoSapUtil = ConexaoSapUtil.getConnection(this.empresa);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public Recebimento inserir(Recebimento model) throws
	 * TSApplicationException {
	 * 
	 * int retorno;
	 * 
	 * this.initObjetosNaRequisicao(model.getEmpresa());
	 * 
	 * this.oBillOfExchangeTransaction = new
	 * BillOfExchangeTransaction(conexaoSapUtil
	 * .getCompany().getBusinessObject(SBOCOMConstants
	 * .BoObjectTypes_oBillOfExchangeTransactions));
	 * 
	 * this.oBillOfExchangeTransaction.getLines().setBillOfExchangeNo(model.
	 * getContasReceber
	 * ().getModalidadePagamentoBoleto().getBoleto().getId().intValue());
	 * 
	 * this.oBillOfExchangeTransaction.getLines().setBillOfExchangeType(
	 * SBOCOMConstants.BoBOETypes_Incoming);
	 * 
	 * this.oBillOfExchangeTransaction.setPostingDate(new Date());
	 * 
	 * this.oBillOfExchangeTransaction.setTaxDate(new Date());
	 * 
	 * this.oBillOfExchangeTransaction.setIsBoeReconciled(SBOCOMConstants.
	 * BoYesNoEnum_tNO);
	 * 
	 * if (Constantes.STATUS_BOLETO_PAGO.equals(model.getContasReceber().
	 * getModalidadePagamentoBoleto().getBoleto().getStatus().getId())) {
	 * 
	 * Boleto boleto = new
	 * BoletoDAO().obter(model.getContasReceber().getModalidadePagamentoBoleto
	 * ().getBoleto());
	 * 
	 * if (Constantes.STATUS_BOLETO_ENVIADO.equals(boleto.getStatus().getId()))
	 * {
	 * 
	 * this.oBillOfExchangeTransaction.setStatusFrom(SBOCOMConstants.
	 * BoBOTFromStatus_btfs_Sent);
	 * 
	 * this.oBillOfExchangeTransaction.setStatusTo(SBOCOMConstants.
	 * BoBOTToStatus_btts_Generated);
	 * 
	 * retorno = this.salvar();
	 * 
	 * if (retorno == 0) {
	 * 
	 * this.inserir(model);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * if (Constantes.STATUS_BOLETO_GERADO.equals(boleto.getStatus().getId())) {
	 * 
	 * this.oBillOfExchangeTransaction.setStatusFrom(SBOCOMConstants.
	 * BoBOTFromStatus_btfs_Generated);
	 * 
	 * this.oBillOfExchangeTransaction.setStatusTo(SBOCOMConstants.
	 * BoBOTToStatus_btts_Deposit);
	 * 
	 * this.oBillOfExchangeTransaction.getDeposits().setBankCountry("BR");
	 * 
	 * this.oBillOfExchangeTransaction.getDeposits().setBankAccount("1.1.1.2.2");
	 * 
	 * this.oBillOfExchangeTransaction.getDeposits().setBankBranch("3551");
	 * 
	 * this.oBillOfExchangeTransaction.getDeposits().setBankDepositAccount("299")
	 * ;
	 * 
	 * this.oBillOfExchangeTransaction.getDeposits().setPostingType(SBOCOMConstants
	 * .BoDepositPostingTypes_dpt_Collection);
	 * 
	 * retorno = this.salvar();
	 * 
	 * if (retorno == 0) {
	 * 
	 * this.inserir(model);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * if
	 * (Constantes.STATUS_BOLETO_DEPOSITADO.equals(boleto.getStatus().getId()))
	 * {
	 * 
	 * this.oBillOfExchangeTransaction.setStatusFrom(SBOCOMConstants.
	 * BoBOTFromStatus_btfs_Deposited);
	 * 
	 * this.oBillOfExchangeTransaction.setStatusTo(SBOCOMConstants.
	 * BoBOTToStatus_btts_Paid);
	 * 
	 * retorno = this.salvar();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * this.oBillOfExchangeTransaction.release();
	 * 
	 * this.oBillOfExchangeTransaction = null;
	 * 
	 * Runtime r = Runtime.getRuntime();
	 * 
	 * r.gc();
	 * 
	 * return model;
	 * 
	 * }
	 * 
	 * private int salvar() throws TSApplicationException {
	 * 
	 * int retorno;
	 * 
	 * retorno = this.oBillOfExchangeTransaction.add();
	 * 
	 * if (retorno != 0) {
	 * 
	 * errorMessage = conexaoSapUtil.getCompany().getLastError();
	 * 
	 * throw new TSApplicationException("Error " + errorMessage.getErrorCode() +
	 * " : " + errorMessage.getErrorMessage());
	 * 
	 * } else { //
	 * System.out.println("Nota cadastrada com Sucesso!!, uHuuuuuu"); }
	 * 
	 * return retorno; }
	 */
}
