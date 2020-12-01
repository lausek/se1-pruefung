package de.dhbw.aes;

import javax.crypto.spec.SecretKeySpec;

public class Application {
    private SecretKeySpec secretKey;
    private byte[] key;

    public static void main(String... args) {
        Application application = new Application();
        AesCryptor cryptor = new AesCryptor();

        final String secretKey = "dhbw$20^20_";

        String plainMessage = "***I***0614***";
        String encryptedString = cryptor.encrypt(plainMessage, secretKey);
        String decryptedString = cryptor.decrypt(encryptedString, secretKey);

        System.out.println(plainMessage);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }

}