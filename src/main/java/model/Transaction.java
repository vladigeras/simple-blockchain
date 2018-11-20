package model;

import util.StringUtil;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author vladi_geras on 20/11/2018
 */
public class Transaction {

	private String hash;
	private PublicKey sender;
	private PublicKey reciepient;
	private BigDecimal amount;
	private byte[] signature;

	public Transaction(PublicKey from, PublicKey to, BigDecimal amount) {
		this.sender = from;
		this.reciepient = to;
		this.amount = amount;
		this.hash = calulateHash();
	}

	private String calulateHash() {
		return StringUtil.applySha256(StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + amount);
	}

	public void generateSignature(PrivateKey privateKey) {
		var data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + amount;
		signature = StringUtil.applyECDSASig(privateKey, data);
	}

	public boolean verifiySignature() {
		var data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + amount;
		return StringUtil.verifyECDSASig(sender, data, signature);
	}

	public String getHash() {
		return hash;
	}

	public PublicKey getSender() {
		return sender;
	}

	public PublicKey getReciepient() {
		return reciepient;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}
