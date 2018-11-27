package cn.bywind;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LocalLockDemo {
    static int n = 500;

    public static void secskill() {
        --n;
    }

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock(true);
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    boolean b = lock.tryLock(2, TimeUnit.SECONDS);
                    if (b){
                        String name = Thread.currentThread().getName();
                        secskill();
                        System.out.println("name:"+name+"--"+n);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }


            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                secskill();
                System.out.println("name:"+name+"--"+n);
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(runnable).start();
        }
    }
}
