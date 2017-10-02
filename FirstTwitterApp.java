import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import twitter4j.*;

public class FirstTwitterApp {
	public static void main(String args[]) throws TwitterException {
		BufferedWriter writer = null;
		int i = 1;

		try {
			GetTweets gt = new GetTweets();
			gt.DBAction.CreateDB();
			gt.get();
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream("C:\\Users\\Alex\\Desktop\\tweets.txt"), "utf-8"));

			for (Status st : gt.tweetsList) {
				writer.write(i + ". " + st.getUser().getScreenName() + " : " + st.getText() + "\n\n");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
