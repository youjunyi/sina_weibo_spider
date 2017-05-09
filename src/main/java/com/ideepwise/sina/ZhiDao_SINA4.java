package com.ideepwise.sina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

public class ZhiDao_SINA4 {
	
	static final String NET = "http://iask.sina.com.cn";
	public static void main(String[] args) throws Exception {
		
		/*BufferedWriter allUrl = new BufferedWriter(new FileWriter("D:\\nlp\\sina\\zhidao\\url3.sql",true));
		BufferedReader br = new BufferedReader(new FileReader("D:\\nlp\\sina\\zhidao\\url2.sql"));*/
		
		final String allData[] = {
				"http://iask.sina.com.cn/c/1202-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1144-goodAnswer-1-new.html@http://iask.sina.com.cn/c/853-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1146-goodAnswer-1-new.html@http://iask.sina.com.cn/c/847-goodAnswer-1-new.html@http://iask.sina.com.cn/c/120-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1145-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1187-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1159-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1162-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1156-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1076-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1158-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1160-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1163-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1157-goodAnswer-1-new.html@http://iask.sina.com.cn/c/855-goodAnswer-1-new.html@http://iask.sina.com.cn/c/846-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1161-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1017-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1203-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1133-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1205-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1206-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1132-goodAnswer-1-new.html@http://iask.sina.com.cn/c/852-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1141-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1143-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1142-goodAnswer-1-new.html",
				"http://iask.sina.com.cn/c/849-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1112-goodAnswer-1-new.html@http://iask.sina.com.cn/c/995-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1111-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1047-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1110-goodAnswer-1-new.html@http://iask.sina.com.cn/c/996-goodAnswer-1-new.html@http://iask.sina.com.cn/c/777-goodAnswer-1-new.html@http://iask.sina.com.cn/c/935-goodAnswer-1-new.html@http://iask.sina.com.cn/c/931-goodAnswer-1-new.html@http://iask.sina.com.cn/c/937-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1091-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1084-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1086-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1087-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1085-goodAnswer-1-new.html@http://iask.sina.com.cn/c/936-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1083-goodAnswer-1-new.html@http://iask.sina.com.cn/c/940-goodAnswer-1-new.html@http://iask.sina.com.cn/c/939-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1082-goodAnswer-1-new.html@http://iask.sina.com.cn/c/941-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1080-goodAnswer-1-new.html@http://iask.sina.com.cn/c/938-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1081-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1090-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1088-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1089-goodAnswer-1-new.html@http://iask.sina.com.cn/c/15803-goodAnswer-1-new.html@http://iask.sina.com.cn/c/15805-goodAnswer-1-new.html@http://iask.sina.com.cn/c/15806-goodAnswer-1-new.html@http://iask.sina.com.cn/c/15804-goodAnswer-1-new.html",
				"http://iask.sina.com.cn/c/15802-goodAnswer-1-new.html@http://iask.sina.com.cn/c/15801-goodAnswer-1-new.html@http://iask.sina.com.cn/c/15807-goodAnswer-1-new.html@http://iask.sina.com.cn/c/859-goodAnswer-1-new.html@http://iask.sina.com.cn/c/858-goodAnswer-1-new.html@http://iask.sina.com.cn/c/916-goodAnswer-1-new.html@http://iask.sina.com.cn/c/922-goodAnswer-1-new.html@http://iask.sina.com.cn/c/905-goodAnswer-1-new.html@http://iask.sina.com.cn/c/921-goodAnswer-1-new.html@http://iask.sina.com.cn/c/898-goodAnswer-1-new.html@http://iask.sina.com.cn/c/901-goodAnswer-1-new.html@http://iask.sina.com.cn/c/897-goodAnswer-1-new.html@http://iask.sina.com.cn/c/896-goodAnswer-1-new.html@http://iask.sina.com.cn/c/902-goodAnswer-1-new.html@http://iask.sina.com.cn/c/895-goodAnswer-1-new.html@http://iask.sina.com.cn/c/990-goodAnswer-1-new.html@http://iask.sina.com.cn/c/203-goodAnswer-1-new.html@http://iask.sina.com.cn/c/986-goodAnswer-1-new.html@http://iask.sina.com.cn/c/985-goodAnswer-1-new.html@http://iask.sina.com.cn/c/989-goodAnswer-1-new.html@http://iask.sina.com.cn/c/993-goodAnswer-1-new.html@http://iask.sina.com.cn/c/988-goodAnswer-1-new.html@http://iask.sina.com.cn/c/991-goodAnswer-1-new.html@http://iask.sina.com.cn/c/992-goodAnswer-1-new.html@http://iask.sina.com.cn/c/987-goodAnswer-1-new.html@http://iask.sina.com.cn/c/983-goodAnswer-1-new.html@http://iask.sina.com.cn/c/984-goodAnswer-1-new.html@http://iask.sina.com.cn/c/980-goodAnswer-1-new.html@http://iask.sina.com.cn/c/981-goodAnswer-1-new.html@http://iask.sina.com.cn/c/979-goodAnswer-1-new.html@http://iask.sina.com.cn/c/976-goodAnswer-1-new.html@http://iask.sina.com.cn/c/978-goodAnswer-1-new.html",
				"http://iask.sina.com.cn/c/974-goodAnswer-1-new.html@http://iask.sina.com.cn/c/975-goodAnswer-1-new.html@http://iask.sina.com.cn/c/955-goodAnswer-1-new.html@http://iask.sina.com.cn/c/952-goodAnswer-1-new.html@http://iask.sina.com.cn/c/954-goodAnswer-1-new.html@http://iask.sina.com.cn/c/951-goodAnswer-1-new.html@http://iask.sina.com.cn/c/953-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1094-goodAnswer-1-new.html@http://iask.sina.com.cn/c/946-goodAnswer-1-new.html@http://iask.sina.com.cn/c/199-goodAnswer-1-new.html@http://iask.sina.com.cn/c/191-goodAnswer-1-new.html@http://iask.sina.com.cn/c/948-goodAnswer-1-new.html@http://iask.sina.com.cn/c/945-goodAnswer-1-new.html@http://iask.sina.com.cn/c/771-goodAnswer-1-new.html@http://iask.sina.com.cn/c/195-goodAnswer-1-new.html@http://iask.sina.com.cn/c/942-goodAnswer-1-new.html@http://iask.sina.com.cn/c/194-goodAnswer-1-new.html@http://iask.sina.com.cn/c/193-goodAnswer-1-new.html@http://iask.sina.com.cn/c/218-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1099-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1098-goodAnswer-1-new.html@http://iask.sina.com.cn/c/216-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1040-goodAnswer-1-new.html@http://iask.sina.com.cn/c/839-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1105-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1003-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1004-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1005-goodAnswer-1-new.html@http://iask.sina.com.cn/c/106-goodAnswer-1-new.html@http://iask.sina.com.cn/c/107-goodAnswer-1-new.html@http://iask.sina.com.cn/c/108-goodAnswer-1-new.html"
				
		};
		
		for(int i=0;i<allData.length;i++){
			final int j = i;
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					runSpider(allData[j],j+"");
				}
			}).start();
		}
		
		
		
	}
	
	public static void runSpider(String data,String file){
		String jarFilePath = ZhiDao_SINA4.class.getProtectionDomain().getCodeSource().getLocation().getFile();  
        File file2 = new File(jarFilePath);
       String path =  file2.getAbsolutePath().substring(0, file2.getAbsolutePath().lastIndexOf("\\"));
        File file3 = new File(path+"\\questoin"+file);
        if(!file3.exists()){
        	file3.mkdirs();
        }
		String allUrl[] = data.split("@");
		
		
		for(int i=0;i<allUrl.length;i++){
		try{Utils u = new Utils();
			HtmlPage connection = u.connection(allUrl[i]);
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
			
			BufferedWriter file1 = new BufferedWriter(new FileWriter(file3.getAbsolutePath()+"\\"+title.replace("/", "")+".sql",true));
			for (HtmlElement htmlElement : elementsByTagName2) {
				try{
				String href = htmlElement.getAttribute("href");
				String target = htmlElement.getAttribute("target");
				if(href.contains(".html")&&target.equals("_blank")){
					String qUrl =NET+ href;
					int writeAnswer = writeAnswer(qUrl,file1);
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
			System.out.println("外围异常   "+allUrl[i]);
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
