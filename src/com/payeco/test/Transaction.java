package com.payeco.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import sun.util.BuddhistCalendar;

import com.payeco.model.MerchantMessage;
import com.payeco.util.MD5;
import com.payeco.util.Toolkit;



public class Transaction {
	
	private static Logger logger = Logger.getLogger(Transaction.class.getName());
	public static void main(String[] args) {
		orderQuery();
		//downFile();
	}
	
	//�˿�
	public static void  orderQuash(){

		try {
			
			String url = "http://test.payeco.com:9080/services/ApiV2ServerRSA";
			String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJ1fKGMV/yOUnY1ysFCk0yPP4bfOolC/nTAyHmoser+1yzeLtyYsfitYonFIsXBKoAYwSAhNE+ZSdXZs4A5zt4EKoU+T3IoByCoKgvpCuOx8rgIAqC3O/95pGb9n6rKHR2sz5EPT0aBUUDAB2FJYjA9Sy+kURxa52EOtRKolSmEwIDAQAB";
			
			String acqSsn = new SimpleDateFormat("HHmmss").format(new Date());
			String transDatetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			//������Ϣ
			MerchantMessage msg = new MerchantMessage();
			msg.setVersion("2.1.0");
			msg.setProcCode("0220");
			msg.setProcessCode("290000");
			msg.setMerchantNo("1462847066331");
			msg.setMerchantOrderNo("20160530180933");
			msg.setAcqSsn(acqSsn);
			msg.setTransDatetime(transDatetime);
			String mac = msg.computeMac("123456");
			msg.setMac(mac);

			String srcXml = msg.toXml();
 
			String key = Toolkit.random(24);
			String tmpXml = Toolkit.signWithMD5(key, srcXml, pubKey);
		
			TransactionClient  tc = new TransactionClient(url);
			String result = tc.transact(tmpXml);
			logger.info(result);
			
			String xml = Toolkit.verify(key,result);
			logger.info(xml);
			String src = Toolkit.getValue(xml,"ProcCode")
					+ Toolkit.getString(Toolkit.getValue(xml,"AccountNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"ProcessCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Amount"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransDatetime"))
					+ Toolkit.getString(Toolkit.getValue(xml,"AcqSsn"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransData"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Reference"))
					+ Toolkit.getString(Toolkit.getValue(xml,"RespCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TerminalNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantOrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderState")) + " " + "123456";
		
			String MAC = new MD5().getMD5ofStr(src);
			String orderState = Toolkit.getValue(xml,"OrderState").trim();
			if(MAC.equals(Toolkit.getValue(xml,"MAC"))){
				//mac verify success
				if("03".equals(orderState)){
					 logger.info("���˿�");
				}
			}
			
		} catch (Exception e) {
			 logger.error(Toolkit.toString(e));
		}
	}
	
	//�����˿�����
	public static void  adjustApply(){
		try {
			
			String url = "http://10.123.65.20:9080/services/ApiV2ServerRSA";
			String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJ1fKGMV/yOUnY1ysFCk0yPP4bfOolC/nTAyHmoser+1yzeLtyYsfitYonFIsXBKoAYwSAhNE+ZSdXZs4A5zt4EKoU+T3IoByCoKgvpCuOx8rgIAqC3O/95pGb9n6rKHR2sz5EPT0aBUUDAB2FJYjA9Sy+kURxa52EOtRKolSmEwIDAQAB";
			
			String acqSsn = new SimpleDateFormat("HHmmss").format(new Date());
			String transDatetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			String merchantOrderNo = "";
			String amount = "";
			//������Ϣ
			MerchantMessage msg = new MerchantMessage();
			msg.setVersion("2.1.0");
			msg.setProcCode("0220");
			msg.setProcessCode("290004");
			msg.setAmount(amount);
			msg.setMerchantNo("1462847066331");
			msg.setMerchantOrderNo(merchantOrderNo);
			msg.setAcqSsn(acqSsn);
			msg.setTransDatetime(transDatetime);

			String mac = msg.computeMac("123456");
			msg.setMac(mac);

			String srcXml = msg.toXml();
 
			String key = Toolkit.random(24);
			String tmpXml = Toolkit.signWithMD5(key, srcXml, pubKey);
		
			TransactionClient  tc = new TransactionClient(url);
			String result = tc.transact(tmpXml);
			logger.info(result);
			
			String xml = Toolkit.verify(key,result);
			logger.info(xml);
			String src = Toolkit.getValue(xml,"ProcCode")
					+ Toolkit.getString(Toolkit.getValue(xml,"AccountNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"ProcessCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Amount"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransDatetime"))
					+ Toolkit.getString(Toolkit.getValue(xml,"AcqSsn"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransData"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Reference"))
					+ Toolkit.getString(Toolkit.getValue(xml,"RespCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TerminalNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantOrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderState")) + " " + "123456";
		
			String MAC = new MD5().getMD5ofStr(src);
			String orderState = Toolkit.getValue(xml,"OrderState").trim();
			if(MAC.equals(Toolkit.getValue(xml,"MAC"))){
				//mac verify success
				if("03".equals(orderState)){
					 logger.info("���������У��ȴ�����");
				}
				adjustQuery("01", merchantOrderNo, acqSsn, transDatetime);
			}
			
		} catch (Exception e) {
			 logger.error(Toolkit.toString(e));
		}
	}
	
	//�����˿��ѯ
	public static void  adjustQuery(String amount,String merchantOrderNo,String acqSsn,String transDatetime){
		try {
			
			String url = "http://10.123.65.20:9080/services/ApiV2ServerRSA";
			String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJ1fKGMV/yOUnY1ysFCk0yPP4bfOolC/nTAyHmoser+1yzeLtyYsfitYonFIsXBKoAYwSAhNE+ZSdXZs4A5zt4EKoU+T3IoByCoKgvpCuOx8rgIAqC3O/95pGb9n6rKHR2sz5EPT0aBUUDAB2FJYjA9Sy+kURxa52EOtRKolSmEwIDAQAB";
			
			
			//������Ϣ
			MerchantMessage msg = new MerchantMessage();
			msg.setVersion("2.1.0");
			msg.setProcCode("0220");
			msg.setProcessCode("290004");
			msg.setAmount(amount);
			msg.setMerchantNo("1462847066331");
			msg.setMerchantOrderNo(merchantOrderNo);
			msg.setAcqSsn(acqSsn);		//���˲�ѯ��Ҫʹ�õ�������ʱ��acqSSn��transDatetime
			msg.setTransDatetime(transDatetime);
			String mac = msg.computeMac("123456");
			msg.setMac(mac);

			String srcXml = msg.toXml();
 
			String key = Toolkit.random(24);
			String tmpXml = Toolkit.signWithMD5(key, srcXml, pubKey);
		
			TransactionClient  tc = new TransactionClient(url);
			String result = tc.transact(tmpXml);
			logger.info(result);
			
			String xml = Toolkit.verify(key,result);
			logger.info(xml);
			String src = Toolkit.getValue(xml,"ProcCode")
					+ Toolkit.getString(Toolkit.getValue(xml,"AccountNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"ProcessCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Amount"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransDatetime"))
					+ Toolkit.getString(Toolkit.getValue(xml,"AcqSsn"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransData"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Reference"))
					+ Toolkit.getString(Toolkit.getValue(xml,"RespCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TerminalNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantOrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderState")) + " " + "123456";
		
			String MAC = new MD5().getMD5ofStr(src);
			if(MAC.equals(Toolkit.getValue(xml,"MAC"))){
					logger.info(Toolkit.getValue(xml,"Remark").trim());
			
			}
			
		} catch (Exception e) {
			 logger.error(Toolkit.toString(e));
		}
	}

	//������ѯ
	public static void  orderQuery(){
		try {
			
			String url = "http://10.123.65.20:9080/services/ApiV2ServerRSA";
			String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJ1fKGMV/yOUnY1ysFCk0yPP4bfOolC/nTAyHmoser+1yzeLtyYsfitYonFIsXBKoAYwSAhNE+ZSdXZs4A5zt4EKoU+T3IoByCoKgvpCuOx8rgIAqC3O/95pGb9n6rKHR2sz5EPT0aBUUDAB2FJYjA9Sy+kURxa52EOtRKolSmEwIDAQAB";
			
			String acqSsn = new SimpleDateFormat("HHmmss").format(new Date());
			String transDatetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			String merchantOrderNo = "20160601142102";
		//	String amount = "";
			//������Ϣ
			MerchantMessage msg = new MerchantMessage();
			msg.setVersion("2.1.0");
			msg.setProcCode("0120");
			msg.setProcessCode("310000");
			//msg.setAmount(amount);
			msg.setMerchantNo("1462847066331");
			msg.setMerchantOrderNo(merchantOrderNo);
			msg.setAcqSsn(acqSsn);
			msg.setTransDatetime(transDatetime);

			String mac = msg.computeMac("123456");
			msg.setMac(mac);

			String srcXml = msg.toXml();
 
			String key = Toolkit.random(24);
			String tmpXml = Toolkit.signWithMD5(key, srcXml, pubKey);
		
			TransactionClient  tc = new TransactionClient(url);
			String result = tc.transact(tmpXml);
			logger.info(result);
			
			String xml = Toolkit.verify(key,result);
			logger.info(xml);
			String src = Toolkit.getValue(xml,"ProcCode")
					+ Toolkit.getString(Toolkit.getValue(xml,"AccountNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"ProcessCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Amount"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransDatetime"))
					+ Toolkit.getString(Toolkit.getValue(xml,"AcqSsn"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TransData"))
					+ Toolkit.getString(Toolkit.getValue(xml,"Reference"))
					+ Toolkit.getString(Toolkit.getValue(xml,"RespCode"))
					+ Toolkit.getString(Toolkit.getValue(xml,"TerminalNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"MerchantOrderNo"))
					+ Toolkit.getString(Toolkit.getValue(xml,"OrderState")) + " " + "123456";
		
			String MAC = new MD5().getMD5ofStr(src);
			String orderState = Toolkit.getValue(xml,"OrderState").trim();
			if(MAC.equals(Toolkit.getValue(xml,"MAC"))){
				logger.info("OrderState="+orderState);
			}
			
		} catch (Exception e) {
			 logger.error(Toolkit.toString(e));
		}
	}
	
	//�����ļ�����
	public static void downFile(){

		String merchantNo = "1472181543236";	//1462847066331
		String acqSsn = new SimpleDateFormat("HHmmss").format(new Date());
		String transDatetime = new SimpleDateFormat("MMddHHmmss").format(new Date());
		String settleDate  = new SimpleDateFormat("yyyyMMdd").format(new Date());
		MerchantMessage msg = new MerchantMessage();
		msg.setVersion("2.0.0");
		msg.setProcCode("0120");
		msg.setProcessCode("310000");
		msg.setMerchantNo("02"+merchantNo);
		msg.setAcqSsn(acqSsn);
		msg.setTransDatetime(transDatetime);	//transDatetime
		//msg.setTerminalNo("");
		StringBuilder  sb = new StringBuilder();
		 sb.append("http://test.payeco.com:9080/third/OrderServerWeb?")
		.append("ProcCode="+msg.getProcCode())
		.append("&Version="+msg.getVersion())
		.append("&ProcessCode="+msg.getProcessCode())
		.append("&TransDatetime="+msg.getTransDatetime())
		.append("&AcqSsn="+msg.getAcqSsn())
		.append("&SettleDate="+"0927")	//settleDate
		.append("&MerchantNo="+msg.getMerchantNo())
		.append("&Mac="+msg.computeMac("123456"));
		
		logger.info(sb.toString());
		

		}
		
	
}
