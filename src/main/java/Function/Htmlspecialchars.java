package Function;

public class Htmlspecialchars {
	public String escape(String str) {
		String result_str = new String(str);
		String[] escape = {"&", "<", ">", "\"", "\'", "\n", "\t"};
		String[] replace = {"&amp;", "&lt;", "&gt;", "&quot;", "&#39;", "<br>", "&#x0009;"};
		
		for (int i=0; i<escape.length; i++) {
			result_str = result_str.replace(escape[i], replace[i]);
		}
		return result_str;
	}
}
