package ArrayType;

import java.util.Arrays;

/*
 * 数组的旋转
 * */
public class Solution02 {
    public static void main(String[] args) {
//        rotate02(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        System.out.println(maxRotateFunction(new int[]{4, 3, 2, 6}));
    }

    //https://leetcode.cn/problems/rotate-array/
    public static void rotate(int[] nums, int k) {
        /*
         * 超出时间限制
         * 思路：元素长度为一个周期，向右轮转多少次，意味着数组最后几位放到数组最前面
         * */
        int n = k % nums.length;
        for (int i = 0; i < n; i++) {
            int temp = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = temp;
        }
    }

    public static void rotate01(int[] nums, int k) {
        /*
         * 使用额外的数组
         * */
        int n = nums.length;
        int[] newArray = new int[n];
        for (int i = 0; i < n; i++) {
            newArray[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArray, 0, nums, 0, n);
    }

    public static void rotate02(int[] nums, int k) {
        /*
         * 数组翻转
         * */
        int n = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, n - 1);
        reverse(nums, n, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotate03(int[] nums, int k) {
        /*
         * 环状替换
         * todo 有点复杂暂时放一边了
         * */


    }

    //https://leetcode.cn/problems/rotate-function/
    public static int maxRotateFunction(int[] nums) {
        /*
         * 根据计算公式可以总结出规律
         * */
        int arraySum = Arrays.stream(nums).sum(), n = nums.length, basic = 0;

        for (int i = 0; i < n; i++) {
            basic += i * nums[i];
        }
        int res = basic;
        for (int i = n - 1; i > 0; i--) {

            basic += arraySum - n * nums[i];
            res = Math.max(res, basic);
        }
        return res;
    }
}
