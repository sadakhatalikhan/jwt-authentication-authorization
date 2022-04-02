package com.jwt.auth.utility;

import java.security.MessageDigest;

public class AppUtility {

	public static void main(String[] args) {

	}

	public static String pwdHashing(String passwordToHash) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(passwordToHash.getBytes());
			byte[] bytes = md.digest();

			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
