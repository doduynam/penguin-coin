package me.duynam.penguin_coin.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.duynam.penguin_coin.model.Block;
import me.duynam.penguin_coin.util.CryptoUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockValidator {

  public static boolean isValidBlock(Block newBlock, Block previousBlock) {
    if (previousBlock == null) {
      log.info("No previous block to validate against.");
      return false; // No previous block to compare against
    }

    if (newBlock.getIndex() != previousBlock.getIndex() + 1) {
      log.info(
          "Invalid block index: expected {}, got {}",
          previousBlock.getIndex() + 1,
          newBlock.getIndex());
      return false; // New block index must be one greater than the previous block index
    }

    if (!newBlock.getPreviousHash().equals(previousBlock.getHash())) {
      log.info(
          "Invalid previous hash: expected {}, got {}",
          previousBlock.getHash(),
          newBlock.getPreviousHash());
      return false; // New block's previous hash must match the previous block's hash
    }

    if (StringUtils.isBlank(newBlock.getHash())
        || StringUtils.equals(newBlock.getHash(), CryptoUtils.calculateHash(newBlock))) {
      log.info(
          "Invalid block hash: expected a non-empty hash that does not match the calculated hash.");
      return false;
    }

    if (newBlock.getTimestamp() <= previousBlock.getTimestamp()) {
      log.info(
          "Invalid block timestamp: expected greater than {}, got {}",
          previousBlock.getTimestamp(),
          newBlock.getTimestamp());
      return false; // New block timestamp must be greater than the previous block's timestamp
    }

    return true; // All checks passed, the new block is valid
  }
}
