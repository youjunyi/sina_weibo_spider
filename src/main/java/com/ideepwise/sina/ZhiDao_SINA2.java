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

public class ZhiDao_SINA2 {
	
	static final String NET = "http://iask.sina.com.cn";
	public static void main(String[] args) throws Exception {
		
		/*BufferedWriter allUrl = new BufferedWriter(new FileWriter("D:\\nlp\\sina\\zhidao\\url3.sql",true));
		BufferedReader br = new BufferedReader(new FileReader("D:\\nlp\\sina\\zhidao\\url2.sql"));*/
		
		
		String jarFilePath = ZhiDao_SINA2.class.getProtectionDomain().getCodeSource().getLocation().getFile();  
        File file2 = new File(jarFilePath);
       String path =  file2.getAbsolutePath().substring(0, file2.getAbsolutePath().lastIndexOf("\\"));
        File file3 = new File(path+"\\questoin");
        if(!file3.exists()){
        	file3.mkdirs();
        }
		String allUrl[] = "http://iask.sina.com.cn/c/866-goodAnswer-1-new.html@http://iask.sina.com.cn/c/869-goodAnswer-1-new.html@http://iask.sina.com.cn/c/870-goodAnswer-1-new.html@http://iask.sina.com.cn/c/867-goodAnswer-1-new.html@http://iask.sina.com.cn/c/868-goodAnswer-1-new.html@http://iask.sina.com.cn/c/93-goodAnswer-1-new.html@http://iask.sina.com.cn/c/865-goodAnswer-1-new.html@http://iask.sina.com.cn/c/863-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1222-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1067-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1066-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1068-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1064-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1065-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1216-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1024-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1218-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1125-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1126-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1019-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1020-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1124-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1023-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1026-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1022-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1215-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1123-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1021-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1217-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1025-goodAnswer-1-new.html@http://iask.sina.com.cn/c/54116217f2bc09ecc3cfc3c7-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1109-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1220-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1077-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1188-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1122-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1219-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1189-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1121-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1129-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1131-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1210-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1211-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1169-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1170-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1167-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1184-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1185-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1166-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1171-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1180-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1060-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1182-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1200-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1183-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1061-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1198-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1165-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1178-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1199-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1177-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1201-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1164-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1176-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1174-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1173-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1186-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1168-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1181-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1172-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1175-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1179-goodAnswer-1-new.html@http://iask.sina.com.cn/c/54364e36f2bc227f01ffb9d1-goodAnswer-1-new.html@http://iask.sina.com.cn/c/543647caf2bc227f01ffb9ce-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1062-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1212-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1134-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1135-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1139-goodAnswer-1-new.html@http://iask.sina.com.cn/c/848-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1214-goodAnswer-1-new.html@http://iask.sina.com.cn/c/851-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1213-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1137-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1140-goodAnswer-1-new.html@http://iask.sina.com.cn/c/850-goodAnswer-1-new.html@http://iask.sina.com.cn/c/5493f1baf2bc765a5b41c28a-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1138-goodAnswer-1-new.html@http://iask.sina.com.cn/c/5437b88bf2bc227f01ffb9dd-goodAnswer-1-new.html@http://iask.sina.com.cn/c/543de015f2bc227f01ffb9e1-goodAnswer-1-new.html@http://iask.sina.com.cn/c/54583dedf2bc252c1299460f-goodAnswer-1-new.html@http://iask.sina.com.cn/c/54608b5bf2bc2871331f6b59-goodAnswer-1-new.html@http://iask.sina.com.cn/c/5461bfc7f2bc458b3f6bc391-goodAnswer-1-new.html@http://iask.sina.com.cn/c/546d88cff2bcc0e886ffeebd-goodAnswer-1-new.html@http://iask.sina.com.cn/c/54a9f9a8f2bc86f1ec2cdbe0-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1029-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1028-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1030-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1120-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1119-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1118-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1153-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1149-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1151-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1150-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1152-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1147-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1154-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1155-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1204-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1100-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1148-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1209-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1208-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1207-goodAnswer-1-new.html@http://iask.sina.com.cn/c/1128-goodAnswer-1-new.html".split("@");
		
		
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
			
			BufferedWriter file = new BufferedWriter(new FileWriter(file3.getAbsolutePath()+"\\"+title.replace("/", "")+".sql",true));
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
