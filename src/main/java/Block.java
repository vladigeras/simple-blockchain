import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author vladi_geras on 07/11/2018
 */
public class Block {
	private Long id;
	private String prevHash;
	private String hash;
	private Object payload;
	private Integer nonce;
	private LocalDateTime dateTime;

	public Block(Long id, String prevHash, Object payload) {
		this.id = id;
		this.prevHash = prevHash;
		this.payload = payload;
		this.dateTime = LocalDateTime.now(ZoneOffset.UTC);
		this.nonce = 0;
		this.hash = calculateHash();
	}

	private String calculateHash() {
		nonce++;
		return StringUtil.applySha256(this.id + this.prevHash + this.dateTime.toString() + nonce + this.payload);
	}

	public void mineBlock(int difficulty) {
		var target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block was mined : " + hash + " with nonce = " + nonce);
	}

	public Long getId() {
		return id;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public String getHash() {
		return hash;
	}

	public Object getPayload() {
		return payload;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return "{" +
				"id: " + id + ", " +
				"prevHash: " + prevHash + ", " +
				"hash: " + hash + ", " +
				"payload: " + payload + ", " +
				"dateTime: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.YYYY")) + ", " +
				"nonce: " + nonce +
				'}';
	}
}
