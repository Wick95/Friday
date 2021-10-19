import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>给定一个字符传 ，求这个字符串最大不包含重复字符的子串
 *
 * @author Matt Liu
 * @version 1.0 created by 2020.08.14
 */
public class Exercise1234 {

    public static String getMaxStr(String str) {
        int[] dict = new int[str.length()];
        List<Set<Character>> col = new ArrayList<>(str.length());

        //0 从字符串头节点遍历
        for (int i = 0; i < str.length(); i++) {
            char target = str.charAt(i);
            int length = 1;
            if (col.get(i) == null ){
                col.add(new HashSet<>());
                col.get(i).add(target);
            }
            //1 向前遍历 直到头节点 找到最大不重复子串
            int k = i;
            while (k != 0 && str.charAt(k - 1) != target && !col.get(i).contains(str.charAt(i - 1))) {
                length++;
                k--;
            }
            //2 在数组对应位置记录出最大长度
            dict[i] = length;
        }
        //3 遍历数组取出最大长度
        int max =0;
        for (int k = 0; k < dict.length; k++) {
            int ele = dict[k];
            max = ele > max ? k : max;
        }

        //4 截取字符串
        return str.substring(max-dict[max], max);

    }
}
