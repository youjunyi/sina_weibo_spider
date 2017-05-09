package com.ideepwise.sina;


import java.io.BufferedWriter;
import java.io.FileWriter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class User_WeiBo {
	
	public static void main(String[] args) throws Exception {
		try{
		BufferedWriter allUrl = new BufferedWriter(new FileWriter("D:\\nlp\\sina\\weibo\\urls2.sql",true));
		CookieClone cc = new CookieClone();
		WebClient web = cc.getWeb();
		
		HtmlPage page = web.getPage("https://weibo.cn/v2star/");
		HtmlPage first = null;
		HtmlPage second = null;
		DomNodeList<DomElement> elementsByTagName = page.getElementsByTagName("div");
		boolean b = false;
		for (DomElement domElement : elementsByTagName) {
			try{
			if(domElement.getAttribute("class").equals("c")){
				if(domElement.asText().contains("娱乐")){
					DomNodeList<HtmlElement> elementsByTagName2 = domElement.getElementsByTagName("a");
					for (HtmlElement htmlElement : elementsByTagName2) {
					try{
						if(htmlElement.asText().contains("航天")||b){
							b = true;
						first = htmlElement.click();
						DomNodeList<DomElement> elementsByTagName3 = first.getElementsByTagName("div");
						DomElement domElement2 = elementsByTagName3.get(6);
						DomNodeList<HtmlElement> elementsByTagName4 = domElement2.getElementsByTagName("a");
						for (HtmlElement htmlElement2 : elementsByTagName4) {
							try{
							//具体页数
							second = htmlElement2.click();
							HtmlAnchor anchorByText = null;
							do{
								anchorByText = null;
							DomNodeList<DomElement> elementsByTagName5 = second.getElementsByTagName("td");
							
							for (DomElement domElement3 : elementsByTagName5) {
								if(domElement3.asText().contains("粉丝")){
									
									 DomNodeList<HtmlElement> elementsByTagName6 = domElement3.getElementsByTagName("a");	
									 HtmlElement htmlElement3 = elementsByTagName6.get(0);
									 
									String url = "https://weibo.cn"+ htmlElement3.getAttribute("href")+"?page=1";
									String name = htmlElement3.asText();
									allUrl.newLine();
									allUrl.write(name+"@"+url);
									allUrl.flush();
								}
							}
							try{ anchorByText = second.getAnchorByText("下页");
							second = anchorByText.click();Thread.sleep(3600);
							}catch(Exception e){
								
							}
							}while(anchorByText!=null);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						}
							
					}catch(Exception e){
						e.printStackTrace();
					}
					}
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			Thread.sleep(1000*60*30);
		}
		
	}
}
