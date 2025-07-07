package me.duynam.penguin_coin.model;

import java.util.ArrayList;
import java.util.List;
import me.duynam.penguin_coin.factory.BlockFactory;

public class Blockchain {

  private final List<Block> chain;

  public Blockchain() {
    this.chain = new ArrayList<>();

    // Initialize the blockchain with the genesis block
    this.chain.add(BlockFactory.generateGenesisBlock());
  }

  public void addBlock(String data) {
    final Block previousBlock = getLatestBlock();
    final Block newBlock = BlockFactory.generateNextBlock(previousBlock, data);

    this.chain.add(newBlock);
  }

  private Block getLatestBlock() {
    return this.chain.get(this.chain.size() - 1);
  }
}
