package com.caixin.mytest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

/**
 * Hello world!
 */
public class ImagesUtil {

    private static String OS_NAME = System.getProperty("os.name").toLowerCase();

    /**
     * 读取配置文件
     */

    private static String getGraphicsMagickPath() {
        String path = "";
        try {
            path = "D://Program Files//GraphicsMagick-Q8";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
     * 
     * @param imagePath 源图片路径
     * @param newPath 处理后图片路径
     * @param width 缩放后的图片宽度
     * @param height 缩放后的图片高度
     * @return 返回true说明缩放成功,否则失败
     */
    public static boolean zoomImage(String imagePath, String newPath, Integer width, Integer height,String quality) {
        boolean flag = false;
        try {
            IMOperation op = new IMOperation();
            op.addImage(imagePath);
            if (width == null) {// 根据高度缩放图片
                op.resize(null, height);
            } else if (height == null) {// 根据宽度缩放图片
                op.resize(width, null);
            } else {
                op.resize(width, height);
            }
            op.addImage(newPath);
            if((quality !=null && quality.equals(""))){
                op.addRawArgs("-quality" ,  quality );
               }
            ConvertCmd convert = new ConvertCmd(true);
            if (OS_NAME.indexOf("win") >= 0) {
                // linux下不要设置此值，不然会报错
                String path = getGraphicsMagickPath();
                if (StringUtils.isNotBlank(path))
                    convert.setSearchPath(path);
            }
            convert.run(op);
            flag = true;
        } catch (IOException e) {
            flag = false;
            e.printStackTrace();
        } catch (InterruptedException e) {
            flag = false;
            e.printStackTrace();
        } catch (IM4JavaException e) {
            flag = false;
            e.printStackTrace();
        } finally {

        }
        return flag;
    }

    public  static boolean formatFile(String fileType){
        boolean flag = false;
        
        return flag;
    } 
    
    public static void main(String[] args) {
        String filePath = "E://pics//";
        String newFilePath = "E://pic//";
        String imagePath = StringUtils.EMPTY;
        String newPath = StringUtils.EMPTY;
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            imagePath = filePath + fileName;
            newPath = newFilePath + fileName;
            ImagesUtil.zoomImage(imagePath, newPath, 375, 4060,"80");
            
        }
        
    }
}
