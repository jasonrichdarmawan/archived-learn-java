package com.example.bankaccount.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PinHashingService {
  /**
   * method hash(String pin) is to hash the PIN.
   */
  public String hash(String pin) {
    byte[] salt = getSalt();
    int iterations = 65536;

    KeySpec keySpec = new PBEKeySpec(pin.toCharArray(), salt, iterations, 512);
    SecretKeyFactory secretKeyFactory;
    byte[] Hashed_PIN;
    try {
      secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512"); // HmacSHA1 max length is 160 bits while HmacSHA512 max length is 512 bits.
      Hashed_PIN = secretKeyFactory.generateSecret(keySpec).getEncoded();

      // TODO: thread safe.
      return iterations +
              ":" +
              toHex(salt) +
              ":" +
              toHex(Hashed_PIN);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
      return "invalid"; // @todo: rollback.
    }

//    byte[] Hashed_PIN = new byte[0];
//    try {
//      Hashed_PIN = secretKeyFactory.generateSecret(keySpec).getEncoded();
//    } catch (InvalidKeySpecException e) {
//      e.printStackTrace();
//    }
  }

  /**
   * method validate(String pin, String storedhashed_pin) is to validate the PIN.
   */
  public boolean validate(String pin, String storedHashed_PIN) {
    String[] parts = storedHashed_PIN.split(":");
    int iterations = Integer.parseInt(parts[0]);
    byte[] salt = fromHex(parts[1]);
    byte[] hash = fromHex(parts[2]);

    KeySpec keySpec = new PBEKeySpec(pin.toCharArray(), salt, iterations, hash.length * 8);
    SecretKeyFactory secretKeyFactory;
    /**
     * note: storedHashed_PIN is not Hashed_PIN
     * storedHashed_PIN iterations:salt:hash
     * Hashed_PIN is just the hash.
     */
    byte[] Hashed_PIN;
    try {
      secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
      Hashed_PIN = secretKeyFactory.generateSecret(keySpec).getEncoded();
      /**
       * Comparing the stored hash with the generated hash.
       *
       * 1. 1 | 1 = 1, bitwise OR, salah satu 1 berarti 1.
       * 2. 1 | 0 = 1
       * 2. 1 ^ 1 = 0, bitwise XOR, 1 jika tidak sama.
       * 3. 1 ^ 0 = 1, 0 jika berbeda.
       * 4. difference |= hash[i] ^ Hashed_PIN[i]
       *    => a += b + c, sama seperti: a = a + b + c
       *    => difference = difference | hash[i] ^ Hashed_PIN[i]
       *    => urutan: difference = ( difference | hash[i] ) ^ Hashed_PIN[i]
       *
       * Pusing? pakai Arrays.equals(byte[], byte[]); kelar.
       */
      int difference = hash.length ^ Hashed_PIN.length; // 16 ^ 16 = 0;
      for (
              int i = 0;
              i < hash.length && i < Hashed_PIN.length; // i < 16 & i < 16; the constraints.
              i++
      ) {
        difference |= hash[i] ^ Hashed_PIN[i]; // intinya: jika beda, ketika bandingin byte by byte maka difference = 1.
      }
      return difference == 0;
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
      return false;
    }

//    /**
//     * note: storedHashed_PIN is not Hashed_PIN
//     * storedHashed_PIN iterations:salt:hash
//     * Hashed_PIN is just the hash.
//     */
//    byte[] Hashed_PIN = new byte[0];
//    try {
//      Hashed_PIN = secretKeyFactory.generateSecret(keySpec).getEncoded();
//    } catch (InvalidKeySpecException e) {
//      e.printStackTrace();
//    }
  }

  private byte[] getSalt() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }

  private String toHex(byte[] bytes) {
    BigInteger bigInteger = new BigInteger(1, bytes);
    return bigInteger.toString(16);
  }

  private byte[] fromHex(String hex) {
    byte[] bytes = new byte[hex.length() / 2];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
  }
}
