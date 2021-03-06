package com.marving.code.java.lang;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mercop on 2017/6/19.
 * 每个线程有一个ThreadLocalMap，存储这个在线程中定义的ThreadLocal变量
 */
public class ThreadLocalDemo {

    @Test
    public void ThreadLocalTest(){
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "Id: " + getThreadId());
                increaseId(10);
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "Id: " + getThreadId());

            }
        });
        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "Id: " + getThreadId());
                increaseId(10);
                System.out.println("ThreadName: " + Thread.currentThread().getName() + "Id: " + getThreadId());
            }
        });
        th1.start();
        th2.start();
    }


    private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }

    };

    /**
     * 获取ThreadId值
     * @return
     */
    public static int getThreadId(){
        return threadId.get();
    }

    /**
     * 加上一个值获取新的id值
     * @param addId
     * @return
     */
    public static int increaseId(int addId){
        threadId.set(threadId.get() + addId);
        return threadId.get();
    }


    @Test
    public void test(){
        System.out.println();
        new Thread(()->{
            Message message = new Message();
            message.setDate("123");
            MyUtil.setMessage(message);
            new MessageConsumer().print();
        }).start();

        new Thread(()->{
            Message message = new Message();
            message.setDate("456");
            MyUtil.setMessage(message);
            new MessageConsumer().print();
        }).start();
    }
}

class Message{
    public String date;

    public void setDate(String date) {
        this.date = date;
    }
}

class MessageConsumer{
    public void print(){
        System.out.println(Thread.currentThread().getName() + " = " + MyUtil.getMessage().date);
    }
}

class MyUtil{

    private static ThreadLocal<Message> threadLocal = new ThreadLocal<>();

    public static Message getMessage(){
        return threadLocal.get();
    }

    public static void setMessage(Message message){
        threadLocal.set(message);
    }
}
