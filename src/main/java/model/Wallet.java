package model;

import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

/**
 * @author vladi_geras on 16/11/2018
 */
public class Wallet {

	private PrivateKey privateKey;
	private PublicKey publicKey;        //wallet address

	public Wallet() {
		generateKeys();
	}

	private void generateKeys() {
		try {
			var keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
			var random = SecureRandom.getInstance("SHA1PRNG");
			var ecSpec = new ECGenParameterSpec("prime192v1");
			keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level

			var keyPair = keyGen.generateKeyPair();
			privateKey = keyPair.getPrivate();
			publicKey = keyPair.getPublic();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}
}
