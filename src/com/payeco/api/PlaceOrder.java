package com.payeco.qrorderen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.ListIterator;

import org.apache.log4j.Logger;


import com.payeco.model.MerchantMessage;
import com.payeco.test.SslConnection;
import com.payeco.test.Transaction;
import com.payeco.util.Toolkit;


/** 
 * @author fengmuhai
 * @date 2016-10-9 ����10:56:52 
 * @version 1.0  
 */
public class PlaceOrder {
	
	private static Logger logger = Logger.getLogger(PlaceOrder.class.getName());
	
	public static void main(String[] args) {
		new PlaceOrder().assemble();
	}
	
	/**
	 * ΢�š�֧����ɨ�� ��΢�Ź��ں��µ�demo�����ظ�ʽΪxml
	 * 1.΢��֧����ɨ���µ��ɹ������ض�ά��URL
	 * 2.΢�Ź��ںţ����ص���΢��֧���Ĳ���
	 */
	public String assemble(){
		
		try {
			String url = "http://test.payeco.com:9080/pay/services/ApiV2ServerRSA";
			String asynAddress = "http://test.payeco.com:9080/payecodemo/servlet/AsynServlet";	//�����첽֪ͨ��ַ

			String GDYILIAN_CERT_PUB_64="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDJ1fKGMV/yOUnY1ysFCk0yPP4bfOolC/nTAyHmoser+1yzeLtyYsfitYonFIsXBKoAYwSAhNE+ZSdXZs4A5zt4EKoU+T3IoByCoKgvpCuOx8rgIAqC3O/95pGb9n6rKHR2sz5EPT0aBUUDAB2FJYjA9Sy+kURxa52EOtRKolSmEwIDAQAB";
			
			String request_text = "";
			String srcXml="";
			String amount = "0.01";
			String curcode = Toolkit.getCurrency("01");
			String desc = "Test Description";
			String remark = "";
			
			String merchantPwd = "123456";//��Կ
			String merchantOrderNo = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String acqSsn = new SimpleDateFormat("HHmmss").format(new Date());
			String transDatetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			String merchantNo = "1462847066331";	//�����̻��ţ�1462847066331��1472181543236
			
			MerchantMessage msg = new MerchantMessage();
			msg.setVersion("2.1.0");
			msg.setProcCode("0200");
			msg.setProcessCode("190011");
			msg.setMerchantNo(merchantNo);
			msg.setMerchantOrderNo(merchantOrderNo);
			msg.setAcqSsn(acqSsn);
			msg.setTransDatetime(transDatetime);
			msg.setAmount(amount);
			msg.setCurrency(curcode);
			msg.setDescription(desc);
			msg.setAsynAddress(asynAddress);
			//����������Դ
			msg.setOrderFrom("34");		//30�����һɨ��,32΢��ɨ��,33֧����ɨ��,34΢�Ź��ں�
			msg.setTransData("|openid|");	//�޸ĳɶ�Ӧ��openid
			//msg.setSdkExtData("{\"geelyUserId\":\"001221\", \"walletUserId\":\"10234\"}");
			
			
			String mac = msg.computeMac(merchantPwd);
			msg.setMac(mac);
		
		    srcXml = msg.toXml();
		    
			String encryptKey = Toolkit.random(24);
			String pubKey =  GDYILIAN_CERT_PUB_64;
			
			String tmp = Toolkit.signWithMD5(encryptKey, srcXml, pubKey);
			request_text = Toolkit.bytePadLeft(tmp.length()+"", '0', 6) + tmp;
			String resp = sendAndGet(url, request_text.getBytes("utf-8"));
			resp = new String(new sun.misc.BASE64Decoder().decodeBuffer(resp), "utf-8");
			logger.info("�������ݣ�\n"+resp);   
			return resp;
		} catch (Exception e) {
			logger.info("assemble(): ", e);
		}
		return "";
	}
	
	public static String sendAndGet(String url, byte[] req) throws Exception {
    	HttpURLConnection connect = null;
        if (!url.toLowerCase().startsWith("https:")) {
          URL urlConnect = new URL(url);
          connect = (HttpURLConnection)urlConnect.openConnection();
        } else {
          SslConnection urlConnect = new SslConnection();
          connect = urlConnect.openConnection(url);
        };
        connect.addRequestProperty("content-type", "application/text;charset=UTF-8");
        connect.setReadTimeout(1000 * 40);
        connect.setConnectTimeout(10000);
        connect.setRequestMethod("POST");
        connect.setDoInput(true);
        connect.setDoOutput(true);
        connect.connect();
        BufferedOutputStream out = new BufferedOutputStream(connect.getOutputStream());
        out.write(req);
        out.flush();
        out.close();
        byte[] resp = readResponse(new BufferedInputStream(connect.getInputStream()));
        connect.getInputStream().close();
        connect.disconnect();
        return new String(resp, "utf-8");
	}

	
	
	public static byte[] readResponse(InputStream is) throws Exception {

		BufferedInputStream in = new BufferedInputStream(is);

		LinkedList<Httpbuf> bufList = new LinkedList<Httpbuf>();
		int size = 0;
		byte buf[];
		
		do {
			buf = new byte[128];
			int num = in.read(buf);
			if (num == -1)
				break;
			size += num;
			bufList.add(new Httpbuf(buf, num));
		} while (true);
		
		buf = new byte[size];
		int pos = 0;
		for (ListIterator<Httpbuf> p = bufList.listIterator(); p.hasNext();) {
			
			Httpbuf b = p.next();
			for (int i = 0; i < b.size;) {
				buf[pos] = b.buf[i];
				i++;
				pos++;
			}

		}
		return buf;
	}
	
}
class Httpbuf
{

	public byte buf[];
	public int size;

	public Httpbuf(byte b[], int s)
	{
		buf = b;
		size = s;
	}
}
