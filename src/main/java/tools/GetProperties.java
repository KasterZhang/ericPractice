package tools;

import java.util.Properties;
import java.util.logging.Logger;

import org.mariadb.jdbc.internal.logging.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GetProperties {
    
    private Properties prop;
    private InputStream in;
    private String configUrl;
    
    public  GetProperties() throws IOException{
        
        in = new BufferedInputStream (new FileInputStream(configUrl));
        prop.load(in);
        System.out.println("please remember close inputStream with method closeIn()!");
    }
    public void closeIn() throws IOException {
        in.close();
    }
    public String getValue(String key){
        return prop.getProperty(key);
    }
}