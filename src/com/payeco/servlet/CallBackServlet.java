package com.payeco.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.payeco.util.MD5;
import com.payeco.util.Toolkit;

/**
 * ͬ��֪ͨ�����߼�demo
 * @author user
 */
public class CallBackServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		final String CharSet = "UTF-8";
	
		response.setContentType("text/html");
		request.setCharacterEncoding(CharSet);
        response.setCharacterEncoding(CharSet);
		PrintWriter out = response.getWriter();
        String response_text = request.getParameter("response_text");
       
		String xml = "";
		if(response_text != null && !"".equals(response_text)){
			
                        //����������xml������ģ��뽫��������ע�͵����еķ�����������Զ���UrlDecode
			String urlText = URLDecoder.decode(response_text, CharSet);
			xml = new String(new sun.misc.BASE64Decoder().decodeBuffer(urlText),CharSet);
			out.println("response_text Base64 decode after<br/>");
			out.println("<textarea rows=\"40\" cols=\"100\">" + xml + "</textarea>");
			out.println();
		    String merchantPwd = "123456";
			String src = getValue(xml,"ProcCode")
					+ getString(getValue(xml,"AccountNo"))
					+ getString(getValue(xml,"ProcessCode"))
					+ getString(getValue(xml,"Amount"))
					+ getString(getValue(xml,"TransDatetime"))
					+ getString(getValue(xml,"AcqSsn"))
					+ getString(getValue(xml,"OrderNo"))
					+ getString(getValue(xml,"TransData"))
					+ getString(getValue(xml,"Reference"))
					+ getString(getValue(xml,"RespCode"))
					+ getString(getValue(xml,"TerminalNo"))
					+ getString(getValue(xml,"MerchantNo"))
					+ getString(getValue(xml,"MerchantOrderNo"))
					+ getString(getValue(xml,"OrderState")) + " " + merchantPwd;
			out.println();
			String MAC = new MD5().getMD5ofStr(src);
			String orderState = getValue(xml,"OrderState").trim();
			if(MAC.equals(getValue(xml,"MAC"))){
				//mac verify success
				if("02".equals(orderState)){
					//payment success
					
				}else if("05".equals(orderState)){
					//payment fail
					String errorCode = getValue(xml,"RespCode");
					
				}else{
					//payment in-process
					String errorCode = getValue(xml,"RespCode");
				}
			}
			out.println();
			//֪ͨ�������ճɹ�
			//notify payeco
			out.print("0000");
			out.flush();
			out.close();
		}
	}
	private String getValue(String xml, String name){
		if(xml==null || "".equals(xml.trim()) 
				|| name == null || "".equals(name.trim())){
			return "";
		}
		String tag = "<" + name + ">";
		String endTag = "</" + name + ">";
		if(!xml.contains(tag) || !xml.contains(endTag)){
			return "";
		}
		String value = xml.substring(xml.indexOf(tag) + tag.length(), xml.indexOf(endTag));
		if(value != null && !"".equals(value)){
			return value;
		}
		return "";
	}
	
	private String getString(String src) {
	    return (Toolkit.isNullOrEmpty(src) ? "" : (" " + src.trim()));
	}
	
}
