package demo.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(3*1000);
        return "HelloWorld";
    }

    public static void main(String[] args) {
        CallableThread callable = new CallableThread();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        System.out.println("启动线程");
        // 启动线程
        thread.start();

        // 获取线程的返回值
        try {
            System.out.println("线程返回值："+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }
}
