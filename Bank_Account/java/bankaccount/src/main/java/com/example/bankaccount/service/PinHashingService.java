package com.example.bankaccount.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.Key;
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

    KeySpec keySpec = new PBEKeySpec(pin.toCharArray(), salt, iterations, 128);
    SecretKeyFactory secretKeyFactory = null;
    try {
      secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    byte[] Hashed_PIN = new byte[0];
    try {
      Hashed_PIN = secretKeyFactory.generateSecret(keySpec).getEncoded();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }

    // TODO: thread safe.
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(iterations);
    stringBuffer.append(":");
    stringBuffer.append(toHex(salt));
    stringBuffer.append(":");
    stringBuffer.append(toHex(Hashed_PIN));

    return stringBuffer.toString();
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

    SecretKeyFactory secretKeyFactory = null;
    try {
      secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    byte[] Hashed_PIN = new byte[0];
    try {
      Hashed_PIN = secretKeyFactory.generateSecret(keySpec).getEncoded();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }

    int difference = hash.length ^ Hashed_PIN.length;
    for (int i = 0; i < hash.length && i < Hashed_PIN.length; i++) {
      difference |= hash[i] ^ Hashed_PIN[i];
    }
    return difference == 0;

  }

  private byte[] getSalt() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] salt = new byte[16];
    secureRandom.nextBytes(salt);
    return salt;
  }

  private String toHex(byte[] bytes) {
    BigInteger bigInteger = new BigInteger(1, bytes);
    String hex = bigInteger.toString(16);
    return hex;
  }

  private byte[] fromHex(String hex) {
    byte[] bytes = new byte[hex.length() / 2];
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
    }
    return bytes;
  }
}
