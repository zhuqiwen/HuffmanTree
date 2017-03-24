import org.junit.Test;

/**
 * Running this driver will print some statistics about a small sample
 * text.
 *
 *
 *
 * Once your code passes all the unit tests, replace text with the contents
 * of Alice In Wonderland. Figure out how to calculate the percentage of
 * bits saved. Do the same for Moby Dick. Then, download the plain text version
 * of a favorite book from http://www.gutenberg.org to your project directory,
 * and collect those statistics.
 *
 * Create a text file named report.txt in your project directory. This file should
 * include your full name, username, and lab section, and a table of your
 * statistics regarding the three books.
 */

public class Driver {
	public static void main(String[] args)
	{
		System.out.println(Constants.TITLE);
//		String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//				+ "bbbbbbbbbbbbbb"
//				+ "cccccccccccc"
//				+ "ddddddddddddddddddddd"
//				+ "eeeeeeeee"
//				+ "fffff";
//		String text = Util.loadFile(Constants.ALICE);
//		String text = Util.loadFile(Constants.MOBY_DICK);

		String[] books = new String[3];
		books[0] = Util.loadFile(Constants.BEN);
		books[1] = Util.loadFile(Constants.ALICE);
		books[2] = Util.loadFile(Constants.MOBY_DICK);
		String[] names = new String[3];
		names[0] = Constants.BEN;
		names[1] = Constants.ALICE;
		names[2] = Constants.MOBY_DICK;



		bookTest(books[2], names[2]);
//		for (int i = 0; i < books.length; i++)
//		{
//			bookTest(books[i], names[i]);
//		}


	}


	public static void bookTest(String text, String name)
	{
		long start = System.currentTimeMillis();

		System.out.println("****************TEST:" + name + "********************");
		System.out.println("The original text has " + text.length() + " characters.");
		CodeBook book = new CodeBook(text);
		System.out.println(String.format("The average length of a code is %.2f bits.",
				book.getWeightedAverage()));
		Zipper zipper = new Zipper(book);
		String bits = zipper.encode(text);
		System.out.println("The text is encoded in " + bits.length() + " bits.");
		System.out.println("The savings is " +
				(text.length() * Constants.BITESIZE - bits.length()) + " bits.");
		String packing = zipper.compress(bits);
		System.out.println("The compressed text is encoded in " + packing.length() + " characters.");
		System.out.println("The savings is " +
				(text.length() - packing.length()) + " characters.");
		String unpacking = zipper.decompress(packing);
		System.out.println("Decompressing yields " + unpacking.length() + " bits.");
		String recoveredText = zipper.decode(unpacking);
		System.out.println("The recovered text has " + recoveredText.length() + " characters.");
		assert recoveredText.equals(text);
		System.out.println("\nAll tests passed...");

		long end = System.currentTimeMillis();


		System.out.println("Total time cost: " + (end - start));
		System.out.println("*********************TEST " + name + " FINISHED*********************");



	}


}

