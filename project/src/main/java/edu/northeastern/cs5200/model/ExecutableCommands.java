package edu.northeastern.cs5200.model;

import java.sql.SQLException;

public class ExecutableCommands {
		
	public void execute() throws ClassNotFoundException, SQLException {
		
		// Creates 
		Developer d1 = new Developer(1, "Alice", "Wonder", "alice", "wonder", "alice@wonder", null, "4321rewq");
		Developer d2 = new Developer(2, "Bob", "Marley", "bob", "bob", "bob@marley", null, "5432trew");
		Developer d3 = new Developer(3, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia", null, "6543ytre");
		User u1 = new User(4, "Dan", "Martin", "dan", "dan", "dan@martin", null, false);
		User u2 = new User(5, "Ed", "Karaz", "ed", "ed", "ed@kar", null, false);
		
		DeveloperDao dev = DeveloperDao.getInstance();
		UserDao user = UserDao.getInstance();
		
		dev.createDeveloper(d1);
		dev.createDeveloper(d2);
		dev.createDeveloper(d3);
		user.createUser(u1);
		user.createUser(u2);
		
		Website w1 = new Website(1, d1.getId(), "Facebook", "an online social media and social networking service", 1234234);
		Website w2 = new Website(2, d2.getId(), "Twitter", "an online news and social networking service", 4321543);
		Website w3 = new Website(3, d3.getId(), "Wikipedia", "a free online encyclopedia", 3456654);
		Website w4 = new Website(4, d1.getId(), "CNN", "an American basic cable and satellite television news channel", 6543345);
		Website w5 = new Website(5, d2.getId(), "CNET", "an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics", 5433455);
		Website w6 = new Website(6, d3.getId(), "Gizmodo", "a design, technology, science and science fiction website that also writes articles on politics", 4322345);

		WebsiteDao w = WebsiteDao.getInstance();
		
		w.createWebsiteForDeveloper(w1.getDeveloperId(), w1);
		w.createWebsiteForDeveloper(w2.getDeveloperId(), w2);
		w.createWebsiteForDeveloper(w3.getDeveloperId(), w3);
		w.createWebsiteForDeveloper(w4.getDeveloperId(), w4);
		w.createWebsiteForDeveloper(w5.getDeveloperId(), w5);
		w.createWebsiteForDeveloper(w6.getDeveloperId(), w6);
		
		RoleDao rd = RoleDao.getInstance();
		
		rd.assignWebsiteRole(d2.getId(), w1.getId(), Role.editor);
		rd.assignWebsiteRole(d3.getId(), w1.getId(), Role.admin);
		
		rd.assignWebsiteRole(d3.getId(), w2.getId(), Role.editor);
		rd.assignWebsiteRole(d1.getId(), w2.getId(), Role.admin);
		
		rd.assignWebsiteRole(d1.getId(), w3.getId(), Role.editor);
		rd.assignWebsiteRole(d2.getId(), w3.getId(), Role.admin);
		
		rd.assignWebsiteRole(d2.getId(), w4.getId(), Role.editor);
		rd.assignWebsiteRole(d3.getId(), w4.getId(), Role.admin);
		
		rd.assignWebsiteRole(d3.getId(), w5.getId(), Role.editor);
		rd.assignWebsiteRole(d1.getId(), w5.getId(), Role.admin);
		
		rd.assignWebsiteRole(d1.getId(), w6.getId(), Role.editor);
		rd.assignWebsiteRole(d2.getId(), w6.getId(), Role.admin);
		
		Page p1 = new Page(1, w5.getId(), "Home", "Landing page", 123434);
		Page p2 = new Page(2, w6.getId(), "About", "Website description", 234545);
		Page p3 = new Page(3, w3.getId(), "Contact", "Addresses, phones, and contact info", 345656);
		Page p4 = new Page(4, w4.getId(), "Preferences", "Where users can configure their preferences", 456776);
		Page p5 = new Page(5, w5.getId(), "Profile", "Users can configure their personal information", 567878);
		
		PageDao p = PageDao.getInstance();
		p.createPageForWebsite(p1.getWebsiteId(), p1);
		p.createPageForWebsite(p2.getWebsiteId(), p2);
		p.createPageForWebsite(p3.getWebsiteId(), p3);
		p.createPageForWebsite(p4.getWebsiteId(), p4);
		p.createPageForWebsite(p5.getWebsiteId(), p5);
		
		rd.assignPageRole(d1.getId(), p1.getId(), Role.editor);
		rd.assignPageRole(d2.getId(), p1.getId(), Role.reviewer);
		rd.assignPageRole(d3.getId(), p1.getId(), Role.writer);
		
		rd.assignPageRole(d2.getId(), p2.getId(), Role.editor);
		rd.assignPageRole(d3.getId(), p2.getId(), Role.reviewer);
		rd.assignPageRole(d1.getId(), p2.getId(), Role.writer);
		
		rd.assignPageRole(d3.getId(), p3.getId(), Role.editor);
		rd.assignPageRole(d1.getId(), p3.getId(), Role.reviewer);
		rd.assignPageRole(d2.getId(), p3.getId(), Role.writer);
		
		rd.assignPageRole(d1.getId(), p4.getId(), Role.editor);
		rd.assignPageRole(d2.getId(), p4.getId(), Role.reviewer);
		rd.assignPageRole(d3.getId(), p4.getId(), Role.writer);
		
		rd.assignPageRole(d2.getId(), p5.getId(), Role.editor);
		rd.assignPageRole(d3.getId(), p5.getId(), Role.reviewer);
		rd.assignPageRole(d1.getId(), p5.getId(), Role.writer);
		
		HeadingWidget wd1 = new HeadingWidget(1, p1.getId(), "head123", 0, 0, null, null, "Welcome", 0, WidgetType.Heading, 0);
		HtmlWidget wd2 = new HtmlWidget(2, p2.getId(), "post234", 0, 0, null, null, null, 0, WidgetType.Html, "<p>Lorem</p>");
		HeadingWidget wd3 = new HeadingWidget(3, p3.getId(), "head345", 0, 0, null, null, "Hi", 1, WidgetType.Heading, 0);
		HtmlWidget wd4 = new HtmlWidget(4, p3.getId(), "intro456", 0, 0, null, null, null, 2, WidgetType.Html, "<h1>Hi</h1>");
		ImageWidget wd5 = new ImageWidget(5, p3.getId(), "image345", 50, 100, null, null, null, 3, WidgetType.Image, "/img/567.png");
		YouTubeWidget wd6 = new YouTubeWidget(6, p4.getId(), "video456", 400, 300, null, null, null, 0, WidgetType.YouTube, "https://youtu.be/h67VX51QXiQ", false, false);		
		
		WidgetDao wd = WidgetDao.getInstance();
		wd.createWidgetForPage(wd1.getPageId(), wd1);
		wd.createWidgetForPage(wd2.getPageId(), wd2);
		wd.createWidgetForPage(wd3.getPageId(), wd3);
		wd.createWidgetForPage(wd4.getPageId(), wd4);
		wd.createWidgetForPage(wd5.getPageId(), wd5);
		wd.createWidgetForPage(wd6.getPageId(), wd6);
		
		// Updates
		
	}

}
