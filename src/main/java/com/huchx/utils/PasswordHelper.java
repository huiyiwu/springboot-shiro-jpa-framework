package com.huchx.utils;


import com.huchx.ApiContstants;
import com.huchx.entity.MUserEntity;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

/**
 * 密码加密工具
 */
public class PasswordHelper {
	//private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	public static void encryptPassword(MUserEntity user) {
		//String salt=randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),  ByteSource.Util.bytes(StringUtils.isEmpty( user.getSalt())?"":user.getSalt()), hashIterations).toHex();
		//String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
		user.setPassword(newPassword);
	}
	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		MUserEntity user = new MUserEntity();
		user.setName("huchx");
		user.setPassword("123456");
		user.setSalt(ApiContstants.PWD_SALT);
		passwordHelper.encryptPassword(user);
		System.out.println(user.getPassword());
	}
}
