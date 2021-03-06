package com.huivip.steel.util;

/*import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;*/
/*import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;*/

/**
 * Created by sunlaihui on 6/9/15.
 */
public class Thumbnail {
   /* public static void thumbnail_create(String url, String name) {
        Tosmallerpic(url, new File(url + name), "_middle", name, 188, 165,
                (float) 0.7);
        Tosmallerpic(url, new File(url + name), "_smaller", name, 45, 45,
                (float) 0.7);
        Tosmallerpic(url, new File(url + name), "_small", name, 116, 100,
                (float) 0.7);
    }*/
    public static void thumbnail_create(String filePath,String name){
        if(!filePath.endsWith("/")){
            filePath+="/";
        }
        String sourceFile=filePath+name;
        String thumbnailFile=filePath+name.substring(0,name.lastIndexOf("."))+"_smaller"+name.substring(name.lastIndexOf("."));
        ImageUtil.compressImage(sourceFile,thumbnailFile,90,120);
    }
    /**
     * * * @param f 图片所在的文件夹路径 * @param filelist 图片路径 *
     *
     * @param ext
     *            扩展名
     * @param n
     *            图片名
     * @param w
     *            目标宽 *
     * @param h
     *            目标高 *
     * @param per
     *            百分比
     */
    /*private static void Tosmallerpic(String f, File filelist, String ext,
                                     String n, int w, int h, float per) {
        Image src;
        try {
            src = javax.imageio.ImageIO.read(filelist);
            // 构造Image对象
            String img_midname = f + n.substring(0, n.indexOf(".")) + ext
                    + n.substring(n.indexOf("."));
            int old_w = src.getWidth(null); // 得到源图宽
            int old_h = src.getHeight(null);
            int new_w = 0;
            int new_h = 0; // 得到源图长
            double w2 = (old_w * 1.00) / (w * 1.00);
            double h2 = (old_h * 1.00) / (h * 1.00);
            // 图片跟据长宽留白，成一个正方形图。
            BufferedImage oldpic;
            if (old_w > old_h) {
                oldpic = new BufferedImage(old_w, old_w,
                        BufferedImage.TYPE_INT_RGB);
            } else {
                if (old_w < old_h) {
                    oldpic = new BufferedImage(old_h, old_h,
                            BufferedImage.TYPE_INT_RGB);
                } else {
                    oldpic = new BufferedImage(old_w, old_h,
                            BufferedImage.TYPE_INT_RGB);
                }
            }
            Graphics2D g = oldpic.createGraphics();
            g.setColor(Color.white);
            if (old_w > old_h) {
                g.fillRect(0, 0, old_w, old_w);
                g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h,
                        Color.white, null);
            } else {
                if (old_w < old_h) {
                    g.fillRect(0, 0, old_h, old_h);
                    g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h,
                            Color.white, null);
                } else {

                    // g.fillRect(0,0,old_h,old_h);
                    g.drawImage(src.getScaledInstance(old_w, old_h,
                            Image.SCALE_SMOOTH), 0, 0, null);
                }
            }
            g.dispose();
            src = oldpic;
            // 图片调整为方形结束
            if (old_w > w)
                new_w = (int) Math.round(old_w / w2);
            else
                new_w = old_w;
            if (old_h > h)
                new_h = (int) Math.round(old_h / h2);// 计算新图长宽
            else
                new_h = old_h;
            BufferedImage tag = new BufferedImage(new_w, new_h,
                    BufferedImage.TYPE_INT_RGB);
            // tag.getGraphics().drawImage(src,0,0,new_w,new_h,null);
            // 绘制缩小后的图
            tag.getGraphics().drawImage(
                    src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
                    0, null);
            FileOutputStream newimage = new FileOutputStream(img_midname); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
			*//* 压缩质量 *//*
            jep.setQuality(per, true);
            encoder.encode(tag, jep);
            // encoder.encode(tag);
            // 近JPEG编码
            newimage.close();
        } catch (IOException ex) {
            Logger.getLogger(Thumbnail.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    public static String parsingImage(String content,String contextPath){
        String imgRegex = "<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        String imgeURL="";
        Pattern r = Pattern.compile(imgRegex);
        Matcher m = r.matcher(content);
        String attacheDir = SteelConfig.getConfigureAttachDir();
        if (null == attacheDir || attacheDir.length() == 0) {
            attacheDir = contextPath;
        }
        if (!attacheDir.endsWith("/")) {
            attacheDir += "/";
        }
        while (m.find()) {
            String imgUrl = m.group(1);
            if (imgUrl.indexOf("attached") < 0) {
                continue;
            }
            String fileUrl = attacheDir + imgUrl;
            //to do  check if need create thumbnail
            Thumbnail.thumbnail_create(fileUrl.substring(0, fileUrl.lastIndexOf("/") + 1),
                    fileUrl.substring(fileUrl.lastIndexOf("/") + 1));
            String thumbnailURL = imgUrl.substring(0, imgUrl.lastIndexOf("/") + 1) + imgUrl.substring(imgUrl.lastIndexOf("/") + 1,
                    imgUrl.lastIndexOf(".")) + "_smaller" + imgUrl.substring(imgUrl.lastIndexOf("."));
            imgeURL=thumbnailURL;
            break;
        }
        return imgeURL;
    }
    public static void main(String[] args){
        //Thumbnail thumbnail=new Thumbnail();
        //thumbnail.thumbnail_create("/Users/sunlaihui/Documents/workspace/javaspace/steel/src/main/webapp/attached/image/20150609/","20150609_231.jpg");
        String regexString="<img.*?(?: |\\\\t|\\\\r|\\\\n)?src=['\\\"]?(.+?)['\\\"]?(?:(?: |\\\\t|\\\\r|\\\\n)+.*?)?>";
        String imgRegex="<img.*?(?: |\\t|\\r|\\n)?src=['\"]?(.+?)['\"]?(?:(?: |\\t|\\r|\\n)+.*?)?>";
        Pattern r = Pattern.compile(imgRegex);
        String str="<img src=\"/attached/image/20150609/20150609_231.jpg\" alt=\"\" /><img src=\"/attached/image/20150609/20150609_231_middle.jpg\" alt=\"\" /><img src=\"/attached/image/20150609/20150609_231_smaller.jpg\" alt=\"\" />";
        Matcher m=r.matcher(str);
        if(m.find()){
            String fileUrl=m.group(1);
           *//* String filePath=fileUrl.substring(0,fileUrl.lastIndexOf("/")+1);
            String fileName=fileUrl.substring(fileUrl.lastIndexOf("/")+1);
            System.out.println(filePath);
            System.out.println(fileName);*//*
            String imgUrl=fileUrl;
            String thumbnailURL=imgUrl.substring(0,imgUrl.lastIndexOf("/")+1)+imgUrl.substring(imgUrl.lastIndexOf("/")+1,
                    imgUrl.lastIndexOf("."))+"_small"+imgUrl.substring(imgUrl.lastIndexOf("."));
            System.out.println(thumbnailURL);
        }

    }*/
}
