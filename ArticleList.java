// class: ArticleList
// written by: Mr. Swope
// date: 11/1/2023
// description: This class contains an ArrayList of Articles that are fetched from newsapi.org. 
//              Several accessors and modifiers will be added. 
import parser.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ArticleList {

	private ArrayList<Article> articles;
	private ArrayList<Article> withKeyword;
	private ArrayList<Article> withAuthor;
	private String topic;
	private String author;
	private int year, month;
	private Date date;

	
	//default constructor
	public ArticleList() {

		System.out.println("Constructor topic parameter = " + topic);
		articles = new ArrayList<>();
		withKeyword = new ArrayList<>();
		withAuthor = new ArrayList<>();
		topic = "eagles";
		author = "Anne Demoulin";
		fetchArticles();
	}

	//semi packed?
	public ArticleList(String topic, String author) {
		articles = new ArrayList<>();
		withKeyword = new ArrayList<>();
		withAuthor = new ArrayList<>();
		this.topic = topic;
		this.author = author;

		fetchArticles();


	}

	public String getTopic() {
		return topic;
	}

	public String getAuthor() {
		return author;
	}

	public void setTopic(String topic) {
		this.topic = topic;
		fetchArticles();
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	//remove article
	public void removeAuthor(String author) {
		for(int i = 0; i < articles.size(); i ++) {
			if(articles.get(i).getAuthor().equals(author)) {
				articles.remove(i);
			}
		}
	}



	public ArrayList< Article > findKeyWord(String word) {
		ArrayList<Article> withKeyword = new ArrayList<>();
		for(Article a : articles) {
			if(a.getTitle().contains(word)) {
				withKeyword.add(a);
			}
		}
		return withKeyword;
	}

	public ArrayList< Article > getArticles(String author){
		ArrayList<Article> withAuthor = new ArrayList<>();
		for(Article a : articles) {
			if(a.getAuthor().contains(author)) {
				withAuthor.add(a);
			}
		}
		return withAuthor;
	}

	// method: swap
	// description: Switches positions of article1 and article2 in articles. Remember that Articles are objects; You shouldn’t copy individual properties,
	//	              instead you should move objects.
	// parameters: the indexes of the two articles that should be swapped in the Arraylist
	// return: void
	public void swap(int article1, int article2) {
		Article placeHolder = articles.get(article1);
		articles.set(article1,articles.get(article2));
		articles.set(article2, placeHolder);
	}

	// method: removeArticles
	// description:  Removes all Articles that were written before date. This method will be a bit tricky because you’ll need to make use of the Date class API.
	// parameters: Three integers that represent the year, month and and date before which all articles will be removed
	// return:  void
	public void removeArticles(int year, int month, int date) {

		for(int i = 0; i < articles.size(); i ++) {
			if(articles.get(i).getDate().getYear() <= year && articles.get(i).getDate().getMonth() <= month && articles.get(i).getDate().getDate() < date) {
				articles.remove(i);
			}

		}
	}

	// method: getContent
	// description: Returns the content of the Article with the given title. If there isn't an article with
	//	              the given title, the function should return "article not found.
	// parameters: String title - the title of the article that the method is looking for.
	// return: A string that contains the content of the article with title.
	public String getContent(String title) {
		String placeHolder = "Article not found.";
		for(Article a: articles) {
			if(a.getTitle().equalsIgnoreCase(title)) {
				placeHolder = a.getContent();

			}
		}
		return placeHolder;
	}


	// method: getNumberOfArticles()
	// description: Returns the number of Articles in the ArrayList.
	// parameters: none
	// return: The number of articles in the Article arraylist.
	public int getNumberOfArticles() {
		return articles.size();
	}
	
	// method: toString()
	// description: Returns a String representation of all Articles in the ArrayList. This string should contain the topic
//	              for your Articles and how many there are, along with each Article separated by a newline, which can be
//	              inserted into a string as “\n”.  
	// parameters: none
	// return: A string representation of Article list. An example of what this string would look like when printed is
//	         shown below.
	public String toString() {
	    String result = "Topic: " + topic + "\n";
	    result += "Number of articles: " + articles.size() + "\n\n";

	    for (Article a : articles) {
	        result += a + "\n";
	    }

	    return result;
	}

	
	public Date buildDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, 9); // year, month and date are all integers. (Month is zero based, so you have to subtract 1)
		Date date = cal.getTime();    // Creates a new Date object @ date/month/year
		
		return cal.getTime();
	}
	
	private void fetchArticles() {
	    articles.clear();

	    System.out.println("FETCHING ARTICLES FOR TOPIC = " + topic);
	    
	    ArticleGetter a = new ArticleGetter(topic, "0165439630a24028bcf3ab1d2974d6d2");
	    JSONArray arr = a.getArticles();

	    if (arr != null) {
	        for (int i = 0; i < arr.size(); i++) {
	            JSONObject obj = (JSONObject) arr.get(i);
	            articles.add(new Article(obj, topic));
	        }
	    }
	}








}
