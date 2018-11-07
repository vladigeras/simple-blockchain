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
	private LocalDateTime dateTime;

	public Block(Long id, String prevHash, Object payload) {
		this.id = id;
		this.prevHash = prevHash;
		this.payload = payload;
		this.dateTime = LocalDateTime.now(ZoneOffset.UTC);
		this.hash = calculateHash();
	}

	private String calculateHash() {
		return StringUtil.applySha256(this.id + this.prevHash + this.dateTime.toString() + this.payload);
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
				"dateTime: " + dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.YYYY")) +
				'}';
	}
}
