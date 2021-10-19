package org.surge;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    private static final HashMap<String, Object> bizRouterMap = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            bizRouterMap.put("key" + i, new String("123"));
        }

        for (Map.Entry<String, Object> entry : bizRouterMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        for (int i = 0; i < 10; i++) {
            bizRouterMap.put("key" + i, new String("456"));
        }

        for (Map.Entry<String, Object> entry : bizRouterMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println("----" + list);

        adjust(list);

        System.out.println("----" + list);

        String blank = "";
        List<String> list2 = new ArrayList<>();

        list2.add("123");
        list2.add("456");
        list2.add("");
        System.out.println("-----"+list2.contains(blank));

    }

    public static void adjust(List<Integer> list) {
        list.clear();
    }
}
