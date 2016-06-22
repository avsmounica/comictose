package XKCD;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.File;
import java.security.CodeSource;

/**
 * Created by manojkarthicks on 6/11/2016.
 */
public class XKCDScraper {

    public static int totalNoOfComics;
    public static int NUM_OF_THREADS = 100;
    public static String filesPath;

    public static void startPoint() throws Exception {
        CodeSource codeSource = XKCDScraper.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        String folderName = "XKCD";
        File file = new File(jarDir + "\\" + folderName);
        file.mkdir();
        filesPath = jarDir + "\\" + folderName; //Change filepath here
        System.out.println("Downloading to: " + filesPath);
        System.out.println("\n Downloading. This may take some time depending on your Internet Connection..");
        String latestURL = "http://XKCD.com/";
        Document document = Jsoup.connect(latestURL).get();
        java.util.List<TextNode> nodes = document.getElementById("middleContainer").textNodes();
        for (Node node : nodes) {
            String textContent = node.toString();
            if (textContent.startsWith(" Permanent")) {
                String objects[] = textContent.split("xkcd.com/");
                totalNoOfComics = Integer.parseInt(objects[1].substring(0, objects[1].length() - 1));
            }

        }
        float perThreadComicsFloat = ((float) totalNoOfComics) / NUM_OF_THREADS;
        int perThreadComics = (int) Math.ceil(perThreadComicsFloat);
        Thread[] threadList = new Thread[NUM_OF_THREADS + 1];
        for (int i = 0; i <= NUM_OF_THREADS; i++) {
            int start = i * perThreadComics;
            int end = start + perThreadComics;
            threadList[i] = new XKCDThread(i, filesPath, start, end);
        }

        for (int i = 0; i <= NUM_OF_THREADS; i++) {
            threadList[i].join();
        }
        System.out.println("Download Complete. Happy Reading!");
    }
}
