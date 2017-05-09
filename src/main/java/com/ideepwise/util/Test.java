package com.ideepwise.util;

import java.util.Arrays;

public class Test {
	
	public static void main(String[] args) {
		
		String str = "今天天气今天天气今天天气";
		
		System.out.println(Arrays.toString(str.split("天气")));
		System.out.println(Arrays.toString(str.split("(天|气)")));
		System.out.println(str.replaceAll("(天|气)", ""));
	}
}
