package com.gae.net;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wang1 on 2017/8/4.
 */
public class GetEmoticon {
    public void getPicFromURL(String stringUrl,String filename) {
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            File directory = new File("E:\\photo");
            //如果文件夹不存在则创建
            if (!directory.exists()) {
                directory.mkdir();
            }
            File imageFile = new File("E:\\photo\\"+filename+".gif");
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
            System.out.println(filename+"图片下载完成!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 获取所有表情
     */
    public void getAllAcfunEmoticon() {
        String version = "ac";
        String emoticonSerialNumber = "";
        String prefixURL = "http://cdn.aixifan.com/dotnet/20130418/umeditor/dialogs/emotion/images/";
        String suffixURL = ".gif?v=0.1";
        //获取所有表情
        for (int i=1;i<55;i++) {
            if (i<10) {
                emoticonSerialNumber = "0"+i;
            } else {
                emoticonSerialNumber = i+"";
            }
            getPicFromURL(prefixURL+version+"/"+emoticonSerialNumber+suffixURL,"ac1-"+i);
        }

        //获取所有新ac娘表情
        version = "ac2";
        for (int i=1;i<56;i++) {
            if (i<10) {
                emoticonSerialNumber = "0"+i;
            } else {
                emoticonSerialNumber = i+"";
            }
            getPicFromURL(prefixURL+version+"/"+emoticonSerialNumber+suffixURL,"ac2-"+i);
        }

        //获取所有彩ac娘表情
        version = "ac3";
        for (int i=1;i<17;i++) {
            if (i<10) {
                emoticonSerialNumber = "0"+i;
            } else {
                emoticonSerialNumber = i+"";
            }
            getPicFromURL(prefixURL+version+"/"+emoticonSerialNumber+suffixURL,"ac3-"+i);
        }
        
                //获取所有ac先锋表情
        version = "blizzard";
        for (int i=1;i<22;i++) {
            if (i<10) {
                emoticonSerialNumber = "0"+i;
            } else {
                emoticonSerialNumber = i+"";
            }
            getPicFromURL(prefixURL+version+"/"+emoticonSerialNumber+suffixURL,"ac-blizzard-"+i);
        }

        //获取所有ac匿名版表情
        version = "ais";
        for (int i=1;i<41;i++) {
            if (i<10) {
                emoticonSerialNumber = "0"+i;
            } else {
                emoticonSerialNumber = i+"";
            }
            getPicFromURL(prefixURL+version+"/"+emoticonSerialNumber+suffixURL,"ac-ais-"+i);
        }

    }
}
