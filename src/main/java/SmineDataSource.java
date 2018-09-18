import java.sql.Connection;
import java.sql.SQLException;

import javax.crypto.SecretKey;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import tools.GetProperties;
import tools.SmineDESUtil;
/**
 * Copyright 2018-2018 Beijing SmartMining Data & Tech Co.,Ltd.
 *
 * @creator zijian.zhang
 * @createTime 2018/9/13 10:37
 * @description
 */
public class SmineDataSource {

    private HikariDataSource ds;

    private HikariDataSource targetDataSource;

    private final String  reader_PW = "0IOP&!IUNA85B2&F";

    private final String reader_acc = "reader";

    public void initManagerPool(String userType, int Minimum, int Maximum){
        HikariConfig config = new HikariConfig("G:/workspace/IntelliJ/ericSmine/src/main/resources/config/hikari-mariadb-manager.properties");
        config.setUsername(userType);
        
    }


    public void init(String user,int Minimum,int Maximum){
        
        String configUrl;
        switch(user){
            case "admin" : configUrl = "G:/workspace/IntelliJ/ericSmine/src/main/resources/config/hikari-mariadb-manager.properties"; break;
            case "reader": configUrl = "G:/workspace/IntelliJ/ericSmine/src/main/resources/config/hikari-mariadb-manager.properties"; break;
            case "writer": configUrl = "G:/workspace/IntelliJ/ericSmine/src/main/resources/config/hikari-mariadb-manager.properties"; break;
            default: configUrl = "error"; break;
        }
        // SecretKey key = SmineDESUtil.generateKey("zksj2018");
        // GetProperties prop =  new GetProperties();
        // prop.setConfigUrl("G:/workspace/IntelliJ/ericSmine/src/main/resources/config/hikari-mariadb-target.properties");
        // data=SmineDESUtil.decrypt(data, key.getEncoded());
        HikariConfig config = new HikariConfig(configUrl);
        config.setUsername(reader_acc);
        config.setPassword(reader_PW); 

        //If your driver supports JDBC4 we strongly recommend not setting this property
        // config.setConnectionTestQuery("SELECT 1");
        config.setAutoCommit(true);
        //pool lazy conn count
        config.setMinimumIdle(Minimum);
        //pool max count
        config.setMaximumPoolSize(Maximum);
        ds = new HikariDataSource(config);
    }

    /**
     * destroy pool
     */
    public void close(){
        ds.close();
    }

    /**
     * get connection
     * @return
     */
    public Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
//            ds.resumePool();
            return null;
        }
    }

}
