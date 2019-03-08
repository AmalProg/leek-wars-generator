package com.leekwars.game;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

public class Util {

	private static Random random = new Random();
	private static HashSet<Integer> primes = null;

	public static int getRandom(int min, int max) {
		if (max - min + 1 <= 0)
			return 0;
		return min + random.nextInt(max - min + 1);
	}

	public static boolean isPrime(int value) {
		if (primes == null) {
			primes = new HashSet<Integer>();
			int values[] = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691 };
			for (int i = 0; i < values.length; i++)
				primes.add(values[i]);
		}
		return primes.contains(value);
	}

	public static String getHexaColor(int color) {
		String retour = Integer.toString(color & 0xFFFFFF, 16);
		while (retour.length() < 6)
			retour = "0" + retour;
		return retour;
	}

	public static String[] jsonArrayToStringArray(JSONArray array) {
		String[] res = new String[array.size()];
		for (int i = 0; i < array.size(); ++i) {
			res[i] = array.getString(i);
		}
		return res;
	}

	public static void save(JSON data, String file) {
		File f = new File(file);
		try {
			PrintWriter out = new PrintWriter(f);
			out.append(data.toJSONString());
			out.close();
		} catch (Exception e) {
			ErrorManager.exception(e);
		}
	}

	public static JSONArray readJSONArray(String file) {
		File f = new File(file);
		if (!f.exists())
			return null;
		try {
			BufferedInputStream reader = new BufferedInputStream(new FileInputStream(f));
			ByteArrayOutputStream datas = new ByteArrayOutputStream();
			byte b[] = new byte[256];
			int len;
			while ((len = reader.read(b)) != -1) {
				datas.write(b, 0, len);
			}
			reader.close();
			return JSON.parseArray(new String(datas.toByteArray()));

		} catch (Exception e) {
			ErrorManager.exception(e);
		}
		return null;
	}

	public static String getMD5(String data) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] md5_data = md.digest(data.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder(2 * md5_data.length);
			for (byte b : md5_data) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (Exception e) {}
		return "";
	}
}