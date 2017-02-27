package com.payeco.model;

import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.payeco.util.MD5;
import com.payeco.util.Toolkit;


public class MerchantMessage  {

	private static final long serialVersionUID = 8769203519691723123L;
	private String version="";//�汾��
	private String procCode="";//��Ϣ����
	private String processCode="";//������
	private String accountNo="";//����
	private String amount="";//���
	private String currency="";//����
	
	private String remark="";//��ע
	private String terminalNo="";//�ն˺�
	private String merchantNo="";//�̻���
	private String merchantOrderNo="";//�̻�������
	private String orderFrom="";//������Դ
	private String language="";//����
	private String description="";//��������
	private String orderType="";//�µ�����
	private String acqSsn="";//ϵͳ���ٺ�
	private String reference="";//ϵͳ�ο�
	private String transDatetime="";//����ʱ��
	private String uiLanguage = "";//UI����
	private String transData="";//��������
	private String synAddress="";//ͬ����ַ
	private String asynAddress="";//�첽��ַ
	
	private String respCode="";//��Ӧ��
	private String orderState="";//����״̬
	private String upsNo="";//������ˮ��
	private String tsNo="";//�����ն���ˮ��
	private String orderNo="";//�µ����صĶ�����
 
	private String mac="";//У����

   private String sdkExtData="";

	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		if(validLength(version, "Version", 0, 99)){
			this.version = version;
		}
	}
	public String getProcCode() {
		return procCode;
	}

	public void setProcCode(String procCode) {
		if(validLength(procCode, "ProcCode", 4, 4)){
			if(validType(procCode, "ProcCode", "d")){
				this.procCode = procCode;
			}
		}
	}
	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		if(validLength(processCode, "ProcessCode", 6, 6)){
			this.processCode = processCode;
		}
	}
	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		if(validLength(accountNo, "AccountNo", 0, 99)){
			this.accountNo = accountNo;
		}
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		if(validLength(amount, "Amount", 0, 12)){
			this.amount = amount;
		}
	}
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		if(validLength(currency, "Currency", 3, 3)){
			this.currency = currency;
		}
	}
	public String getCurCode(){
		return currency;
	}
	public String getSynAddress() {
		return synAddress;
	}

	public void setSynAddress(String synAddress) {
		if(validLength(synAddress, "SynAddress", 0, 99)){
			this.synAddress = synAddress;
		}
	}
	public String getAsynAddress() {
		return asynAddress;
	}

	public void setAsynAddress(String asynAddress) {
		if(validLength(synAddress, "SynAddress", 0, 99)){
			this.asynAddress = asynAddress;
		}
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		if(validLength(remark, "Remark", 0, 99)){
			this.remark = remark;
		}
	}
	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		if(validLength(terminalNo, "TerminalNo", 0, 99)){
			this.terminalNo = terminalNo;
		}
	}
	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		if(validLength(merchantNo, "MerchantNo", 0, 99)){
			this.merchantNo = merchantNo;
		}
	}
	public String getMerchantOrderNo() {
		return merchantOrderNo;
	}

	public void setMerchantOrderNo(String merchantOrderNo) {
		if(validLength(merchantOrderNo, "MerchantOrderNo", 0, 99)){
			this.merchantOrderNo = merchantOrderNo;
		}
	}
	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		if(validLength(orderFrom, "OrderFrom", 2, 2)){
			this.orderFrom = orderFrom;
		}
	}
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		if(validLength(language, "Language", 2, 2)){
			this.language = language;
		}
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		if(validLength(orderType, "OrderType", 2, 2)){
			this.orderType = orderType;
		}
	}
	public String getAcqSsn() {
		return acqSsn;
	}

	public void setAcqSsn(String acqSsn) {
		if(validLength(acqSsn, "AcqSsn", 6, 6)){
			this.acqSsn = acqSsn;
		}
	}
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		if(validLength(reference, "Reference", 0, 99)){
			this.reference = reference;
		}
	}
	public String getTransDatetime() {
		return transDatetime;
	}

	public void setTransDatetime(String transDatetime) {
		//if(validLength(transDatetime, "TransDatetime", 10, 10)){
			this.transDatetime = transDatetime;
		//}
	}
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		if(validLength(mac, "Mac", 32, 32)){
			this.mac = mac;
		}
	}
	
	public String computeMac(String pass) {
		MD5 md5 = new MD5();
		return md5.getMD5ofStr(getMacString() + " " + pass);
	}
	
	
	
	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		if(validLength(transData, "TransData", 0, 999)){
			this.transData = transData;
		}
	}
	
	
	
	
	public String getRespCode() {
		return this.respCode;
	}
	public void setRespCode(String respCode) {
		if(validLength(respCode, "RespCode", 4, 4)){
			this.respCode = respCode;
		}
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		if(validLength(orderState, "OrderState", 2, 2)){
			this.orderState = orderState;
		}
	}
	public String getUpsNo() {
		return upsNo;
	}
	public void setUpsNo(String upsNo) {
		this.upsNo = upsNo;
	}
	public String getTsNo() {
		return tsNo;
	}
	public void setTsNo(String tsNo) {
		this.tsNo = tsNo;
	}
	private boolean validLength(String src, String name, int start, int end){
		if(!isNullOrEmpty(src)){
			if(src.length() < start || src.length() > end){
				throw new RuntimeException(name + " Length Invalid: " + src.length());
			}
		}
		return true;
	}

	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
	private boolean validType(String src, String name, String type){
		if(!isNullOrEmpty(src)){
			if("a".equals(type)){
				if(!Pattern.matches("^[a-zA-Z]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be Character");
				}
			}
			if("s".equals(type)){
				if(Pattern.matches("^[0-9a-zA-Z]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be Special Character");
				}
			}
			if("d".equals(type)){
				if(!Pattern.matches("^[0-9]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be Digit");
				}
			}
			if("n".equals(type)){
				if(!Pattern.matches("^[0-9\\.\\-\\+]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be Number");
				}
			}
			if("an".equals(type)){
				if(!Pattern.matches("^[0-9a-zA-Z]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be Number or Character except Special Character");
				}
			}
			if("ns".equals(type)){
				if(Pattern.matches("^[a-zA-Z]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be Number or Special Character");
				}
			}
			if("as".equals(type)){
				if(Pattern.matches("^[0-9]*$", src)){
					throw new RuntimeException(name + " Value Type Invalid: should be any Character except Number");
				}
			}
		}
		return true;
	}
	
	public boolean verifyMac(String pass){
		return getMac().equals(computeMac(pass));
	}
	
	private boolean isNullOrEmpty(String src){
		return src == null || "".equals(src.trim());
	}
	
	private static String getValue(String name, Document doc){
		return Toolkit.getElementValue(name, doc);
	}
	
	public String getMacString() {
		String src = "";
			src = getProcCode()
					+ getString(getAccountNo())
					+ getString(getProcessCode())
					+ getString(getAmount())
					+ getString(getTransDatetime())
					+ getString(getAcqSsn())
					+ getString(getOrderNo())
					+ getString(getTransData())
					+ getString(getReference())
					+ getString(getRespCode())
					+ getString(getTerminalNo())
					+ getString(getMerchantNo())
					+ getString(getMerchantOrderNo())
					+ getString(getOrderState());
			System.out.println(src);
		return src;
	}
	public String getString(String src) {
	    return (Toolkit.isNullOrEmpty(src) ? "" : (" " + src.trim()));
	}
	

	
	public MerchantMessage() {}
	
	
	private void createElement(Document doc, Element root, String value, String tag) {
		if(!Toolkit.isNullOrEmpty(value) && !Toolkit.isNullOrEmpty(tag)){
			Element element = doc.createElement(tag);
			element.appendChild(doc.createTextNode(value));
			root.appendChild(element);
		}
	}
	
	public String toXml() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement("x:NetworkRequest");
        root.setAttribute("xmlns:x", "http://www.payeco.com");
        root.setAttribute("xmlns:xsi", "http://www.w3.org");
        doc.appendChild(root);
        createElement(doc, root, getVersion(), "Version");
        createElement(doc, root, getProcCode(), "ProcCode");
        createElement(doc, root, getProcessCode(), "ProcessCode");
        createElement(doc, root, getAccountNo(), "AccountNo");
        createElement(doc, root, getAmount(), "Amount");
        createElement(doc, root, getCurrency(), "Currency");
        createElement(doc, root, getTerminalNo(), "TerminalNo");
        createElement(doc, root, getMerchantNo(), "MerchantNo");
        createElement(doc, root, getMerchantOrderNo(), "MerchantOrderNo");
        createElement(doc, root, getOrderNo(), "OrderNo");
        createElement(doc, root, getUiLanguage(), "UILanguage");
        createElement(doc, root, getDescription(), "Description");
        createElement(doc, root, getAcqSsn(), "AcqSsn");
        createElement(doc, root, getTransDatetime(), "TransDatetime");
        createElement(doc, root, getTransData(), "TransData");
        createElement(doc, root, getReference(), "Reference");
        createElement(doc, root, getRemark(), "Remark");
        createElement(doc, root, getSynAddress(), "SynAddress");
        createElement(doc, root, getAsynAddress(), "AsynAddress");
        createElement(doc, root, getRespCode(), "RespCode");
        createElement(doc, root, getOrderState(), "OrderState");
        createElement(doc, root, getUpsNo(), "UpsNo");
        createElement(doc, root, getTsNo(), "TsNo");
        createElement(doc, root, getOrderFrom(), "OrderFrom");
        createElement(doc, root, getSdkExtData(), "SdkExtData");
        createElement(doc, root, getMac(), "Mac");
        return Toolkit.getStringFromDocument(doc);
	}

	public String getUiLanguage() {
		return uiLanguage;
	}

	public void setUiLanguage(String uiLanguage) {
		this.uiLanguage = uiLanguage;
	}

	public String getSdkExtData() {
		return sdkExtData;
	}

	public void setSdkExtData(String sdkExtData) {
		this.sdkExtData = sdkExtData;
	}
	

	
	
}
