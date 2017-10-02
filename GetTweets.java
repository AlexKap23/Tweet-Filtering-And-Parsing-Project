
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class GetTweets {
	private static final String ConsumerKey = "LSOhhAyXSZr2VysDC2o2wjJMw";
	private static final String ConsumerSecret = "8fToCGyS70vTjhwf54ShWV8yI1YRRB4XMyU64rEQoD1tYP4tBM";
	private static final String AccessToken = "2577638922-j8oomg2PWhiTHyO0InQDMkKOlGn45M2jkWp8Ja6";
	private static final String TokenSecret = "8J5jsNT6EUg1x2N4eRhM6gJnShHPbVCWyrtAY9oR8Ik9R";

	List<Status> tweetsList = new ArrayList<Status>();
	DataBase DBAction = new DataBase();
	private int Possitive = 0;
	private int Negative = 0;

	public int getPossitive() {
		return Possitive;
	}

	public int getNegative() {
		return Negative;
	}

	private void runQuery(String Hashtag, String Username) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true).setOAuthConsumerKey(ConsumerKey).setOAuthConsumerSecret(ConsumerSecret)
				.setOAuthAccessToken(AccessToken).setOAuthAccessTokenSecret(TokenSecret);

		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.Twitter twitter = tf.getInstance();
		try {
			Date dt = new Date();
			String modifiedDt = new SimpleDateFormat("yyyymmdd").format(dt);

			// query for hastags
			Query HashTag = new Query(Hashtag);
			HashTag.setSince(modifiedDt);
			HashTag.setCount(100);
			QueryResult resultsPerHashTag;
			int i = 0;

			// query for Username
			Query UserName = new Query(Username);
			UserName.setSince(modifiedDt);
			UserName.setCount(100);
			QueryResult resultsPerUsername;
			int j = 0;

			do {
				resultsPerHashTag = twitter.search(HashTag);
				for (Status tweet : resultsPerHashTag.getTweets()) {
					if (!tweet.isRetweet()) {
						i++;
						tweetsList.add(tweet);
					}
				}
			} while ((HashTag = resultsPerHashTag.nextQuery()) != null);

			do {
				resultsPerUsername = twitter.search(UserName);
				for (Status tweet : resultsPerUsername.getTweets()) {

					if (!tweet.isRetweet()) {
						j++;
						tweetsList.add(tweet);
					}
				}
			} while ((UserName = resultsPerHashTag.nextQuery()) != null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void get() {

		Scanner input = new Scanner(System.in);
		System.out.println("Press 1 for Syriza and Alexis Tsipras");
		System.out.println("Press 2 for Nea Dimokratia and Kiriakos Mitsotakis");
		int x = input.nextInt();
		switch (x) {
		case 1:
			String HashtagSRZ = "#Syriza OR #syriza OR #Syrizanel OR #syrizanel OR #ΣΥΡΙΖΑ";
			String Tsipras = "@atsipras OR @tsipras_eu";
			runQuery(HashtagSRZ, Tsipras);
			parseTweetList();

			System.out.println("There were " + getPossitive() + " possitive tweets about Syriza and Alexis Tsipras");
			System.out.println("And there were " + getNegative() + " negative tweets for Syriza and Alexis Tsipras");
			break;
		case 2:
			String HashtagND = "#NewDemocracy OR #ND OR #NeaDimokratia OR #neaDimokratia OR #ΝΕΑΔΗΜΟΚΡΑΤΙΑ OR #νεαδημοκρατια OR #New_democracy OR #νεα_δημοκρατία";
			String Koulis = "@kmitsotakis";
			runQuery(HashtagND, Koulis);
			parseTweetList();

			System.out.println(
					"There were " + getPossitive() + " possitive tweets about Nea Dimokratia and Kiriakos Mitsotakis");
			System.out.println("And there were " + getNegative()
					+ " negative tweets about Nea Dimokratia and Kiriakos Mitsotakis ");

			break;
		default:
			System.out.println("Please press 1 or 2");

		}
		input.close();

	}

	private void parseTweetList() {
		try {
			for (Status st : tweetsList) {
				String tweetMessage = st.getText();
				if (EmoticonsValidator.isPositiveEmoticon(tweetMessage)) {
					DBAction.Insert(st.getId(), st.getUser(), st.getText(), "Possitive");
					Possitive += 1;
				}

				if (EmoticonsValidator.isNegativeEmoticon(tweetMessage)) {
					DBAction.Insert(st.getId(), st.getUser(), st.getText(), "Negative");
					Negative += 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
