package codeInterView.assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FindApiVersionDemo {

    static class SortObject implements Comparable<SortObject> {

        private String app;
        private String api;
        private String version;

        public SortObject(String app, String api, String version) {
            super();
            this.app = app;
            this.api = api;
            this.version = version;
        }

        @Override
        public int compareTo(SortObject o) {
            int c = o.api.compareTo(this.api);
            if (c == 0)
                c = this.version.compareTo(o.version);
            if (c == 0)
                c = this.app.compareTo(o.app);
            return c;
        }
    }

    public void getApiVersion() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
            String line;
            List<SortObject> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                list.add(new SortObject(tokens[0], tokens[1], tokens[2]));
            }
            br.close();

            Collections.sort(list);
            Map<String, SortObject> appVersionMap = new HashMap<>();
            String prevApi = null;
            for (SortObject obj : list) {
                // System.out.println(obj.app + ":" + obj.api + ":" + obj.version);

                if (appVersionMap.get(obj.api) == null)
                    appVersionMap.put(obj.api, obj);

            }
            FileWriter writer = new FileWriter("output.txt");
            BufferedWriter buffer = new BufferedWriter(writer);

            for (Entry<String, SortObject> entry : appVersionMap.entrySet()) {
                SortObject obj = entry.getValue();

                buffer.write(entry.getKey() + "," + obj.version + "\n");
                System.out.println("  API APP name:: " + obj.app + " API:: " + entry.getKey() + "  API version:: " + obj.version);
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("IOException ::" + e);
        }
    }

    public static void main(String[] args) {
        FindApiVersionDemo demo = new FindApiVersionDemo();
        demo.getApiVersion();
    }
}