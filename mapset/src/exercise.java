import java.util.*;

public class exercise {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        map.put("yinzhiping", "xiaolongnv");
        map.put("2", "xiaolongnv");
        map.put("3", "xiaolongnv");

        map.forEach((k, v) -> System.out.println(k + ":" + v));







        // 通过键获得值
//        Set<String> keys = map.keySet();
//        // 迭代器
//        Iterator<String> iterator = keys.iterator();
////        while (iterator.hasNext()){
////            String key = iterator.next();
////            System.out.println(map.get(key));
////        }
////        // 增强for
////        for (String key : keys) {
////            System.out.println(map.get(key));
////        }
//        keys.forEach(s -> System.out.println(map.get(s)));

//        获取键值对
//        Set<Map.Entry<String, String>> entries = map.entrySet();
//        //增强for
//        for (Map.Entry<String, String> entry : entries) {
//            System.out.println(entry.getKey()+entry.getValue());
//        }
//
//        // foreach
//        entries.forEach(entry -> System.out.println(entry.getKey()+entry.getValue()));
//
//        // 迭代器
//        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> next = iterator.next();
//            System.out.println(next.getKey()+next.getValue());
//        }





    }
}
