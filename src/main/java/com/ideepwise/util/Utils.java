package com.ideepwise.util;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Utils {
	
	public  HtmlPage  connection(String url) throws Exception{
		URL url3 = new URL(url);
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try{	
		webClient.setJavaScriptTimeout(5000);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		webClient.getOptions().setCssEnabled(false); // 禁用css支持
		webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		webClient.getOptions().setTimeout(100000); // 设置连接超时时间
													// ，这里是10S。如果为0，则无限期等待
		webClient.addCookie("_T_WM=d36e8fbc9b466f761e06619415fa2d75; ALF=1496564957; SCF=Av0_qefPgHgEOrLgkKOLOQrH3mPvdJ0a5-2UcsxAbJXElpNgrjUAXcI4UJ-we9lEEGhyolZ-cSxoMijvganAqQs.; SUB=_2A250CEhfDeRhGeBM6loS9SbOwj2IHXVX82gXrDV6PUJbktANLRHNkW0QjpUO9z0b_bDOEqStP4_sBmP1hw..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WF-gs6_rWUWiR6NZPBm8jlh5JpX5o2p5NHD95Qceo2Re0-Reo.pWs4DqcjMi--Ri-8si-zNi--4iKn7iKn4i--ciKyFi-24eBtt; SUHB=0h153v0w2xCCPS; SSOLoginState=1493973007; M_WEIBOCN_PARAMS=featurecode%3D20000320%26oid%3D4100821102470259%26lfid%3D1005056218358291_-_WEIBO_SECOND_PROFILE_LIKE_WEIBO%26luicode%3D20000174", url3, "");
		
//		webClient.addRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		webClient.addRequestHeader("Accept-Accept-Encoding", "gzip, deflate, sdch");
//		webClient.addRequestHeader("Referer", "http://d.weibo.com/102803_ctg1_4288_-_ctg1_4288?from=faxian_hot&mod=fenlei");
//		webClient.addRequestHeader("Accept", "*/*");
//		webClient.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//		webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
//		webClient.addRequestHeader("X-Requested-With", "XMLHttpRequest");
//		webClient.addRequestHeader("Connection", "keep-alive");
//		webClient.addRequestHeader("Host", "d.weibo.com");
//		webClient.addRequestHeader("GET", "/p/aj/proxy?api=http://contentrecommend.mobile.sina.cn/dot/dot.lua&__rnd=1493118507266 HTTP/1.1");
		//webClient.addCookie("BAIDUID=1B42610E101031058AB7D56F481E2411:FG=1; BIDUPSID=1B42610E101031058AB7D56F481E2411; PSTM=1491013090; HMACCOUNT=C22E8CC93F9AEFB9; PSINO=2; H_PS_PSSID=1458_21088_22158; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598", url3, "");
		//webClient.getOptions().setDoNotTrackEnabled(false);
		//webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);  
		Thread.sleep(800);
		HtmlPage page = webClient.getPage(url3);
		return page;
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		webClient.getCurrentWindow().getJobManager().removeAllJobs();  
        //webClient.close();  
        System.gc();  
	}
	return null;
	}
	
	public static void main(String[] args) throws Exception {
		
		URL url3 = new URL("https://passport.weibo.cn/signin/login");
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.setJavaScriptTimeout(5000);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		webClient.getOptions().setCssEnabled(false); // 禁用css支持
		webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		webClient.getOptions().setTimeout(100000); 
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);  
		Thread.sleep(800);
		HtmlPage connection = webClient.getPage(url3);
		DomElement username = connection.getElementById("loginName");
		DomElement password = connection.getElementById("loginPassword");
		
		username.setAttribute("value", "15555530123");
		password.setAttribute("value", "aa123321");
		DomElement elementById = connection.getElementById("loginAction");
		
		connection = elementById.click();
		Thread.sleep(3000);
		connection = webClient.getPage("https://weibo.cn/comment/F1FGudo1W?uid=2656274875&rl=0#cmtfrm");
		Thread.sleep(3000);
		System.out.println(connection.asText());
	}
}
