package com.cogu.shop.tools;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: Cogu
 * @Date: 2018/7/2 10:28
 * @Description:
 */
public class uploadFile {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}