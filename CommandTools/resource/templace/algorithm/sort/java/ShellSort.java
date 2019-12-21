package templace.algorithm.sort.java;

import java.util.Arrays;
import java.util.Map;

/**
 * <a href="https://mp.weixin.qq.com/s/b9-dkpAhWJYshuSs5cwnOw">希尔排序</a>
 * 逐步分组进行粗调，再进行直接插入排序的思想，就是希尔排序
 */
public class ShellSort {
    /**
     * 希尔排序:等比增量.
     *
     * @param array 待排序的数组.
     */
    public static void shellSort(int[] array) {
        // 希尔排序的增量
        int d = array.length;
        while (d > 1) {
            // 使用希尔增量的方式，即每次折半
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i = i + d) {
                    int temp = array[i];
                    int j;
                    for (j = i - d; j >= 0 && array[j] > temp; j = j - d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
        }
    }

    /**
     * 希尔排序Hibbard增量.
     * Hibbard的增量序列如下：
     * 1,3,7,15......,2^k-1
     * 所以希尔增量为 2^k-1<=array.length,
     * 所以 k= log_2(array.length+1)
     * 算法复杂度:O[n^(3/2)]
     *
     * @param array 待排序的数组
     */
    public static void shellSortHibbard(int[] array) {
        // 希尔排序的增量
        int d = array.length;
        // 计算最大的k
        //Math.log()是求log_e(x)
        //在java中求log_2(N),需要使用换底公式:log_2(N)=log_e(N)/log_e(2)
        int k = (int) Math.floor(Math.log((double) array.length + 1) / Math.log(2));
        System.out.println("maxK = " + k);
        while (d > 1) {
            // 使用Hibbard的增量序列:1,3,5,7,...,2^k-1
            d = (int) Math.pow(2, k--) - 1;
            //d = d / 2;
            System.out.println("d = " + d);
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i = i + d) {
                    int temp = array[i];
                    int j;
                    for (j = i - d; j >= 0 && array[j] > temp; j = j - d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                    System.out.println(Arrays.toString(array));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        System.out.println(Arrays.toString(array));
        shellSortHibbard(array);
        System.out.println(Arrays.toString(array));
    }
}
