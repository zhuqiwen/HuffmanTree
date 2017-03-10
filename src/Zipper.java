/**
 *
 *
 * A Zipper is used to encode/decode text strings based on a supplied code book.
 * Additionally, a Zipper is capable of compressing a bit string into a string of
 * characters, as well as the inverse (i.e., decompressing a character string
 * into a bit string).
 */

public class Zipper {
	private CodeBook book;   // used for encoding
	private HuffmanTree ht;  // used for decoding

	/**
	 * Create a Zipper from the provided code book.
	 */
	public Zipper(CodeBook book) {
		this.book = book;
		ht = book.getHuffmanTree();
	}

	/**
	 *
	 *
	 * Returns the bit string encoding of the given plain text.
	 */
	public String encode(String plainText)
	{
		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0; i < plainText.length(); i++)
		{
			char c = plainText.charAt(i);
			stringBuilder.append(book.encodeChar(c));
		}
		return stringBuilder.toString();
	}

	/**
	 *
	 *
	 * Returns the text string corresponding to the given bit string.
	 */
	public String decode(String bits)
	{
		StringBuilder stringBuilder = new StringBuilder();

		int i = 0;
		// make sure all bits are covered
		while(i < bits.length())
		{
			HuffmanTree.Node node = ht.root;

			while (!node.isLeaf())
			{
				if(bits.charAt(i) == '0')
				{
					node = node.left;
				}
				if(bits.charAt(i) == '1')
				{
					node = node.right;
				}
				i++;
			}

			stringBuilder.append(node.key);
		}

		return stringBuilder.toString();
	}


	/**
	 * Returns the result of compressing a string of bits into a string of
	 * 8-bit characters.
	 */
	public String compress(String bits) {
		int n = bits.length();
		// The last byte may not be full. We'll need to know how many bits are in
		// the last byte, so that we can decompress properly later. We will prepend
		// the compressed string with a head byte that holds the size of the last
		// byte.
		int lastBiteSize = n % Constants.BITESIZE;
		if (lastBiteSize == 0)
			lastBiteSize = Constants.BITESIZE;  // the last byte is full
		String headByte = Util.padLeft(Integer.toBinaryString(lastBiteSize), Constants.BITESIZE);
		StringBuilder chars = new StringBuilder();
		chars.append(Util.bitsToAscii(headByte));
		for (int i = 0; i < n; i += Constants.BITESIZE) {
			String block = bits.substring(i, Math.min(n, i + Constants.BITESIZE));
			chars.append(Util.bitsToAscii(block));
		}
		return chars.toString();
	}

	/**
	 * Returns the result of expanding the given compressed text into a bit string.
	 */
	public String decompress(String compressedText) {
		// Process the head byte to retrieve the size of the last byte.
		int lastBiteSize = (int) compressedText.charAt(0);
		String bits = "";
		int n = compressedText.length();
		for (int i = 1; i < n - 1; i++)
			bits += Util.asciiToBits(compressedText.charAt(i), Constants.BITESIZE);
		bits += Util.asciiToBits(compressedText.charAt(n - 1), lastBiteSize);
		return bits.toString();
	}
}
