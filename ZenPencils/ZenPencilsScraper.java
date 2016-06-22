package ZenPencils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.CodeSource;
import java.util.List;

/**
 * Created by manojkarthicks on 6/12/2016.
 */
public class ZenPencilsScraper {

    private static String filesPath;
    public static void startPoint()
    {
        String baseURL ="http://zenpencils.com/";
        Document document;
        try
        {
            CodeSource codeSource = ZenPencilsScraper.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            File file1 = new File(jarDir + "\\" + "ZenPencils");
            file1.mkdir();
            filesPath = jarDir + "\\" + "ZenPencils"; //Change filepath here
            System.out.println("Downloading to: " + filesPath);
            System.out.println("\n Downloading. This may take some time depending on your Internet Connection..");
            document = Jsoup.connect(baseURL).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").get();
            Elements elements = document.getElementsByClass("level-0");
            String userAgents[] = {"Opera/9.80 (X11; Linux i686; Ubuntu/14.10) Presto/2.12.388 Version/12.16","Mozilla/5.0 (X11; U; Linux i586; en-US; rv:1.7.3) Gecko/20040924 Epiphany/1.4.4 (Ubuntu)"
                                    ,"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36",
                                    "Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10"};
            int index = 0,totalSize = elements.size()/2;

            for(Element element : elements)
            {
                if(index<=totalSize)
                {
                    String absoluteUrl = element.absUrl("value");
                    Document document1 = Jsoup.connect(absoluteUrl).timeout(0).userAgent(userAgents[index%5]).get();
                    index++;
                    List<Node> nodes = document1.getElementById("comic").childNodes();
                    String[] splits = absoluteUrl.split("/");
                    String folderName = splits[splits.length-1];
                    File file = new File(filesPath + "\\" + folderName);
                    file.mkdir();
                    for(Node node : nodes)
                    {
                        if(node.toString().startsWith("<img"))
                        {
                            String imgURL = node.absUrl("src");
                            String fileName = imgURL.split("/")[imgURL.split("/").length - 1];
                            URL url = new URL(imgURL);
                            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                            httpcon.addRequestProperty("User-Agent", userAgents[index%5]);
                            java.net.URL imageURL = new URL(imgURL);
                            BufferedImage image = ImageIO.read(httpcon.getInputStream());
                            if(imageURL.toString().endsWith("png"))
                            {
                                ImageIO.write(image,"png",new File(file.toString() + "\\" + fileName + ".png"));
                            }
                            else if(imageURL.toString().endsWith("jpg"))
                            {
                                ImageIO.write(image,"jpg",new File(file.toString() + "\\" + fileName +  ".jpg"));
                            }
                            else
                            {
                                ImageIO.write(image,"gif",new File(file.toString() + "\\" + fileName +  ".gif"));
                            }
                        }
                    }

                }
                else {
                    break;
                }

            }
        }
        catch(Exception e)
        {

        }
    }
}
