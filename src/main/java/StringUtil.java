import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

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
}
