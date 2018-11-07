/**
 * @author vladi_geras on 07/11/2018
 */
public class Client {
	public static void main(String[] args) {
		var blockChain = new BlockChain();
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "First Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Second Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Third Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Fourth Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Fifth Block"));
		blockChain.printChain();
	}
}
