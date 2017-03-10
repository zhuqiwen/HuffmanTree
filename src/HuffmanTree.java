import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * A HuffmanTree represents a variable-length code such that the shorter the
 * bit pattern associated with a character, the more frequently that character
 * appears in the text to be encoded.
 */

public class HuffmanTree {

	class Node {
		protected char key;
		protected int priority;
		protected Node left, right;

		// this is for leaf, where we have only char and their frequency
		public Node(int priority, char key)
		{
			this(priority, key, null, null);
		}

		//this is for non-leaf node
		public Node(int priority, Node left, Node right)
		{
			this(priority, '\0', left, right);
		}

		public Node(int priority, char key, Node left, Node right)
		{
			this.key = key;
			this.priority = priority;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}
	}

	protected Node root;

	protected Map<Character, String> lookupTable;

	/**
	 *
	 *
	 * Creates a HuffmanTree from the given frequencies of letters in the
	 * alphabet using the algorithm described in lecture.
	 */
	public HuffmanTree(FrequencyTable charFreqs) {
		Comparator<Node> comparator = (x, y) -> {
			/**
			 *
			 *  x comes before y if x's priority is less than y's priority
			 */
			return x.priority - y.priority;
		};

		PriorityQueue<Node> forest = new Heap<Node>(comparator);

		/**
		 *
		 * Start by populating forest with leaves.
		 */

		for(char c : charFreqs.keySet())
		{
			Node node = new Node(charFreqs.get(c), c);
			forest.insert(node);
		}

		while(forest.size() > 1)
		{
			Node left = forest.delete();
			Node right = forest.delete();
			Node node = new Node(left.priority + right.priority, left, right);
			forest.insert(node);
		}


		root = forest.peek();

		// produce the lookup table when instantiate an object
		lookupTable = makeLookupTable();
	}

	/**
	 *
	 *
	 * Returns the character associated with the prefix of bits.
	 *
	 * @throws DecodeException if bits does not match a character in the tree.
	 */
	public char decodeChar(String bits)
	{

		return decodeCharHelper(root, bits);

	}

	/**
	 * A helper for decodeChar that goes down the tree to retrieve char
	 * @param String
	 * @param Node
	 * @return char
	 */
	private char decodeCharHelper(Node node, String bits)
	{
		int i = 0;

		while (!node.isLeaf())
		{
			if(bits.charAt(i) == '0')
			{
				node = node.left;
			}
			else
			{
				node = node.right;
			}
			i ++;
		}

		if(node.key != '\0')
		{
			return node.key;
		}
		else
		{
			throw new DecodeException(bits);
		}
	}

	/**
	 *
	 *
	 * Returns the bit string associated with the given character. Must
	 * search the tree for a leaf containing the character. Every left
	 * turn corresponds to a 0 in the code. Every right turn corresponds
	 * to a 1. This function is used by CodeBook to populate the map.
	 *
	 * @throws EncodeException if the character does not appear in the tree.
	 */
	public String lookup(char ch)
	{

		if(lookupTable.containsKey(ch))
		{
			return lookupTable.get(ch);
		}
		else
		{
			throw new EncodeException(ch);
		}

	}


	/**
	 * lookup helper, used by makeLookupTable to produce a look up hashmap
	 * @param Node
	 * @param StringBuilder
	 * @param Map
	 */
	private void lookHelper(Node node, StringBuilder stringBuilder, Map<Character, String> lookupTable)
	{
		if(node.isLeaf())
		{
			lookupTable.put(node.key, stringBuilder.toString());
			return;
		}


		lookHelper(node.left, stringBuilder.append('0'), lookupTable);
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		lookHelper(node.right, stringBuilder.append('1'), lookupTable);
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
	}


	/**
	 * returns a hash map that contains character and its huffman code
	 * @return Map
	 */
	private Map<Character, String> makeLookupTable()
	{
		StringBuilder stringBuilder = new StringBuilder();
		Map<Character, String> lookupTable = new HashMap<>();
		lookHelper(root, stringBuilder, lookupTable);

		return lookupTable;
	}


	/**
	 * print helper to facilitate debugging.
	 */
	public void printOut()
	{
		StringBuilder stringBuilder = new StringBuilder();
		printOutHelper(root, stringBuilder);
	}

	private void printOutHelper(Node node, StringBuilder stringBuilder)
	{
		if(node.isLeaf())
		{
			System.out.print("<[" + stringBuilder.toString() + "]");
			System.out.print(node.key);
			System.out.print(":");
			System.out.print(node.priority);
			System.out.print(">");
			return;
		}

		System.out.print("<");
		System.out.print(node.key);
		System.out.print("--");
		System.out.print(node.priority);
		System.out.print(">");

		System.out.print(" ( ");
		printOutHelper(node.left, stringBuilder.append('0'));
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		System.out.print(", ");
		printOutHelper(node.right, stringBuilder.append('1'));
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		System.out.print(" ) ");

	}


}

