package com.retinder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.testng.ITestContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class mailGenerator {

	public String generateHtml(ITestContext context) throws IOException {
		String content=readToString("ReportTemplat.ftl");
		content=content.replace("${suiteName}", context.getSuite().getName());
		content=content.replace("${testStarttime}", context.getStartDate().toString());
		content=content.replace("${testEndtime}", context.getEndDate().toString());
		content=content.replace("${testmethods}", String.valueOf(context.getAllTestMethods().length));
		content=content.replace("${testPass}",String.valueOf( context.getPassedTests().size()));
		content=content.replace("${testFailed}", String.valueOf( context.getFailedTests().size()));
		if (context.getFailedTests().size()>0) {
			content=content.replace("${testresult}", "不通过");

		}else {
			content=content.replace("${testresult}", "通过");
		}
		System.out.println(content);
		return content;
	}

	public String readToString(String fileName) {
		String encoding = "UTF-8";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

}
