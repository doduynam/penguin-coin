package me.duynam.penguin_coin.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import me.duynam.penguin_coin.factory.BlockFactory;
import me.duynam.penguin_coin.validator.BlockchainValidator;

@Slf4j
@Getter
public class Blockchain {

  private static final int BLOCK_GENERATION_INTERVAL = 10; // in seconds
  private static final int DIFFICULTY_ADJUSTMENT_INTERVAL = 1024; // in blocks

  private List<Block> chain;

  public Blockchain() {
    this.chain = new ArrayList<>();

    // Initialize the blockchain with the genesis block
    this.chain.add(BlockFactory.generateGenesisBlock());
  }

  public void mineBlock(String data, Integer difficulty) {
    final Block previousBlock = getLatestBlock();

    final Block newBlock = BlockFactory.findBlock(previousBlock, data, difficulty);

    this.chain.add(newBlock);
    // TODO: Notify peers about the new block
  }

  public void replaceChain(List<Block> newChain) {

    // Check if the new chain is longer than the current chain
    if (newChain.size() > this.chain.size() && BlockchainValidator.isValidChain(newChain)) {
      log.info("Replacing the current chain with a new chain of length {}", newChain.size());
      this.chain = newChain;
      // TODO: Notify peers about the chain replacement
    }
  }

  private Block getLatestBlock() {
    return this.chain.get(this.chain.size() - 1);
  }
}
