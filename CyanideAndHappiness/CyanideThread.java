package CyanideAndHappiness;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * Created by manojkarthicks on 5/30/2016.
 */
public class CyanideThread extends Thread {

    private int threadID;
    private String filesPath;
    private int start;
    private int end;
    private String baseURL = "http://explosm.net/comics/";

    public CyanideThread(int ThreadID, String filesPath, int start, int end) {
        this.threadID = ThreadID;
        this.filesPath = filesPath;
        this.start = start;
        this.end = end;
        this.start();
    }

    public void run() {

        int start = this.start;
        int end = this.end;
        for (int i = start; i <= end; i++) {
            int ID = i;
            String URL = baseURL + String.valueOf(ID) + "/";
            Document document;
            try {
                document = Jsoup.connect(URL).get();
            } catch (Exception e) {
                continue;
            }
            try {
                Element imageElement = document.getElementById("main-comic");
                String absoluteUrl = imageElement.absUrl("src");
                java.net.URL imageURL = new URL(absoluteUrl);
                BufferedImage image = ImageIO.read(imageURL);
                if (imageURL.toString().endsWith("png")) {
                    ImageIO.write(image, "png", new File(filesPath + "\\" + String.valueOf(ID) + ".png"));
                } else {
                    ImageIO.write(image, "jpg", new File(filesPath + "\\" + String.valueOf(ID) + ".png"));
                }

            } catch (Exception e) {

            }
        }
    }
}

