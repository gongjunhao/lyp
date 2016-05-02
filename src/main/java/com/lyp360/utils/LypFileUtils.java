package com.lyp360.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by junhao on 2016/5/2.
 */
public class LypFileUtils {

    private static LypFileUtils instance;

    public static LypFileUtils getInstance() {
        if(instance == null){
            instance = new LypFileUtils();
        }
        return instance;
    }

    public String generatSavePath(){
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("lyp.properties"));
            String insuranceAttachPath = prop.getProperty("insuranceAttachPath").trim();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dateString = sdf.format(new Date());
            String path = insuranceAttachPath + "/"+ dateString + "/";
            File dir = new File(path);
            if(!dir.exists()){
                dir.mkdirs();
            }
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getInstance().generatSavePath();
    }
}
