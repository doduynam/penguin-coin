package me.duynam.penguin_coin.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptoUtils {

  public static String calculateHash(Long index, String previousHash, Long timestamp, String data, Long difficulty, Long nonce) {
    String input = index + previousHash + timestamp + data + difficulty + nonce;
    return HashingUtils.sha256(input);
  }

  public static String calculateHash(Long index, String previousHash, Long timestamp, String data) {
    return calculateHash(index, previousHash, timestamp, data, 0L, 0L);
  }
}
