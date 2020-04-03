package com.wmpay.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

	public static final String sendHttpGet(String url) {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().get().url(url).build();
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static final String sendHttpPost(String url, HashMap<String, String> paramsMap) {
		OkHttpClient client = new OkHttpClient();
		FormBody.Builder formBodyBuilder = new FormBody.Builder();
		Set<String> keySet = paramsMap.keySet();
		for (String key : keySet) {
			String value = paramsMap.get(key);
			formBodyBuilder.add(key, value);
		}
		FormBody formBody = formBodyBuilder.build();
		Request request = new Request.Builder().post(formBody).url(url).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static final String sendHttpPost(String url, String xmlStr) {
		try {
			RequestBody body = RequestBody.create(MediaType.parse("application/json"), xmlStr);
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(url).post(body).build();

			Response response;
			response = client.newCall(request).execute();
			return response.body().string();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static String getServerIp() {
		String sysType = System.getProperties().getProperty("os.name");
		if (sysType.toLowerCase().startsWith("win")) {
			String localIP = null;
			try {
				localIP = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				System.out.println(e.toString());
			}
			if (localIP != null)
				return localIP;
		} else {
			String ip = getIpByEthNum("eth0");
			if (ip != null)
				return ip;
		}
		return "-1";
	}

	private static String getIpByEthNum(String ethNum) {
		try {
			Enumeration<?> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				if (ethNum.equals(netInterface.getName())) {
					Enumeration<?> addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						InetAddress ip = (InetAddress) addresses.nextElement();
						if (ip != null && ip instanceof java.net.Inet4Address)
							return ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			System.out.println(e.toString());
		}
		return "-1";
	}

}
