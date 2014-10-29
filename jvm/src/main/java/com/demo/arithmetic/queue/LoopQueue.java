package com.demo.arithmetic.queue;


/**
 * Created by louis on 2014/10/29.
 */
/*
    利用数组构成的循环队列
    front:始终指向Q的首元素，即指向下次出队列元素的位置
    rear:始终指向Q末元素，即下次如队列元素的位置
    每次 front 或 rear 加一后，都要以数组的长度做取模运算，以
    保证其所指单元的合法性
 */
public class LoopQueue{
    public static final int CAPACITY=1000;
    protected int capacity;
    protected Object[] Q;
    protected int front=0;
    protected int rear=0;

    public LoopQueue() {
        this(CAPACITY+1);
    }
    public LoopQueue(int capacity){
        this.capacity=capacity+1;
        Q = new Object[this.capacity];
    }

    public boolean enqueue(Object e) {
        if((rear+1)%capacity==front)
            return false;
        Q[rear]=e;
        rear=(rear+1)%capacity;
        return true;
    }

    public Object dequeue() {
        if(front==rear)
            return null;
        Object obj=Q[front];
        front=(front+1)%capacity;
        return obj;
    }

    public Object peek() {
        if(isEmpty())
            return null;
        return (Object)Q[front];
    }

    public int size() {
        return (capacity-front+rear)%capacity;
    }

    public boolean isEmpty() {
        return front==rear;
    }

}
