
public class EmoticonsValidator {
	// happy emoticons
	public static final String SMILE_EMOTICON = ":-)";
	public static final String SMILE_II_EMOTICON = ":)";
	public static final String BIG_TOOTHY_GRIN = ":-D";
	public static final String BIG_TOOTHY_II_GRIN = ":D";
	public static final String BIG_TOOTHY_III_GRIN = ":d";
	public static final String BIG_TOOTHY_IIII_GRIN = ":-d";
	public static final String LAUGHING_HARD = ":))";
	public static final String LAUGHING_II_HARD = ":-))";

	// sad emoticons
	public static final String CRY_EMOTICON = "=(";
	public static final String SAD_EMOTICON = ":-(";
	public static final String SAD_II_EMOTICON = ":(";
	public static final String CRYING_EMOTICON = ":'(";
	public static final String CRYING_II_EMOTICON = ":'-(";
	public static final String VERY_SAD = ":((";
	public static final String SAD_CRYING = ":'s";

	public static boolean isPositiveEmoticon(String tweetMessage) {
		return (tweetMessage.contains(SMILE_EMOTICON) || tweetMessage.contains(SMILE_II_EMOTICON)
				|| tweetMessage.contains(BIG_TOOTHY_GRIN) || tweetMessage.contains(BIG_TOOTHY_II_GRIN)
				|| tweetMessage.contains(BIG_TOOTHY_III_GRIN) || tweetMessage.contains(BIG_TOOTHY_IIII_GRIN)
				|| tweetMessage.contains(LAUGHING_HARD) || tweetMessage.contains(LAUGHING_II_HARD));
	}

	public static boolean isNegativeEmoticon(String tweetMessage) {
		return (tweetMessage.contains(CRY_EMOTICON) || tweetMessage.contains(SAD_EMOTICON)
				|| tweetMessage.contains(CRYING_II_EMOTICON) || tweetMessage.contains(CRYING_EMOTICON)
				|| tweetMessage.contains(VERY_SAD) || tweetMessage.contains(SAD_CRYING)
				|| tweetMessage.contains(SAD_II_EMOTICON));
	}

}
