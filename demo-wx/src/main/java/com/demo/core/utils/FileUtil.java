/**
 * Copyright (c) 2012 RATANSOFT.All rights reserved.
 *
 * @filename FileUtil.java
 * @package com.ratan.util
 * @author dandyzheng
 * @date 2012-6-7
 */
package com.demo.core.utils;


import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author dandyzheng
 */
public class FileUtil {
    /**
     * File exist check
     *
     * @param sFileName File Name
     * @return boolean true - exist<br> false - not exist
     */
    public static boolean checkExist(String sFileName) {
        boolean result = false;
        try {
            File f = new File(sFileName);
            if (f.exists() && f.isFile()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void mkdir(String sFileName) {
        File f = new File(sFileName);
        if (!f.exists()) {
            f.mkdir();
        }
    }


    public static boolean mkdirs(String sFileName) {
        boolean flag = true;
        File f = new File(sFileName);
        if (!f.exists()) {
            f.mkdirs();
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * Get File Size
     *
     * @param sFileName File Name
     * @return long File's size(byte) when File not exist return -1
     */
    public static long getFileSize(String sFileName) {
        long lSize = -1;
        try {
            File f = new File(sFileName);
            if (f.exists()) {
                if (f.isFile() && f.canRead()) {
                    lSize = f.length();
                }
            }
        } catch (Exception e) {
            lSize = -1;
            e.printStackTrace();
        }
        return lSize;
    }

    /**
     * File Delete
     *
     * @param sFileName File Nmae
     * @return boolean true - Delete Success<br> false - Delete Fail
     */
    public static boolean deleteFromName(String sFileName) {

        boolean bReturn = true;
        try {
            File oFile = new File(sFileName);
            // exist
            if (oFile.exists()) {
                // Delete File
                boolean bResult = oFile.delete();
                // Delete Fail
                if (bResult == false) {
                    bReturn = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            bReturn = false;
        }

        return bReturn;
    }

    public static InputStream getInputStreamFromBytes(byte[] bytes) {
        InputStream is = null;
        if (bytes.length != 0) {
            is = new ByteArrayInputStream(bytes);
        }
        return is;
    }

    public static byte[] getBytesFromUrl(String url) {
        if (url == null || url == "") {
            return null;
        }
        byte[] buffer = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream bos = null;
        try {
            URL uri = new URL(url);
            bis = new BufferedInputStream(uri.openStream());
            byte[] bytes = new byte[1024];
            bos = new ByteArrayOutputStream(1024);
            int len;
            while ((len = bis.read(bytes)) > 0) {
                bos.write(bytes, 0, len);
            }
            bis.close();
            bos.flush();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                bis = null;
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                bos = null;
            }
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                bis = null;
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                bos = null;
            }
        }
        return buffer;
    }

    /**
     * jiangyukun on 2015-08-13
     */
    public static byte[] getBytesFromInputStream(InputStream in) throws IOException {
        ByteArrayOutputStream bos;
        bos = new ByteArrayOutputStream(1024);
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1) {
            bos.write(b, 0, n);
        }
        in.close();
        bos.close();
        return bos.toByteArray();
    }

    public static byte[] getBytesFromFile(File file) {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                fis = null;
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                bos = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void writeByte2File(byte[] bytes, File file) throws IOException {
        if (file != null) {
            FileOutputStream outf = new FileOutputStream(file);
            outf.write(bytes);
            outf.flush();
            outf.close();
        }
    }

    public static void writeByte2File(byte[] bytes, String filePath) {
        FileOutputStream outf = null;
        BufferedOutputStream bufferout = null;
        try {
            outf = new FileOutputStream(filePath);
            bufferout = new BufferedOutputStream(outf);
            bufferout.write(bytes);
            bufferout.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferout != null) {
                try {
                    bufferout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bufferout = null;
            }
            if (outf != null) {
                try {
                    outf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                outf = null;
            }
        }
    }

    /**
     * File Unzip
     *
     * @param sToPath  Unzip Directory path
     * @param sZipFile Unzip File Name
     */
    public static void releaseZip(String sToPath, String sZipFile)
            throws Exception {

        if (null == sToPath || ("").equals(sToPath.trim())) {
            File objZipFile = new File(sZipFile);
            sToPath = objZipFile.getParent();
        }
        ZipFile zfile = new ZipFile(sZipFile);
        Enumeration<?> zList = zfile.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {

            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                continue;
            }

            OutputStream os = new BufferedOutputStream(new FileOutputStream(
                    getRealFileName(sToPath, ze.getName())));
            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
            int readLen = 0;
            while ((readLen = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, readLen);
            }
            is.close();
            os.close();
        }
        zfile.close();
    }

    /**
     * getRealFileName
     *
     * @param baseDir     Root Directory
     * @param absFileName absolute Directory File Name
     * @return java.io.File Return file
     */
    private static File getRealFileName(String baseDir, String absFileName)
            throws Exception {

        File ret = null;

        List<String> dirs = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(absFileName,
                System.getProperty("file.separator"));
        while (st.hasMoreTokens()) {
            dirs.add(st.nextToken());
        }

        ret = new File(baseDir);
        if (dirs.size() > 1) {
            for (int i = 0; i < dirs.size() - 1; i++) {
                ret = new File(ret, (String) dirs.get(i));
            }
        }
        if (!ret.exists()) {
            ret.mkdirs();
        }
        ret = new File(ret, (String) dirs.get(dirs.size() - 1));
        return ret;
    }

    /**
     * copyFile
     *
     * @param srcFile    Source File
     * @param targetFile Target file
     */
    @SuppressWarnings("resource")
    static public void copyFile(String srcFile, String targetFile)
            throws IOException {

        FileInputStream reader = new FileInputStream(srcFile);
        FileOutputStream writer = new FileOutputStream(targetFile);

        byte[] buffer = new byte[4096];
        int len;

        try {
            reader = new FileInputStream(srcFile);
            writer = new FileOutputStream(targetFile);

            while ((len = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (writer != null)
                writer.close();
            if (reader != null)
                reader.close();
        }
    }

    /**
     * renameFile
     *
     * @param srcFile    Source File
     * @param targetFile Target file
     */
    static public void renameFile(String srcFile, String targetFile)
            throws IOException {
        try {
            copyFile(srcFile, targetFile);
            deleteFromName(srcFile);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void write(String tivoliMsg, String logFileName) {
        try {
            byte[] bMsg = tivoliMsg.getBytes();
            FileOutputStream fOut = new FileOutputStream(logFileName, true);
            fOut.write(bMsg);
            fOut.close();
        } catch (IOException e) {
            // throw the exception
        }
    }

    /**
     * This method is used to log the messages with timestamp,error code and the
     * method details
     *
     * @param logFile  String
     * @param batchId String
     * @param exceptionInfo        String
     */
    public static void writeLog(String logFile, String batchId,
                                String exceptionInfo) {

        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
                DateFormat.DEFAULT, Locale.JAPANESE);

        Object args[] = {df.format(new Date()), batchId, exceptionInfo};
        String fmtMsg = MessageFormat.format("{0} : {1} : {2}", args);

        try {
            File logfile = new File(logFile);
            if (!logfile.exists()) {
                logfile.createNewFile();
            }
            FileWriter fw = new FileWriter(logFile, true);
            fw.write(fmtMsg);
            fw.write(System.getProperty("line.separator"));

            fw.flush();
            fw.close();
        } catch (Exception e) {
        }
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 取得文件扩展名
     *
     * @param fileName 文件名
     * @return String
     */
    public static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        if (pos < 0) {
            return null;
        }
        return fileName.substring(pos);
    }

    /**
     * 根据网络url获取图片
     * @param url
     * @return
     */
    public static File newImageFromUrl(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() == org.apache.http.HttpStatus.SC_OK) {
            ContentType contentType = ContentType.getOrDefault(entity);
            if (contentType.getMimeType().contains("image")) {
                File tmp = File.createTempFile(UUID.randomUUID().toString(), ".png");
                FileUtils.copyInputStreamToFile(entity.getContent(), tmp);
                return tmp;
            } else {
                System.err.println();
                System.err.println(url);
                System.err.println(EntityUtils.toString(entity));
                throw new FileNotFoundException("该文件不是图片");
            }
        } else {
            throw new HttpResponseException(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase());
        }
    }
    /**
     * 下载文件到指定l
     *
     * @param urlString 被下载的文件地址
     * @param filename  本地文件名
     * @throws Exception 各种异常
     */
    public static File downLoadFileFromUrl(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();

        // 输入流
        InputStream is = null;
        OutputStream os = null;
        try {

            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            os = new FileOutputStream(filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (Exception e) {
            if (is != null) {
                is.close();
            }
            if (os != null) {
                os.close();
            }
            e.printStackTrace();
        }
        return new File(filename);
    }

    public static ResponseEntity<byte[]> mvcFileDownload(String downloadFileName, byte[] content) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);        
        headers.setContentDispositionFormData("attachment", new String(downloadFileName.getBytes("UTF-8"), "ISO8859-1"));
        return new ResponseEntity<>(content, headers, HttpStatus.CREATED);
    }
}
