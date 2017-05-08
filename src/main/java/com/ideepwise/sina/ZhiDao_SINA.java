package com.ideepwise.sina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ideepwise.util.Utils;

public class ZhiDao_SINA {
	
	static final String NET = "http://iask.sina.com.cn";
	public static void main(String[] args) throws Exception {
		
		BufferedWriter allUrl = new BufferedWriter(new FileWriter("D:\\nlp\\sina\\zhidao\\url3.sql",true));
		BufferedReader br = new BufferedReader(new FileReader("D:\\nlp\\sina\\zhidao\\url2.sql"));
		
		String line = "";
		
		while((line=br.readLine())!=null){
		try{
			Utils u = new Utils();
			HtmlPage connection = u.connection(line);
			DomNodeList<DomElement> elementsByTagName = connection.getElementsByTagName("div");
			String title = "";
			for (DomElement domElement : elementsByTagName) {
				if(domElement.getAttribute("class").equals("dib pr current breadcast-fl")){
					title += domElement.asText().trim()+"--";
				}
			}
			HtmlAnchor anchorByText = null;
			
			do{	
				try{
					anchorByText = null;
			 anchorByText = connection.getAnchorByText("下一页");
			}catch(ElementNotFoundException e){
				
			}
			try{
			DomElement elementById = connection.getElementById("q_questions_list");
			DomNodeList<HtmlElement> elementsByTagName2 = elementById.getElementsByTagName("a");
			
			BufferedWriter file = new BufferedWriter(new FileWriter("D:\\nlp\\sina\\zhidao\\"+title.replace("/", "")+".sql",true));
			for (HtmlElement htmlElement : elementsByTagName2) {
				try{
				String href = htmlElement.getAttribute("href");
				String target = htmlElement.getAttribute("target");
				if(href.contains(".html")&&target.equals("_blank")){
					String qUrl =NET+ href;
					int writeAnswer = writeAnswer(qUrl,file);
					System.out.println(writeAnswer);
				}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(anchorByText!=null){
				connection = anchorByText.click();
			}
			if(true){
				
			}
			}catch(Exception e){
				e.printStackTrace();
			}
				}while(anchorByText!=null);
		}catch(Exception e){
			System.out.println("外围异常   "+line);
		}
			
		}
	}
	
	public static int writeAnswer(String url,BufferedWriter file){
		try{Utils u = new Utils();
			HtmlPage connection = u.connection(url);
			String question = "";
			String answer = "";
			String other = "";
			boolean b = true;
		DomNodeList<DomElement> elementsByTagName = connection.getElementsByTagName("div");
		DomNodeList<DomElement> elementsByTagName2 = connection.getElementsByTagName("h1");
		for (DomElement domElement : elementsByTagName2) {
			question+=domElement.asText()+"   ";
		}
		
		for (DomElement domElement : elementsByTagName) {
			if(domElement.getAttribute("class").equals("question_text")){
				question = domElement.asText();
			}
			if((!answer.equals(""))&&domElement.getAttribute("class").equals("answer_text")){
				other += "@@@@@@@@@@@@@@@@@@@@@@@@@@@"+domElement.asText();
			}
			if(domElement.getAttribute("class").equals("answer_text")&&b){
				answer = domElement.asText();
				b= false;
			}
			
		}
		file.newLine();
		file.write(question);
		file.newLine();
		file.write("------------------------------------");
		file.newLine();
		file.write(answer);
		file.newLine();
		file.write("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		file.newLine();
		file.write(other);
		file.newLine();
		file.write("`````````````````````````````````````");
		file.flush();
		return 0;
		}catch(Exception e){
			System.out.println("写入文件异常");
		}
		return 1;
	}
}
