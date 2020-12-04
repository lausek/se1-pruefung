package de.dhbw.aes;

public class Application {
    public static void main(String... args) {
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