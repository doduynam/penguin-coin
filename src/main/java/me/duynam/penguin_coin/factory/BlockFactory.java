package me.duynam.penguin_coin.factory;

import me.duynam.penguin_coin.model.Block;
import me.duynam.penguin_coin.util.CryptoUtils;

public class BlockFactory {

  public static Block generateNextBlock(Block previousBlock, String blockData) {
    if (previousBlock == null) {
      throw new IllegalStateException("Cannot generate next block without a previous block");
    }
    final Long nextIndex = previousBlock.getIndex() + 1;
    final Long nextTimestamp = System.currentTimeMillis();
    final String previousHash = previousBlock.getHash();
    // TODO: Change this variable to calculate data
    final Long difficulty = 0L;
    final Long nonce = 0L;
    final String nextHash =
        CryptoUtils.calculateHash(
            nextIndex, previousHash, nextTimestamp, blockData, difficulty, nonce);

    return new Block(
        nextIndex, nextHash, previousHash, nextTimestamp, blockData, difficulty, nonce);
  }

  public static Block generateGenesisBlock() {
    final Long genesisIndex = 0L;
    final String genesisHash = "189d71748db1c1d623f24961f2df781a46c3d9373453b27d51e234529a430fed";
    final String previousHash = "0";
    final Long genesisTimestamp = System.currentTimeMillis();
    final String genesisData = "Penguin Coin Genesis Block";
    final Long difficulty = 0L;
    final Long nonce = 0L;

    return new Block(
        genesisIndex, genesisHash, previousHash, genesisTimestamp, genesisData, difficulty, nonce);
  }
}
