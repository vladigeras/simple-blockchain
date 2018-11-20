import model.Block;
import model.BlockChain;
import model.Transaction;
import model.Wallet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigDecimal;
import java.security.Security;

/**
 * @author vladi_geras on 07/11/2018
 */
public class Client {

	static Wallet a;
	static Wallet b;

	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		a = new Wallet();
		b = new Wallet();

		var transaction = new Transaction(a.getPublicKey(), b.getPublicKey(), BigDecimal.valueOf(5));
		transaction.generateSignature(a.getPrivateKey());
		System.out.println(transaction.verifiySignature());

		var blockChain = new BlockChain();
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "First model.Block"));
		blockChain.addBlock(new Block(blockChain.getPrevBlock().getId() + 1, blockChain.getPrevBlock().getHash(), "Second model.Block"));
		blockChain.printChain();
	}
}
