package com.bookshop.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

public class DESUtils {

	private static Key key;//密钥key
	private static String KEY_STR = "myKey";
	private static String CHARSETNAME = "UTF-8";
	private static String ALGORITHM = "DES";

	static {
		try {
			//生成DES算法对象
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			//运用SHA1安全策略
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			//设置上密钥种子
			secureRandom.setSeed(KEY_STR.getBytes());			
			generator.init(secureRandom);
			//生成密钥
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
/*
 * 获取加密后的信息
 */
	public static String getEncryptString(String str) {
		//基于base64编码，将byte[]转换成string
		try {
			//按utf8编码
			byte[] bytes = str.getBytes(CHARSETNAME);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			//初始化加密信息
			cipher.init(Cipher.ENCRYPT_MODE, key);
			//加密
			byte[] doFinal = cipher.doFinal(bytes);
			return Base64.encodeBase64String(doFinal);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
/*
 * 获取解密后的信息
 */

	public static String getDecryptString(String str) {
		try {
			byte[] bytes = Base64.decodeBase64(str);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return new String(doFinal, CHARSETNAME);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptString("root"));
		System.out.println(getEncryptString("123456"));
	}

}