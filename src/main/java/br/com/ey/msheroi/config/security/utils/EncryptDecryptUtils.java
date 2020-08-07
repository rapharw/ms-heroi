package br.com.ey.msheroi.config.security.utils;

import org.jasypt.util.text.BasicTextEncryptor;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;


public class EncryptDecryptUtils {

    private static final String KEYWORD = "ZDMkYWYxMHxleV80M3JvSUAyMDIw";

    private EncryptDecryptUtils() {/**/}

    public static String decrypt(String msg){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPasswordCharArray(new String(
                                                Base64.decodeBase64(KEYWORD.getBytes(StandardCharsets.UTF_8)),
                                                StandardCharsets.UTF_8).toCharArray()
        );
        return textEncryptor.decrypt(msg);
    }
}
