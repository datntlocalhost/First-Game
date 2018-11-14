package datnt.gmoz.com.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import datnt.gmoz.com.common.CommonConstants;

/**
 * The Class FileUtil.
 */
public class FileUtil {

    /**
     * Read content file.
     *
     * @param file the file
     * @return the string
     */
    public static StringBuffer readContentFile(String file) {
        
        StringBuffer content = new StringBuffer();
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(file));
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                content.append(line).append(CommonConstants.STR_NEW_LINE);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        return content;
    }
}
