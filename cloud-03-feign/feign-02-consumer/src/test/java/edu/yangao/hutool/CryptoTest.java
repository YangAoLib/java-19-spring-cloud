package edu.yangao.hutool;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SM4;
import org.junit.jupiter.api.Test;

public class CryptoTest {

    @Test
    void testAes() {
        AES aes = SecureUtil.aes();
        String encrypt = aes.encryptBase64("昵称");
        System.out.println(encrypt);
        System.out.println(aes.decryptStr(encrypt));
    }

    @Test
    void testCustomizeAes() {
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding);
        String encrypt = aes.encryptBase64("昵称");
        System.out.println(encrypt);
        System.out.println(aes.decryptStr(encrypt));
    }

    @Test
    void testSM4() {
        SM4 sm4 = SmUtil.sm4();

        String encrypt = sm4.encryptBase64("昵称");
        System.out.println(encrypt);
        System.out.println(sm4.decryptStr(encrypt));
    }
}
