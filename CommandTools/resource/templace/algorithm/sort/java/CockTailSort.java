package templace.algorithm.sort.java;

import java.util.Arrays;

/**
 * <a href="https://mp.weixin.qq.com/s/CoVZrvis6BnxBQgQrdc5kA">鸡尾酒排序</a>:
 * 排序过程就像钟摆一样，第一轮从左到右，第二轮从右到左，第三轮再从左到右......
 * 鸡尾酒排序的优点:
 * 能够在特定条件下,减少排序的回合数;
 * 缺点:
 * 代码量几乎扩大了一倍。
 * 适用情况:
 * 在[大部分元素已经有序]的情况下。
 */
public class CockTailSort {
    /**
     * 鸡尾酒排序的原始实现。代码外层的大循环控制着所有排序回合，大循环内包含两个小循环，第一个循环从左向右比较并交换元素，第二个循环从右向左比较并交换元素
     *
     * @param array
     */
    private static void cockTailSort(int array[]) {
        int tmp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            // 有序标记，每一轮的初始是true
            boolean isSorted = true;
            // 奇数轮，从左向右比较和交换
            for (int j = i; j < array.length - i - 1; j++) {
                // 如果前面的数大
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    // 将小的数换到前面
                    array[j] = array[j + 1];
                    // 将大的数换到后面
                    array[j + 1] = tmp;
                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
            // 偶数轮之前，重新标记为true
            isSorted = true;
            // 偶数轮，从右向左比较和交换
            for (int j = array.length - i - 1; j > i; j--) {
                // 如果前面的数大
                if (array[j] < array[j - 1]) {
                    tmp = array[j];
                    // 将大的数换到后面
                    array[j] = array[j - 1];
                    // 将小的数换到前面
                    array[j - 1] = tmp;
                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 鸡尾酒排序优化版本:
     * 左右两个边界值，rightSortBorder 代表右边界，leftSortBorder代表左边界。 在比较和交换元素时，奇数轮从 leftSortBorder 遍历到 rightSortBorder 位置，偶数轮从 rightSortBorder 遍历到 leftSortBorder 位置
     *
     * @param array 待排序的数组.
     */
    private static void sort(int array[]) {
        int tmp = 0;
        // 记录右侧最后一次交换的位置
        int lastRightExchangeIndex = 0;
        // 记录左侧最后一次交换的位置
        int lastLeftExchangeIndex = 0;
        // 无序数列的右边界，每次比较只需要比到这里为止
        int rightSortBorder = array.length - 1;
        // 无序数列的左边界，每次比较只需要比到这里为止
        int leftSortBorder = 0;
        for (int i = 0; i < array.length / 2; i++) {
            // 有序标记，每一轮的初始是true
            boolean isSorted = true;
            // 奇数轮，从左向右比较和交换
            for (int j = leftSortBorder; j < rightSortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    lastRightExchangeIndex = j;
                }
            }
            rightSortBorder = lastRightExchangeIndex;
            if (isSorted) {
                break;
            }
            // 偶数轮之前，重新标记为true
            isSorted = true;
            // 偶数轮，从右向左比较和交换
            for (int j = rightSortBorder; j > leftSortBorder; j--) {
                if (array[j] < array[j - 1]) {
                    tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    // 有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    lastLeftExchangeIndex = j;
                }
            }
            leftSortBorder = lastLeftExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        cockTailSort(array);
        System.out.println(Arrays.toString(array));
    }
}
