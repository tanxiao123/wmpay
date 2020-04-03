package com.wmpay.util;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GeneralTools {
  public static String getRandom() {
    String randomStr = String.valueOf((Math.random() * 9.0D + 1.0D) * 100000.0D);
    if (randomStr.length() > 6)
      randomStr = randomStr.substring(0, 6); 
    return randomStr;
  }
  
  public static String getRandom(int length) {
    String randomStr = String.valueOf((int)((Math.random() * 9.0D + 1.0D) * Math.pow(10.0D, length)));
    if (randomStr.length() > length)
      randomStr = randomStr.substring(0, length); 
    return randomStr;
  }
  
  public static String getShortUrl(String longUrl) {
    String rValue = longUrl;
    try {
      String urlKey = "3271760578";
      String queryurl = "http://api.t.sina.com.cn/short_url/shorten.xml?source=" + urlKey + "&url_long=" + 
        longUrl;
      URL url = new URL(queryurl);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String inputline = in.readLine();
      rValue = parseXML(inputline);
    } catch (Exception exception) {}
    return rValue;
  }
  
  private static String parseXML(String inputline) {
    String shorurl = "";
    try {
      SAXReader reader = new SAXReader();
      Document document = reader.read(new ByteArrayInputStream(inputline.getBytes("UTF-8")));
      Element root = document.getRootElement();
      Element db_element = root.element("url");
      Element urlElement = db_element.element("url_short");
      shorurl = urlElement.getText();
    } catch (DocumentException documentException) {
    
    } catch (Exception exception) {}
    return shorurl;
  }
  
  public static String getUUID() {
    String uuid = UUID.randomUUID().toString();
    return uuid.toLowerCase().replaceAll("-", "");
  }
  
  public static String getTimestamp() {
    String timestamp = String.valueOf((new Date()).getTime() / 1000L);
    return timestamp;
  }
  
  public static String MD5(String data) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] array = md.digest(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    byte b;
    int i;
    byte[] arrayOfByte1;
    for (i = (arrayOfByte1 = array).length, b = 0; b < i; ) {
      byte item = arrayOfByte1[b];
      sb.append(Integer.toHexString(item & 0xFF | 0x100).substring(1, 3));
      b++;
    } 
    return sb.toString().toUpperCase();
  }
  
  public static String HMACSHA256(String data, String key) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    byte b;
    int i;
    byte[] arrayOfByte1;
    for (i = (arrayOfByte1 = array).length, b = 0; b < i; ) {
      byte item = arrayOfByte1[b];
      sb.append(Integer.toHexString(item & 0xFF | 0x100).substring(1, 3));
      b++;
    } 
    return sb.toString().toUpperCase();
  }
  
  public static Map<String, String> xmlToMap(String strXML) throws Exception {
    try {
      Map<String, String> data = new HashMap<>();
      DocumentBuilder documentBuilder = newDocumentBuilder();
      InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
      org.w3c.dom.Document doc = documentBuilder.parse(stream);
      doc.getDocumentElement().normalize();
      NodeList nodeList = doc.getDocumentElement().getChildNodes();
      for (int idx = 0; idx < nodeList.getLength(); idx++) {
        Node node = nodeList.item(idx);
        if (node.getNodeType() == 1) {
        	org.w3c.dom.Element element = (org.w3c.dom.Element)node;
          data.put(element.getNodeName(), element.getTextContent());
        } 
      } 
      try {
        stream.close();
      } catch (Exception exception) {}
      return data;
    } catch (Exception ex) {
      throw ex;
    } 
  }
  
  public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
    documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    documentBuilderFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
    documentBuilderFactory.setXIncludeAware(false);
    documentBuilderFactory.setExpandEntityReferences(false);
    return documentBuilderFactory.newDocumentBuilder();
  }
  
  public static Document newDocument() throws ParserConfigurationException {
    return (Document)newDocumentBuilder().newDocument();
  }
  
  public static String GetUniqueSerial(String prefix) {
    SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmssSSSS");
    return String.valueOf(prefix) + df.format(new Date()) + getRandom(4);
  }
  
  public static String getTimeNow() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(new Date());
  }
}
