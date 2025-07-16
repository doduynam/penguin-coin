package me.duynam.penguin_coin.model;

import java.security.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionOutput {

  private String id;
  private PublicKey recipient;
  private double amount;
  private String parentTransactionId;

  public boolean isMine(PublicKey publicKey) {
    return recipient.equals(publicKey);
  }
}
