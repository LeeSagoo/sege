package com.ii.nn.utils;

import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by BISHOP on 2017-10-07.
 */

public class Path {//dsds
    public static final String TAG = Path.class.getSimpleName();
    public static String[] getSDCardPath(){
        String[] externalArray;
        String second = System.getenv("SECONDARY_STORAGE");
        if(second!=null){
            externalArray = second.split(":");
        }else{
            externalArray = new String[0];
        }
        return externalArray;
    }

    public String[] getExternalStorageDirectories() {
        List<String> results = new ArrayList<>();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //File[] externalDirs = getExternalFilesDirs(null);
        }
        return null;
    }

    public static String[] getExternalMounts() {
        final HashSet<String> out = new HashSet<>();
        String reg = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
        String s = "";
        try {
            final Process process = new ProcessBuilder().command("mount")
                    .redirectErrorStream(true).start();
            process.waitFor();
            final InputStream is = process.getInputStream();
            final byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                s = s + new String(buffer);
            }
            is.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // parse output
        final String[] lines = s.split("\n");
        for (String line : lines) {
            if (!line.toLowerCase(Locale.US).contains("asec")) {
                if (line.matches(reg)) {
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        if (part.startsWith("/"))
                            if (!part.toLowerCase(Locale.US).contains("vold"))
                                out.add(part);
                    }
                }
            }
        }
        Log.e(TAG, out.iterator().next());
        FilenameFilter filter = (dir, name) -> name.endsWith(".txt");
        File file = new File(out.iterator().next());
        Iterator<String> iter = out.iterator();
        String[] res = new String[out.size()];
        int index = 0;
        while(iter.hasNext()){
            res[index] = iter.next();
            index++;
        }
        return res;
    }
}
