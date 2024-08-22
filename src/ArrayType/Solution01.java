package ArrayType;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
 * 数组篇之数组的改变、移动
 * */
public class Solution01 {

    public static void main(String[] args) {
//        System.out.println(checkPossibility(new int[]{5, 7, 1, 8}));
        moveZeroes(new int[]{0, 0, 1});
    }

    //https://leetcode.cn/problems/minimum-moves-to-equal-array-elements/
    public static int minMoves(int[] nums) {
        /*
         * 方法一
         * 暴力破解，时间复杂度太高了
         * 思路：完全跟着题目描述的思路来写的
         * */
        boolean flag = true;
        //判断初始化数组数据每个元素是否都相等
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[0]) {
                flag = false;
            }
        }
        int count = 0;
        while (!flag) {
            int max = 0, maxIndex = 0;
            count++;
            flag = true;
            //求出最大值的索引
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > max) {
                    max = nums[i];
                    maxIndex = i;
                }
            }
            //排除最大值，其他元素都+1
            for (int i = 0; i < nums.length; i++) {
                if (i != maxIndex) {
                    nums[i]++;

                }
            }
            //遍历判断数组元素是否都相等
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != nums[0]) {
                    flag = false;
                }
            }
        }


        return count;
    }

    public static int minMoves01(int[] nums) {
        /*
         * 方法二
         * 思路：根据题目描述可知，该题和数组元素的实际大小没有关系，只跟元素的相对大小有关；可知给数组中n-1个元素加1相当于
         * 给数组中一个元素减1，这样只需遍历数组，求出最小值和每个元素的差的和即可
         * */
        //求出最小值
        int min = Arrays.stream(nums).min().getAsInt(), res = 0;
        //遍历数组，求出每个元素于最小值差的和
        for (int num : nums) {
            res += num - min;
        }
        return res;
    }

    //https://leetcode.cn/problems/non-decreasing-array/
    public static boolean checkPossibility(int[] nums) {
        /*
         * 遍历判断上一个元素大于下一个元素存在多少种,最多存在一种，在存在一种的情况下，如果通过改变一个元素让数组保持非递减顺序，
         * 只需要两种条件判断
         * 1.x=y
         * 2.y=x赋值即可
         *
         * */
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < nums.length - 1; i++) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i + 1] = x;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i + 1] = y;
                nums[i] = y;
                return isSorted(nums);
            }
        }
        return true;
    }

    public static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    //https://leetcode.cn/problems/move-zeroes/
    public static void moveZeroes(int[] nums) {
        /*
        * 思路：双指针法，左指针和右指针都从零依次遍历，右指针当前不为零就与做指针元素互换位置，然后左右指针都加1，保持左指针的左边
        * 都不为零，左指针和右指针之间都为零
        * */
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

}
