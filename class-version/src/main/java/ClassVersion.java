import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2015/5/11 15:30.
 */
public class ClassVersion {
    public static void main(String[] args) throws Exception {
        fileFolderVersion("D:\\work\\svn\\ta\\4-middleware\\CODE\\kafka\\core\\build\\classes\\main");

        libFolderVersion("D:\\work\\svn\\ta\\4-middleware\\CODE\\storm-sample\\libs");
    }

    public static void libFolderVersion(String folder) throws IOException {
        File dir = new File(folder);
        Collection<File> jarFiles = FileUtils.listFiles(dir, new String[]{"jar"}, true);
        Map<Integer, Set<String>> versionFiles = new TreeMap<Integer, Set<String>>();
        for (File file : jarFiles) {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                if (zipEntry.getName().endsWith("class")) {
                    InputStream inputStream = zipFile.getInputStream(zipEntry);
                    int v = getV(inputStream);
                    Set<String> files = versionFiles.get(v);
                    if (files == null) {
                        files = new TreeSet<String>();
                        versionFiles.put(v, files);
                    }
                    files.add(file.getAbsolutePath());
                    break;
                }
            }
        }

        System.out.println(String.format("jar folder [%s] version:", folder));
        for (Map.Entry<Integer, Set<String>> entry : versionFiles.entrySet()) {
            System.out.println("version = " + entry.getKey());
            for (String file : entry.getValue()) {
                System.out.println(file);
            }
            System.out.println();
        }
    }

    public static void fileFolderVersion(String folder) throws IOException {
        File dir = new File(folder);
        Set<Integer> versions = new HashSet<Integer>();
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"class"}, true);
        for (File file : files) {
            int v = getV(file);
            versions.add(v);
        }
        System.out.println(String.format("file folder [%s] version:%s\n", folder, versions));
    }

    private static int getV(File file) throws IOException {
        FileInputStream stream = new FileInputStream(file);
        return getV(stream);
    }

    private static int getV(InputStream stream) throws IOException {
        byte[] ba = new byte[8];
        stream.read(ba);
        return (int) ba[7];
    }

}
