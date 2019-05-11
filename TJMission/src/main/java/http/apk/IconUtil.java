package http.apk;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.Enumeration;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class IconUtil {
    Logger logger= LoggerFactory.getLogger(IconUtil.class);
    public static void main(String args[]) throws IOException {
        String file = "G:\\新建文件夹 (2)" + File.separator + "球球跳一跳.apk";
        String targetFile = "res/drawable-mdpi-v4/app_icon.png";
        String saveRootDirectory = "G:\\新建文件夹 (2)\\1" + File.separator;
        String iconName = UUID.randomUUID().toString();
        new IconUtil().zipFileRead(targetFile, file, saveRootDirectory, iconName);
    }

    /**
     * @return void 返回类型
     * @throws
     * @Description: TODO(读取Zip信息 ， 获得zip中所有的目录文件信息)
     * @param设定文件
     */
    public void zipFileRead(String targetFile, String file, String saveRootDirectory, String iconName) throws IOException {
        // 获得zip信息
        ZipFile zipFile = new ZipFile(file);
        @SuppressWarnings("unchecked")
        Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile
                .entries();
        while (enu.hasMoreElements()) {
            ZipEntry zipElement = (ZipEntry) enu.nextElement();
            InputStream read = zipFile.getInputStream(zipElement);
            String fileName = zipElement.getName();
            if (targetFile.equals(fileName)) {
                System.out.println(fileName);
                if (fileName != null && fileName.indexOf(".") != -1) {// 是否为文件
                    unZipFile(zipElement, read, saveRootDirectory, iconName);
                }
            }

        }
    }

    /**
     * @return void 返回类型
     * @throws
     * @Description: TODO(找到文件并读取解压到指定目录)
     */
    public void unZipFile(ZipEntry ze, InputStream read,
                          String saveRootDirectory, String iconName) throws IOException {
        String fileName = ze.getName();
        // 判断文件是否符合要求或者是指定的某一类型
        // 指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）
        File getfile = new File(fileName);
        logger.info(getfile.getName());
        File file = new File(saveRootDirectory + iconName);
        if (!file.exists()) {
            File rootDirectoryFile = new File(file.getParent());
            // 创建目录
            if (!rootDirectoryFile.exists()) {
                boolean ifSuccess = rootDirectoryFile.mkdirs();
                if (ifSuccess) {
                    System.out.println("文件夹创建成功!");
                } else {
                    System.out.println("文件创建失败!");
                }
            }
            // 创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 写入文件
        BufferedOutputStream write = new BufferedOutputStream(
                new FileOutputStream(file));
        int cha = 0;
        while ((cha = read.read()) != -1) {
            write.write(cha);
        }
        // 要注意IO流关闭的先后顺序
        write.flush();
        write.close();
        read.close();
    }


    /**
     * @return void 返回类型
     * @throws
     * @Description: TODO(读取Zip信息 ， 获得zip中所有的目录文件信息)
     * @param设定文件
     */
    public void zipFileReadRSA(String file, String saveRootDirectory, String iconName) throws IOException {
        // 获得zip信息
        ZipFile zipFile = new ZipFile(file);
        @SuppressWarnings("unchecked")
        Enumeration<ZipEntry> enu = (Enumeration<ZipEntry>) zipFile
                .entries();
        while (enu.hasMoreElements()) {
            ZipEntry zipElement = (ZipEntry) enu.nextElement();
            InputStream read = zipFile.getInputStream(zipElement);
            String fileName = zipElement.getName();
            if (fileName.indexOf(".") != -1) {
                if (fileName.substring(fileName.lastIndexOf(".")+1).equals("RSA")) {
                    logger.info(fileName.substring(fileName.lastIndexOf(".")+1));
                    System.out.println(fileName);
                    if (fileName != null) {// 是否为文件
                        unZipFileRSA(zipElement, read, saveRootDirectory, iconName);
                    }
                }
            }
        }
        zipFile.close();
    }

    /**
     * @return void 返回类型
     * @throws
     * @Description: TODO(找到文件并读取解压到指定目录)
     */
    public void unZipFileRSA(ZipEntry ze, InputStream read,
                             String saveRootDirectory, String iconName) throws IOException {
        String fileName = ze.getName();
        // 判断文件是否符合要求或者是指定的某一类型
        // 指定要解压出来的文件格式（这些格式可抽取放置在集合或String数组通过参数传递进来，方法更通用）
        File getfile = new File(fileName);
        logger.info(getfile.getName());
        File file = new File(saveRootDirectory + iconName);
        if (!file.exists()) {
            File rootDirectoryFile = new File(file.getParent());
            // 创建目录
            if (!rootDirectoryFile.exists()) {
                boolean ifSuccess = rootDirectoryFile.mkdirs();
                if (ifSuccess) {
                    System.out.println("文件夹创建成功!");
                } else {
                    System.out.println("文件创建失败!");
                }
            }
            // 创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 写入文件
        BufferedOutputStream write = new BufferedOutputStream(
                new FileOutputStream(file));
        int cha = 0;
        while ((cha = read.read()) != -1) {
            write.write(cha);
        }
        // 要注意IO流关闭的先后顺序
        write.flush();
        write.close();
        read.close();
    }
}
