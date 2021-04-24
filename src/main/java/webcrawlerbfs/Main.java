package webcrawlerbfs;

public class Main {

    public static void main(String[] args) {

        Webcrawler crawler = new Webcrawler();

        String rootUrl = "https://www.bbc.com/";

        crawler.discoverWeb(rootUrl);

    }

}
