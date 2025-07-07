package me.duynam.penguin_coin.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashingUtils {

  public static final String SHA_256 = "SHA-256";

  public static String sha256(String input) {
    try {

      MessageDigest messageDigest = MessageDigest.getInstance(SHA_256);

      byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

      byte[] hashBytes = messageDigest.digest(inputBytes);

      StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
      for (byte b : hashBytes) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      return hexString.toString();

    } catch (NoSuchAlgorithmException e) {
      log.error("Cannot hash input with SHA-256 algorithm", e);
      throw new RuntimeException(e);
    }
  }
}
