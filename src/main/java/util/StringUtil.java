package util;

import model.Transaction;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.stream.Collectors;

/**
 * @author vladi_geras on 07/11/2018
 */
public class StringUtil {

	private StringUtil() {
	}

	public static String applySha256(String input) {
		try {
			var digest = MessageDigest.getInstance("SHA-256");
			var bytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
			var hexString = new StringBuffer();

			for (byte b : bytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
		Signature dsa;
		byte[] output;
		try {
			dsa = Signature.getInstance("ECDSA", "BC");
			dsa.initSign(privateKey);
			var strByte = input.getBytes();
			dsa.update(strByte);
			var realSig = dsa.sign();
			output = realSig;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return output;
	}

	public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
		try {
			var ecdsaVerify = Signature.getInstance("ECDSA", "BC");
			ecdsaVerify.initVerify(publicKey);
			ecdsaVerify.update(data.getBytes());
			return ecdsaVerify.verify(signature);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}

	public static String getMerkleRoot(ArrayList<Transaction> transactions) {
		var count = transactions.size();
		var previousTreeLayer = transactions.stream().map(Transaction::getHash).collect(Collectors.toList());

		var treeLayer = previousTreeLayer;

		while (count > 1) {
			treeLayer = new ArrayList<String>();

			for (int i = 1; i < previousTreeLayer.size(); i++) {
				treeLayer.add(applySha256(previousTreeLayer.get(i - 1) + previousTreeLayer.get(i)));
			}
			count = treeLayer.size();
			previousTreeLayer = treeLayer;
		}
		return (treeLayer.size() == 1) ? treeLayer.get(0) : "";
	}
}
