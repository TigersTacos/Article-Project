// project: ArticleList
// description: This project will make an API call to newsapi.org, parse the data and then allow
//              the user to make execute several methods on the ArticleList.
// written by: Mr. Swope
// date: 11/1/2023
//updated by: benjamin whitten
//date: feb 9 2026
//description: this is an update/completion of the main class to have a lot more features/interface.
import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String ChosenTopic;
		String ChosenAuthor;
		System.out.println("What topic do you want to search for?");
		ChosenTopic = s.nextLine();
		System.out.println("What author do you not want?");
		ChosenAuthor = s.nextLine();
		
		ArticleList Chosen = new ArticleList(ChosenTopic, ChosenAuthor);
		Chosen.getTopic();
		
		System.out.println(Chosen);

	}

}
