package me.duynam.penguin_coin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionInput {

  private String transactionOutputId;
  private TransactionOutput utxo;
}
