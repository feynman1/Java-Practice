package com.hspjava.Utils;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class StreamUtils {
    public static byte[] streamToBytes(InputStream inputStream) throws IOException {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = inputStream.read(bytes))!=-1){
            byteOutputStream.write(bytes,0,len);
        }
        return byteOutputStream.getBytes();
    }
    public static String streamToString(InputStream inputStream)  throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while((line = bufferedReader.readLine())!=null){
            stringBuilder.append(line+"\r\n");
        }
        return stringBuilder.toString();
    }
}
