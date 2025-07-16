package me.duynam.penguin_coin.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.duynam.penguin_coin.factory.BlockFactory;
import me.duynam.penguin_coin.model.Block;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockchainValidator {

  public static boolean isValidChain(List<Block> chain) {

    // Check if the blockchain is null or empty
    if (chain == null || chain.isEmpty()) {
      log.info("Chain is null or empty");
      return false;
    }

    // check if the first block is the genesis block
    final ObjectMapper objectMapper = new ObjectMapper();
    try {
      if (!StringUtils.equals(
          objectMapper.writeValueAsString(chain.get(0)),
          objectMapper.writeValueAsString(BlockFactory.generateGenesisBlock()))) {
        log.info("The first block is not the genesis block");
        return false;
      }
    } catch (JsonProcessingException e) {
      log.error("Error processing JSON for the genesis block", e);
      throw new RuntimeException(e);
    }

    // Validate each block in the blockchain
    for (int i = 1; i < chain.size(); i++) {
      Block currentBlock = chain.get(i);
      Block previousBlock = chain.get(i - 1);

      // Validate the current block against the previous block
      if (!BlockValidator.isValidBlock(currentBlock, previousBlock)) {
        log.info("Invalid block with index {}", currentBlock.getIndex());
        return false; // Invalid block found
      }
    }

    return true; // The blockchain is valid
  }
}
