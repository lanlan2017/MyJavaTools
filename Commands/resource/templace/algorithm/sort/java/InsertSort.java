package templace.algorithm.sort.java;

import java.util.Arrays;

/**
 * <a href="https://mp.weixin.qq.com/s/Iuk8_KFxm-017vbTgFXTVA">插入排序</a>:
 */
public class InsertSort {
    /**
     * 插入排序:
     * 最坏时间复杂度是O(n^2)
     * 由于插入排序没有引入额外的数据结构,所以空间复杂度是O(1)。
     *
     * @param array 待排序的数组
     */
    public static void insertSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            // 保存要插入的值
            int insertValue = array[i];
            int j = i - 1;
            // 从右向左和有序区比较,如果左边的元素更大
            for (; j >= 0 && insertValue < array[j]; j--) {
                // 往后移动更大的元素
                array[j + 1] = array[j];
            }
            // insertValue的值插入适当位置
            array[j + 1] = insertValue;
        }
    }

    public static void main(String[] args) {
        int[] array = {12, 1, 3, 46, 5, 0, -3, 12, 35, 16};
        insertSort(array);
        System.out.println(Arrays.toString(array));
    }
}