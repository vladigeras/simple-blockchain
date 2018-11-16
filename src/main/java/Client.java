import model.Block;
import model.BlockChain;

/**
 * @author vladi_geras on 07/11/2018
 */
public class Client {
	public static void main(String[] args) {
		var blockChain = new BlockChain();
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "First model.Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Second model.Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Third model.Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Fourth model.Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Fifth model.Block"));
		blockChain.printChain();
	}
}
