/**
 * @author vladi_geras on 07/11/2018
 */
public class Client {
	public static void main(String[] args) {
		var coreBlock = new Block(0L, "0", "Core Block");
		System.out.println("PrevHash for block 0 is " + coreBlock.getPrevHash());
		System.out.println("Hash for block 0 is " + coreBlock.getHash());
		System.out.println("--- --- --- --- --- --- --- --- --- --- ---");

		var firstBlock = new Block(1L, coreBlock.getHash(), "First Block");
		System.out.println("PrevHash for block 1 is " + firstBlock.getPrevHash());
		System.out.println("Hash for block 1 is " + firstBlock.getHash());
		System.out.println("--- --- --- --- --- --- --- --- --- --- ---");

		var secondBlock = new Block(2L, firstBlock.getHash(), "Second Block");
		System.out.println("PrevHash for block 2 is " + secondBlock.getPrevHash());
		System.out.println("Hash for block 2 is " + secondBlock.getHash());
	}
}
