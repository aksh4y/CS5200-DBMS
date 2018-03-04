package edu.northeastern.cs5200.model;

public class Testing {

	/*public static void main(String[] args) {
		System.out.println("[TESTING] QUESTION 1: INSERT DEVELOPERS AND USERS");

		Developer d1 = new Developer(0, "Alice", "Wonder", "alice", "alice", "alice@wonder.com", null, "4321rewq");
		Developer d2 = new Developer(1, "Bob", "Marley", "bob", "bob", "bob@marley.com", null, "5432trew");
		Developer d3 = new Developer(2, "Charles", "Garcia", "charlie", "charlie", "chuch@garcia.com", null, "6543ytre");
		Developer d4 = new Developer(3, "Dan", "Martin", "dan", "dan", "dan@martin.com", null, "7654fda");
		Developer d5 = new Developer(4, "Ed", "Karaz", "ed", "ed", "ed@kar.com", null, "5678dfgh");

		DeveloperDao dev = DeveloperDao.getInstance();


		dev.createDeveloper(d1);
		dev.createDeveloper(d2);
		dev.createDeveloper(d3);
		dev.createUser(d4);
		dev.createUser(d5);

		assert dev.findAllDevelopers().size() == 5;
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 2: INSERT WEBSITES");

		Website w1 = new Website(0, d1.getId(), "Facebook", "an online social media and social networking service", null, null, 1234234);
		Website w2 = new Website(1, d2.getId(), "Twitter", "an online news and social networking service", null, null, 4321543);
		Website w3 = new Website(2, d3.getId(), "Wikipedia", "a free online encyclopedia", null, null, 3456654);
		Website w4 = new Website(3, d1.getId(), "CNN", "an American basic cable "
				+ "and satellite television news channel", null, null, 6543345);

		Website w5 = new Website(4, d2.getId(), "CNET", "an American media website that publishes reviews, news, articles, blogs, "
				+ "podcasts and videos on technology and consumer electronics", null, null, 5433455);

		Website w6 = new Website(5, d3.getId(), "Gizmodo", "a design, technology, science and science "
				+ "fiction website that also writes articles on politics", null, null, 4322345);

		WebsiteDao web = WebsiteDao.getInstance();

		web.createWebsiteForDeveloper(w1.getUser().getId(), w1);
		web.createWebsiteForDeveloper(w2.getUser().getId(), w2);
		web.createWebsiteForDeveloper(w3.getUser().getId(), w3);
		web.createWebsiteForDeveloper(w4.getUser().getId(), w4);
		web.createWebsiteForDeveloper(w5.getUser().getId(), w5);
		web.createWebsiteForDeveloper(w6.getUser().getId(), w6);

		assert web.findAllWebsites().size() == 6;
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 2: INSERT WEBSITES - IMPLEMENT ROLES");

		RoleDao role = DaoUtilities.roleSingleton;

		//Facebook
		role.assignWebsiteRole(d2.getId(), w1.getId(), Role.EDITOR.id);
		role.assignWebsiteRole(d3.getId(), w1.getId(), Role.ADMIN.id);

		//Twitter
		role.assignWebsiteRole(d3.getId(), w2.getId(), Role.EDITOR.id);
		role.assignWebsiteRole(d1.getId(), w2.getId(), Role.ADMIN.id);

		//Wikipedia
		role.assignWebsiteRole(d1.getId(), w3.getId(), Role.EDITOR.id);
		role.assignWebsiteRole(d2.getId(), w3.getId(), Role.ADMIN.id);

		//CNN
		role.assignWebsiteRole(d2.getId(), w4.getId(), Role.EDITOR.id);
		role.assignWebsiteRole(d3.getId(), w4.getId(), Role.ADMIN.id);

		//CNET
		role.assignWebsiteRole(d3.getId(), w5.getId(), Role.EDITOR.id);
		role.assignWebsiteRole(d1.getId(), w5.getId(), Role.ADMIN.id);

		//Gizmodo
		role.assignWebsiteRole(d1.getId(), w6.getId(), Role.EDITOR.id);
		role.assignWebsiteRole(d2.getId(), w6.getId(), Role.ADMIN.id);

		System.out.println("[TESTING]: ROLE INSERT SUCCESSFUL");
		System.out.println("[TESTING] QUESTION 3: INSERT PAGES");

		PageDao pages = DaoUtilities.pageSingleton;

		Page p1 = new Page(0, w5, "Home", "Landing page", 123434);
		Page p2 = new Page(1, w6, "About", "Website description", 234545);
		Page p3 = new Page(2, w3, "Contact", "Addresses, phones, and contact info", 345656);
		Page p4 = new Page(3, w4, "Preferences", "Where users can configure their preferences", 456776);
		Page p5 = new Page(4, w5, "Profile", "Users can configure their personal information", 567878);

		pages.createPageForWebsite(w5.getId(), p1);
		pages.createPageForWebsite(w6.getId(), p2);
		pages.createPageForWebsite(w3.getId(), p3);
		pages.createPageForWebsite(w4.getId(), p4);
		pages.createPageForWebsite(w5.getId(), p5);

		assert pages.findAllPages().size() == 5;
		System.out.println("[TESTING]: TEST SUCCESSFUL");
		System.out.println("[TESTING] QUESTION 3: INSERT PAGES - IMPLEMENT ROLES");

		//Home
		role.assignPageRole(d1.getId(), p1.getId(), Role.EDITOR.id);
		role.assignPageRole(d2.getId(), p1.getId(), Role.REVIEWER.id);
		role.assignPageRole(d3.getId(), p1.getId(), Role.WRITER.id);

		//About
		role.assignPageRole(d2.getId(), p2.getId(), Role.EDITOR.id);
		role.assignPageRole(d3.getId(), p2.getId(), Role.REVIEWER.id);
		role.assignPageRole(d1.getId(), p2.getId(), Role.WRITER.id);

		//Contact
		role.assignPageRole(d3.getId(), p3.getId(), Role.EDITOR.id);
		role.assignPageRole(d1.getId(), p3.getId(), Role.REVIEWER.id);
		role.assignPageRole(d2.getId(), p3.getId(), Role.WRITER.id);

		//Preference
		role.assignPageRole(d1.getId(), p4.getId(), Role.EDITOR.id);
		role.assignPageRole(d2.getId(), p4.getId(), Role.REVIEWER.id);
		role.assignPageRole(d3.getId(), p4.getId(), Role.WRITER.id);

		//Profile
		role.assignPageRole(d2.getId(), p5.getId(), Role.EDITOR.id);
		role.assignPageRole(d3.getId(), p5.getId(), Role.REVIEWER.id);
		role.assignPageRole(d1.getId(), p5.getId(), Role.WRITER.id);

		System.out.println("[TESTING]: ROLE INSERT SUCCESSFUL");
		System.out.println("[TESTING] QUESTION 4: INSERT WIDGETS");

		WidgetDao wdgts = DaoUtilities.widgetSingleton;

		Widget wd1 = new HeadingWidget(1, p1, "head123", 0, 0, "Welcome", 0, 2);
		Widget wd2 = new HtmlWidget(2, p2, "post234", 0, 0, "", 0, "<p>Lorem</p>");
		Widget wd3 = new HeadingWidget(3, p3, "head345", 0, 0, "Hi", 1, 2);
		Widget wd4 = new HtmlWidget(4, p3, "intro456", 0, 0, "", 2, "<h1>Hi</h1>");
		Widget wd5 = new ImageWidget(5, p3, "image345", 50, 100, null, 3, "/img/567.png");
		Widget wd6 = new YouTubeWidget(6, p4, "video456", 400, 300, 0, "https://youtu.be/h67VX51QXiQ", false, false);

		wdgts.createWidgetForPage(p1.getId(), wd1);
		wdgts.createWidgetForPage(p2.getId(), wd2);
		wdgts.createWidgetForPage(p3.getId(), wd3);
		wdgts.createWidgetForPage(p3.getId(), wd4);
		wdgts.createWidgetForPage(p3.getId(), wd5);
		wdgts.createWidgetForPage(p4.getId(), wd6);

		assert wdgts.findAllWidgets().size() == 6;
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 5[a]: UPDATE WIDGET ORDER");
		Widget wd3_new = new HeadingWidget(wd3.getId(), p3, wd3.getName(), wd3.getWidth(), 
				wd3.getHeight(), wd3.getText(), 3, ((HeadingWidget) wd3).getSize());
		wdgts.updateWidget(wd3_new.getId(), wd3_new);
		Collection<Widget> ws = wdgts.findAllWidgets();
		ArrayList<Widget> widgets = new ArrayList<Widget>(ws);
		assert widgets.get(2).getOrder() == 3;
		assert widgets.get(4).getOrder() == 1;
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 5[b]: APPEND TEXT");
		ArrayList<Page> cnetPages = new ArrayList<Page>(pages.findPagesForWebsite(w5.getId()));
		for(Page page : cnetPages) {
			Page new_page = new Page(page.getId(), page.getWebsite(), "CNET - " + page.getTitle(), page.getDescription(), page.getViews());
			pages.updatePage(new_page.getId(), new_page);
		}
		System.out.println("[TESTING]: TEST SUCCESSFUL");
		System.out.println("[TESTING] QUESTION 5[c]: SWAP ROLES");
		role.swapPageRole(d2.getId(), d3.getId(), p1.getId());
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 6[a]: DELETE HIGHEST WIDGET");
		ArrayList<Widget> contactWidgets = new ArrayList<Widget>(wdgts.findWidgetsForPage(p3.getId()));
		int greatestOrder = -1;
		int widgetId = -1;
		for(Widget widget : contactWidgets) {
			if(widget.getOrder() > greatestOrder) {
				greatestOrder = widget.getOrder();
				widgetId = widget.getId();
			}
		}
		wdgts.deleteWidget(widgetId);
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 6[b]: DELETE LAST UPDATED PAGE");
		ArrayList<Page> wikiPages = new ArrayList<Page>(pages.findPagesForWebsite(w3.getId()));
		Date latestDate = null;
		int pageId = -1;
		for(Page page : wikiPages) {
			if(latestDate == null || latestDate.compareTo(page.getUpdated()) == 1) {
				latestDate = page.getUpdated();
				pageId = page.getId();
			}
		}
		pages.deletePage(pageId);
		System.out.println("[TESTING]: TEST SUCCESSFUL");

		System.out.println("[TESTING] QUESTION 6[c]: DELETE WEBSITE");
		web.deleteWebsite(w5.getId());
		System.out.println("[TESTING]: TEST SUCCESSFUL");
		System.out.println("ALL TESTS SUCCESSFUL! TERMINATING PROGRAM");
		System.exit(0);
	}
*/}
