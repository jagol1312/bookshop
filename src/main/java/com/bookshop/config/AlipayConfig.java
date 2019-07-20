package com.bookshop.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {


	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101100659806";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCX6f9y5R/2U0nesPvY8BLtmMVXnhRiHpMggvGEPowCMtkD98jelbNxV/of1aWnLHtdMzLjBP6L3qQvaN+CrndDM0jmRtSeQjvAObhMkXlb+QeulXv1Akr+rZfmEmjsHDJ30r8RuMfyErVPmgYPLeVFYPGeePdLY2Z4e7WR+71OJotbATClJUXGgFlYWD+tUErxFQyNlxTaEYkG+mbTL+iQWzZo+/URAOtrQwjk0rxjDgEd9biX3a3chuC5Aso4zV0FhcHQnjBHxfCYNeHZ3rF3o0V474g5Yn6KIlA3lv4dOF2DMiSlaQ2sMEBqGFyMG7QYnSnxEwXGS0QE6Zai3LOvAgMBAAECggEASRAh7ACG/tHQFWrJtezBLcTsJNfCte4+lMRlpFTwwZ3zAhWKZoNZaooFoon+wgiCzLBzKPyd8GeET8kAtrlyYr+nsBkg6XyxgCJCoZDV7YY4S59Znt0bdKHJDL6N+AEXxMl3W+6jO6oDyeUqROKuAlA95RoI1hlYj1nwWeXZhlwFgQ7DB0sE6boGY/UHa8ZflODj6l/RSZlSOypfW8OvSN/Joz8CyeJq1fVs8wGZTI4r6hz+cFz9wvZzTRBUsgQVK+aQZGMdffpvbk44adQ0HABZspNCkhNdDhi0cxfqCwDciVbwP5UZsyThb7ie98/yR3UM9Glcf8v446rQEI/AAQKBgQDO1jBTYgPLWf4izA6BjOhv+S8LgXljgfI/ugR5TjS21f121weDm+k/5WDn27Az/OTnF/QrgrL0bXDOWOnvXSjSk2eO0naZYl3hszz7z2WMiswDQYiAF5kR8OE8LiI9treq5AdU+i+NIO8cY6Usp8a0xw+045Qn4HOK6LNWYZF9AQKBgQC8BdIhb7gexWcNgBwrBHwJAkpiKGojXPlgAVuXs/+F1UXyfFAjamTzk6fjXwsKwM3wPhJgAwLYXLqqpbhrFSOSoflS3xF7mQ/X/B9PGr6NyuNMMuEDJov3HNiUEjEzgwDENkUc74jTYnZAtOPdHFlVai7yxEhpIQfdG+wFCShArwKBgBCFlXijpK/ysVUwPZxlmA+RtQtTPVIQiDxE7KSP9bABKez7QjNmGdyrwvDiYJHWiqZXCH8NQqQ5tcAV5CHlrCQd+DC1aRnZfKxRt/ZZW8A9OgysiG3K9MQ1HJjFCqEpXkR9UISeM3wvfEolP4VwpXO9TujhIO2KhVA8RE8LHicBAoGAVgCvmh248VJIDfPQbpjRh2llzro/ukc3xgM4+w4ZTgU8xmq4WUq8PHIItEbwDQ4d5Mrvqr0V2xOnSpEc5tY3fEXcflyMaaWciqXHSG6qRL9N8re/FqrWEMVAB5K+sJo2iolsWYzzYAVEo9pZ8/SYpCo6qU7A9F1odGnmLnN+1v8CgYAJAx33/orgskAPNYz8MHpJ0r2YYmbGvYKkObuBxdeLxYDjKG/MA75RlOmw0791vT1Xs0udkK9lPUtkFsEEw94CcQMtkhBvcdSOI66dmswxYCoWqyAMQMBwcmMfSugdyZc01v4Dz/VuHhgkvVec4O48jMwOlWF+ki3ybl3NM3r8/g==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl+n/cuUf9lNJ3rD72PAS7ZjFV54UYh6TIILxhD6MAjLZA/fI3pWzcVf6H9Wlpyx7XTMy4wT+i96kL2jfgq53QzNI5kbUnkI7wDm4TJF5W/kHrpV79QJK/q2X5hJo7Bwyd9K/EbjH8hK1T5oGDy3lRWDxnnj3S2NmeHu1kfu9TiaLWwEwpSVFxoBZWFg/rVBK8RUMjZcU2hGJBvpm0y/okFs2aPv1EQDra0MI5NK8Yw4BHfW4l92t3IbguQLKOM1dBYXB0J4wR8XwmDXh2d6xd6NFeO+IOWJ+iiJQN5b+HThdgzIkpWkNrDBAahhcjBu0GJ0p8RMFxktEBOmWotyzrwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://39.106.86.107:8080/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://39.106.86.107:8080/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "G:\\";



    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

