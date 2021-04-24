package webcrawlerbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Webcrawler {

    private Queue<String> queue;
    private List<String> discoveredWebsitesList;

    public Webcrawler() {
        this.queue = new LinkedList<>();
        this.discoveredWebsitesList = new ArrayList<>();
    }

    public void discoverWeb(String root) {
        this.queue.add(root);
        this.discoveredWebsitesList.add(root);

        while (!queue.isEmpty()) {
            String vertex = this.queue.remove();
            String rawHtml = readHtml(vertex);

            String regexp = "http://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHtml);

            while (matcher.find()) {

                String actualUrl = matcher.group();

                if (!discoveredWebsitesList.contains(actualUrl)) {
                    discoveredWebsitesList.add(actualUrl);
                    System.out.println("Website has been found with url" + actualUrl);
                    queue.add(actualUrl);
                }
            }
        }
    }

    private String readHtml(String vertex) {
        String rawHtml = "";

        try {

            URL url = new URL(vertex);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine = "";

            while ((inputLine = bufferedReader.readLine()) != null) {
                rawHtml += inputLine;
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return rawHtml;
    }


}
