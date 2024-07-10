package ArrayType;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * 统计数组中的元素
 * */
public class Solution03 {
    public static void main(String[] args) {
        System.out.println(findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
    }


    //https://leetcode.cn/problems/set-mismatch/
    public static int[] findErrorNums(int[] nums) {
        /*
         * 先排序，排序后进行判断，如果存在丢失的数字，那么肯定存在相邻两个数差大于1，也肯定存在相邻两个数相同
         * */
        int n = nums.length;
        int[] res = new int[2];
        Arrays.sort(nums);
        int prev = 0;
        for (int i = 0; i < n; i++) {
            int current = nums[i];
            if (current == prev) {
                res[0] = current;
            } else if (current - prev > 1) {
                res[1] = prev + 1;
            }
            prev = current;
        }

        if (nums[n - 1] != n) {
            res[1] = n;
        }
        return res;
    }

    public static int[] findErrorNums01(int[] nums) {
        /*
         * 利用数据结构中的map，键值对应，遍历到的值加1
         * */
        int n = nums.length;
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int count = map.getOrDefault(i + 1, 0);
            if (count == 0) {
                res[1] = i + 1;
            }
            if (count == 2) {
                res[0] = i + 1;
            }
        }
        return res;
    }

    //https://leetcode.cn/problems/degree-of-an-array/
    public static int findShortestSubArray(int[] nums) {
        /*
         * 先求出数组中出现次数最多的数字，然后拿到该数字第一次出现和最后一次出现的索引
         * 不行，有可能会出现两个元素出现相同次数
         * */
        int n = nums.length, maxNumber = 0, maxCount = 0, first = 0, last = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            int tempCount = map.getOrDefault(nums[i], 0);
            if (tempCount > maxNumber) {
                maxNumber = nums[i];
                maxCount = tempCount;
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == maxNumber) {
                count++;
            }
            if (count == 1 && nums[i] == maxNumber) {
                first = i;
            }
            if (count == maxCount && nums[i] == maxNumber) {
                last = i;
            }
        }

        return last - first + 1;
    }

    public static int findShortestSubArray01(int[] nums) {
        /*
        * 改进，还是通过哈希表的方式，只不过是每个数映射一个长度为3的数组，数组中三个元素分别代表这个数出现的次数，第一次出现的下标，和最后一次出现的下标
        * */

        return 11;
    }
}
