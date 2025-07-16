package me.duynam.penguin_coin.model;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Transaction {

  private String id;
  private PublicKey sender;
  private PublicKey recipient;
  private double amount;
  private byte[] signature;

  private List<TransactionInput> inputs;
  private List<TransactionOutput> outputs;

  public Transaction(PublicKey from, PublicKey to, double amount, List<TransactionInput> inputs) {
    this.sender = from;
    this.recipient = to;
    this.amount = amount;
    this.inputs = inputs;
    this.outputs = new ArrayList<>();
  }
}
