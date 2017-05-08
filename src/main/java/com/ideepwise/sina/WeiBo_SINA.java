package com.ideepwise.sina;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.net.URL;
import java.util.Random;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
/**
 * 存储cookie的类
 * @author seven
 *
 */
class CookieClone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 珍惜每一次登录的机会，如果频繁登录可能会被封号
	 * 初始化登录手机版新浪微博，以后一直使用这个带有登录cookie的浏览器webClient
	 * 登录以后一直操作这个浏览器模拟最真实的点击
	 */
	CookieClone(){
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try{
		webClient.setJavaScriptTimeout(5000);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
		webClient.getOptions().setCssEnabled(false); // 禁用css支持
		webClient.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
		webClient.getOptions().setTimeout(100000); 
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setThrowExceptionOnScriptError(false);  
		Thread.sleep(800);
		HtmlPage connection = webClient.getPage("https://passport.weibo.cn/signin/login");
		DomElement username = connection.getElementById("loginName");
		DomElement password = connection.getElementById("loginPassword");
		username.setAttribute("value", "15555530123");
		password.setAttribute("value", "aa123321");
		DomElement elementById = connection.getElementById("loginAction");
		connection = elementById.click();
		Thread.sleep(3000);
		setWeb(webClient);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			webClient.getCurrentWindow().getJobManager().removeAllJobs();  
	        //webClient.close();  
	        System.gc();  
		}
	}
	
	private WebClient web;

	public WebClient getWeb() {
		return web;
	}

	private void setWeb(WebClient web) {
		this.web = web;
	}
	
}
/**
 * sina微博手机版爬虫
 * @author seven
 *
 */
public class WeiBo_SINA {
	
	public static void main(String[] args) throws Exception {
		CookieClone cc = new CookieClone();
		String url = "https://weibo.cn/u/5187664653?page=1";
		BufferedWriter weibo = new BufferedWriter(new FileWriter("D:\\nlp\\sina\\weibo\\dengchao1.sql",true));
		WebClient web = cc.getWeb();
		HtmlPage page = web.getPage(url);
		HtmlPage comment = null;
		DomElement elementById = page.getElementById("pagelist");
		String asText = elementById.asText();
		
		int pageNum = Integer.parseInt(asText.substring(asText.indexOf("/")+1, asText.lastIndexOf("页")));
		for(int i = 1;i<pageNum+1;i++){
		try{
			if(i!=1){
				page = web.getPage(url.substring(0, url.length()-1)+i);
			}
		DomNodeList<DomElement> elementsByTagName = page.getElementsByTagName("div");
		
		for (DomElement domElement : elementsByTagName) {
			if(domElement.getAttribute("class").equals("c")){
				DomNodeList<HtmlElement> elementsByTagName2 = domElement.getElementsByTagName("a");
				for (HtmlElement htmlElement : elementsByTagName2) {
					if(htmlElement.getAttribute("href").contains("comment")){
						comment = htmlElement.click();
						commentWriter(weibo,comment,cc);
					}
				}
			}
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
	}
	/**
	 * 微博评论和翻页
	 * @param weibo
	 * @param comment
	 */
	public static void commentWriter(BufferedWriter weibo,HtmlPage comment,CookieClone cc ){
		try{
		weibo.newLine();
		weibo.write(comment.asText());
		weibo.newLine();
		weibo.write("-------------------------------------第一页微博内容和评论--------------------------------");
		weibo.flush();
		DomElement elementById = comment.getElementById("pagelist");
		String asText = elementById.asText();
		
		int page = Integer.parseInt(asText.substring(asText.indexOf("/")+1, asText.lastIndexOf("页")));
		String urlComment = comment.getUrl().toString().replace("#cmtfrm", "")+"&page=";
		for(int i =2;i<=page+1;i++){
		try{
			comment = cc.getWeb().getPage(urlComment+i);
			weibo.newLine();
			weibo.write(comment.asText());
			weibo.flush();
			Random r = new Random();
			int nextInt = r.nextInt(5000);
			while(nextInt<2000){
				nextInt = r.nextInt(5000);
			}
			Thread.sleep(nextInt);
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		weibo.newLine();
		weibo.write("-------------------------------------一条微博内容和评论结束--------------------------------");
		weibo.flush();
		System.out.println("一条微博完毕");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
