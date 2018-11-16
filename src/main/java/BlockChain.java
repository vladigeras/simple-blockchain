import java.util.ArrayList;
import java.util.List;

/**
 * @author vladi_geras on 07/11/2018
 */
public class BlockChain {
	private static List<Block> blocks;
	private static Integer difficulty = 3;

	public BlockChain() {
		if (blocks == null) {
			blocks = new ArrayList<>();
			blocks.add(new Block(0L, "0", "Core block"));
		}
	}

	public void printChain() {
		System.out.println(blocks);
	}

	public Integer getSize() {
		return blocks.size();
	}

	public void addBlock(Block block) {
		if (block == null) throw new IllegalArgumentException("Block must be non null");
		block.mineBlock(difficulty);
		blocks.add(block);

		if (!isValidCurrentBlock()) {
			throw new RuntimeException("Error in adding block process - chain is invalid");
		}
	}

	public Block getPrevBlock() {
		return blocks.get(blocks.size() - 1);
	}

	public List<Block> getBlocks() {
		return blocks;
	}

	public Boolean isValidCurrentBlock() {
		Block currentBlock;
		Block prevBlock;

		for (var i = 1; i < getSize(); i++) {
			currentBlock = blocks.get(i);
			prevBlock = blocks.get(i - 1);

			if (!prevBlock.getHash().equals(currentBlock.getPrevHash())) {
				return false;
			}
		}

		return true;
	}
}
