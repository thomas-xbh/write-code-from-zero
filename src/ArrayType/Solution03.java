package ArrayType;

import java.lang.reflect.Array;
import java.util.*;

/*
 * 统计数组中的元素
 * */
public class Solution03 {
    public static void main(String[] args) {
        System.out.println(findDuplicates(new int[]{2, 1}));
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
        for (int current : nums) {
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
        Map<Integer, int[]> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i])[0]++;
                map.get(nums[i])[2] = i;
            } else {
                map.put(nums[i], new int[]{1, i, i});
            }
        }
        int maxNum = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] arr = entry.getValue();
            if (maxNum < arr[0]) {
                maxNum = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxNum == arr[0]) {
                if (minLen > arr[2] - arr[1] + 1) {
                    minLen = arr[2] - arr[1] + 1;
                }
            }
        }

        return minLen;
    }


    //https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        /*
         * 利用哈希表来筛选出消失的数字
         * */
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for (int num : nums) {
            map.put(num, 1);
        }
        for (int i = 1; i <= n; i++) {
            if (map.get(i) == null) {
                res.add(i);
            }
        }

        return res;

    }

    public static List<Integer> findDisappearedNumbers01(int[] nums) {
        /*
         * 利用数组来代替哈希表
         * */
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;

        }
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }

        return res;
    }


    //https://leetcode.cn/problems/find-all-duplicates-in-an-array/
    public static List<Integer> findDuplicates(int[] nums) {
        /*
        * 可以使用哈希表来筛选，由于题目现在，这里使用数组来代替哈希表
        * */
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = (nums[i] - 1) % n;
            nums[x] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 2 * n && n > 1) {
                res.add(i + 1);
            }
        }

        return res;
    }

}
