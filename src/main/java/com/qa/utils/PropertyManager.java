package com.qa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    Properties props = new Properties();
    TestUtils utils = new TestUtils();

    public Properties getProps() throws IOException {
        InputStream is = null;
        String propsFileName = "config.properties";

        if(props.isEmpty())
        {
            try {
                utils.log().info("Config properties loading");
                is = getClass().getClassLoader().getResourceAsStream(propsFileName);
                props.load(is);
            } catch (IOException e) {
               e.printStackTrace();
            }
            finally {
                if(is!=null)
                {
                    is.close();
                }
            }
        }
        return props;
    }

}
