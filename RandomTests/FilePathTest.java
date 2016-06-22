package RandomTests;

import java.io.Console;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.CodeSource;

/**
 * Created by manojkarthicks on 6/20/2016.
 */
public class FilePathTest {
    public static void func() {
        try {
            CodeSource codeSource = FilePathTest.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            String folderName = "CyanideAndHappiness";
            File file = new File(jarDir + "\\" + folderName);
            file.mkdir();
            System.out.println(jarDir);

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


        }
    }