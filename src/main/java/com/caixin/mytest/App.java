package com.caixin.mytest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * 
 */
public class App {

    private final static String moveToDirPath = "E:\\pics\\";

    private static Integer i = 0;
    
    private static void findFile(File dirFile) {
      
        if (dirFile.exists()) {
            for (File fileItem : dirFile.listFiles()) {
                if (fileItem.isDirectory()) {
                    //递归调用
                    findFile(fileItem);
                } else {
                    //移动
                    //创建
                    File moveToDir = new File(moveToDirPath);
                    if (!moveToDir.exists()) {
                        moveToDir.mkdirs();
                    }
                    //copy
                    if (FileTypeUtils.isImage(fileItem)) {
                        i++;
                        File destFile = new File(moveToDirPath + i+"."+FileTypeUtils.getImageFileType(fileItem));
                        try {
                            FileUtils.copyFile(fileItem, destFile);
                            System.out.println("移动第"+i+"张完成。");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    
                }
            }
        }
    }

    public static void main(String[] args) {
        String dirPath = "E:\\pic";
        File dirFile = new File(dirPath);
        try {
            findFile(dirFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
