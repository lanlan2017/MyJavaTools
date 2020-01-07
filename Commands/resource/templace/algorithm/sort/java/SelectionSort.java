package templace.algorithm.sort.java;

import java.util.Arrays;

public class SelectionSort {
    /**
     * <a href="https://mp.weixin.qq.com/s/gDR9QFtl5unJZFcOiX4NNg">选择排序算法</a>:
     * 每次找到最小的元素,将这个元素放到最前面即可.
     *
     * @param array 待排序的数组.
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 用来记录
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                // 如果找到更小的元素
                if (array[minIndex] > array[j]) {
                    // 那就记下该元素的下表
                    minIndex = j;
                }
                // minIndex = array[minIndex] < array[j] ? minIndex : j;
            }
            // 将最小的元素换到最前面
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8, 30, 50, 1, 33, 24, 5, -4, 7, 0};
        selectionSort(array);
        System.out.println(Arrays.toString(array));
    }
}