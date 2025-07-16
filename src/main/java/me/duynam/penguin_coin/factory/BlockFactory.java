package me.duynam.penguin_coin.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import me.duynam.penguin_coin.model.Block;
import me.duynam.penguin_coin.util.CryptoUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockFactory {

  public static Block generateNextBlock(
      Block previousBlock, String blockData, Long nonce, Integer difficulty) {
    if (previousBlock == null) {
      throw new IllegalStateException("Cannot generate next block without a previous block");
    }
    final Long nextIndex = previousBlock.getIndex() + 1;
    final Long nextTimestamp = System.currentTimeMillis();
    final String previousHash = previousBlock.getHash();
    final String nextHash =
        CryptoUtils.calculateHash(
            nextIndex, previousHash, nextTimestamp, blockData, nonce, difficulty);

    return new Block(
        nextIndex, nextHash, previousHash, nextTimestamp, blockData, nonce, difficulty);
  }

  public static Block generateGenesisBlock() {
    final Long genesisIndex = 0L;
    final String genesisHash = "189d71748db1c1d623f24961f2df781a46c3d9373453b27d51e234529a430fed";
    final String previousHash = "";
    final Long genesisTimestamp = System.currentTimeMillis();
    final String genesisData = "Penguin Coin Genesis Block";
    final Integer difficulty = 0;
    final Long nonce = 0L;

    return new Block(
        genesisIndex, genesisHash, previousHash, genesisTimestamp, genesisData, nonce, difficulty);
  }

  public static Block findBlock(Block previousBlock, String blockData, Integer difficulty) {
    final Long index = previousBlock.getIndex() + 1;
    final String previousHash = previousBlock.getHash();
    Long nonce = 0L;

    while (true) {
      final Long currentTimeMillis = System.currentTimeMillis();
      final String hash =
          CryptoUtils.calculateHash(
              index, previousHash, currentTimeMillis, blockData, nonce, difficulty);

      if (hashMatchesDifficulty(hash, difficulty)) {
        return new Block(
            index, hash, previousHash, currentTimeMillis, blockData, nonce, difficulty);
      }

      // Increment nonce for the next iteration
      nonce++;
    }
  }

  private static boolean hashMatchesDifficulty(String hash, Integer difficulty) {
    final String hashPrefix = new String(new char[difficulty]).replace('\0', '0');

    return hash != null && hash.startsWith(hashPrefix);
  }
}
