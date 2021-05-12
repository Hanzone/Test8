package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    public static void writefile(List<String> lines) {
        String fileName="/Users/Ted/sssss.sql";
        try {
            BufferedWriter out=new BufferedWriter(new FileWriter(fileName));

            lines.forEach(line -> {
                try {
                    out.write(line);
                    out.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getStrFile() {

        List<String> strs = new ArrayList<>();
        try {
            FileReader fr = new FileReader("/Users/Ted/old.sql");
            BufferedReader bf = new BufferedReader(fr);

            String str;
            while ((str = bf.readLine()) != null) {
                strs.add(str);
            }
            fr.close();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strs;
    }

}
