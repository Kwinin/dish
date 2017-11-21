package com.cj.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class KwininSMS {
/**
 * 
 * 		<!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
		<dependency>
			<groupId>commons-logging</groupId>
			<artifactId>commons-logging</artifactId>
			<version>1.1.1</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient -->
		<dependency>
			<groupId>commons-httpclient</groupId>
			<artifactId>commons-httpclient</artifactId>
			<version>3.1</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
		<dependency>
			<groupId>commons-codec</groupId>
			<artifactId>commons-codec</artifactId>
			<version>1.10</version>
		</dependency>
	public static void main(String[] args) throws Exception {

		
		//参数说明:
		 * 1.公司名称
		 * 2.网建秘钥
		 * 3.待发送的秘钥
		 * 4.消息文
		 * 5.验证码			
		sendMessage("kwinin","3e2c05***********","132******227","你好X先生欢迎注册我公司用户,您的验证码是:","54321");

	}
**/
	public static void sendMessage(String name,String key,String tel,String Text,String smsText) throws Exception {

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", name), new NameValuePair("Key", key),
				new NameValuePair("smsMob", tel),new NameValuePair("smsText", Text+smsText)};
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
		System.out.println(result); // 打印返回消息状态

		post.releaseConnection();

	}

}
