package tools.random;

import java.util.Random;

public class Randoms {
    private static Random random = new Random();

    /**
     * 获取[min,max]区间的随机整数
     *
     * @param min 最小的随机整数值
     * @param max 最大的随机整数值
     * @return [min, max]区间的随机整数值
     */
    public static int getRandomInt(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }
}
