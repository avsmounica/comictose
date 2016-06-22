package Dilbert;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * Created by manojkarthicks on 6/11/2016.
 */
public class DilbertThread extends Thread {
    private int threadID;
    private String filesPath;
    private org.joda.time.LocalDate start;
    private org.joda.time.LocalDate end;
    private static String baseURL = "http://dilbert.com/strip/";

    public DilbertThread(int threadID, String filesPath, org.joda.time.LocalDate start, org.joda.time.LocalDate end) {
        this.threadID = threadID;
        this.filesPath = filesPath;
        this.start = start;
        this.end = end;
        this.start();
    }

    public void run() {
        org.joda.time.LocalDate start = this.start;
        org.joda.time.LocalDate end = this.end;
        for (org.joda.time.LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            String URL = baseURL + date.toString();
            Document document;
            try {
                document = Jsoup.connect(URL).userAgent("Mozilla/5.0 (X11; U; Linux i586; en-US; rv:1.7.3) Gecko/20040924 Epiphany/1.4.4 (Ubuntu)").timeout(0).get();
            } catch (Exception e) {
                continue;
            }
            try {
                Element element = document.getElementsByClass("img-comic-link").get(0);
                String absoluteUrl = element.getElementsByAttribute("src").first().absUrl("src");
                java.net.URL imageURL = new URL(absoluteUrl);
                BufferedImage image = ImageIO.read(imageURL);
                if (imageURL.toString().endsWith("png")) {
                    ImageIO.write(image, "png", new File(filesPath + "\\" + date.toString() + ".png"));
                } else if (imageURL.toString().endsWith("jpg")) {
                    ImageIO.write(image, "jpg", new File(filesPath + "\\" + date.toString() + ".jpg"));
                } else {
                    ImageIO.write(image, "gif", new File(filesPath + "\\" + date.toString() + ".gif"));
                }
            } catch (Exception e) {

            }
        }
    }
}
