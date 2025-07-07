package me.duynam.penguin_coin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Block {

  public final Long index;
  private final String hash;
  private final String previousHash;
  private final Long timestamp;
  private final String data;
  private final Long difficulty;
  private final Long nonce;
}
