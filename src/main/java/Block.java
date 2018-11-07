import java.time.LocalDateTime;
import java.time.ZoneOffset;

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

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
