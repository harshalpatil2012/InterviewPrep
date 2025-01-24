package com.practice.ood;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a system for putting newspapers using classes and functions
 * 
 * Create a Newspaper class with attributes like title, date, and content
 * 
 * Create a Publisher class with methods to publish and distribute newspapers
 * 
 * Create a Subscriber class with methods to subscribe and receive newspapers
 * 
 * Use inheritance to create different types of newspapers like daily, weekly,
 * etc.
 * 
 * Implement a database to store newspaper information and handle subscriptions
 * 
 * @author harshal
 *
 */
public class NewsPaperDistribution {

	public static void main(String[] args) {
		NewspaperDatabase database = new NewspaperDatabase();

		DailyNewspaper dailyNewspaper = new DailyNewspaper("Daily News", new Date(), "Daily newspaper content");
		WeeklyNewspaper weeklyNewspaper = new WeeklyNewspaper("Weekly Digest", new Date(), "Weekly newspaper content");

		database.addNewspaper("daily", dailyNewspaper);
		database.addNewspaper("weekly", weeklyNewspaper);

		Subscriber subscriber1 = new Subscriber();
		Subscriber subscriber2 = new Subscriber();

		database.subscribe(subscriber1);
		database.subscribe(subscriber2);

		database.publishNewspapers();
	}
}

class NewspaperDatabase {
	private Map<String, Newspaper> newspaperMap;
	private List<Subscriber> subscribers;

	public NewspaperDatabase() {
		newspaperMap = new HashMap<>();
		subscribers = new ArrayList<>();
	}

	public void addNewspaper(String key, Newspaper newspaper) {
		newspaperMap.put(key, newspaper);
	}

	public void subscribe(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	public void publishNewspapers() {
		Publisher publisher = new Publisher();
		publisher.publishNewspaper(newspaperMap.get("daily"));
		publisher.publishNewspaper(newspaperMap.get("weekly"));
		publisher.distributeNewspapers(subscribers);
	}
}

class Subscriber {
	private List<Newspaper> subscribedNewspapers;

	public Subscriber() {
		subscribedNewspapers = new ArrayList<>();
	}

	public void subscribeToNewspaper(Newspaper newspaper) {
		subscribedNewspapers.add(newspaper);
	}

	public void receiveNewspaper(List<Newspaper> newspapers) {
		for (Newspaper newspaper : newspapers) {
			if (subscribedNewspapers.contains(newspaper)) {
				// Perform actions to receive the newspaper (e.g., print, display, etc.)
				System.out.println("Received newspaper: " + newspaper.getTitle());
			}
		}
	}
}

class Publisher {
	private List<Newspaper> newspapers;

	public Publisher() {
		newspapers = new ArrayList<>();
	}

	public void publishNewspaper(Newspaper newspaper) {
		newspapers.add(newspaper);
	}

	public void distributeNewspapers(List<Subscriber> subscribers) {
		for (Subscriber subscriber : subscribers) {
			subscriber.receiveNewspaper(newspapers);
		}
	}
}

class Newspaper {
	private String title;
	private Date publicationDate;
	private String content;

	public Newspaper(String title, Date publicationDate, String content) {
		this.title = title;
		this.publicationDate = publicationDate;
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

class DailyNewspaper extends Newspaper {
	public DailyNewspaper(String title, Date publicationDate, String content) {
		super(title, publicationDate, content);
	}
}

class WeeklyNewspaper extends Newspaper {
	public WeeklyNewspaper(String title, Date publicationDate, String content) {
		super(title, publicationDate, content);
	}
}
