import java.util.*;

public class exercise {
    public static void main(String[] args) {
        Random r = new Random();


        String[] arr = {"a", "b", "c", "d"};
        ArrayList<String> record = new ArrayList<String>();
        for (int i = 0; i < 80; i++) {
            int ran = r.nextInt(arr.length);
            record.add(arr[ran]);
        }
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        for (String s : record) {
            if (hm.containsKey(s)) {
                hm.put(s, hm.get(s) + 1);
            } else {
                hm.put(s, 1);
            }
        }

        hm.forEach((k, v) -> System.out.println("tourist attraction " + k + "points " + v));

        int count = 0;
        String maxKey = null;

        Set<Map.Entry<String, Integer>> entries = hm.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                maxKey = entry.getKey();
            }
        }
        System.out.println(maxKey + count);

    }


}
