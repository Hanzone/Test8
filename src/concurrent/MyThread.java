package concurrent;

public class MyThread extends Thread {

    private MyLock lock;

    public MyThread(MyLock lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        System.out.print(ThreadContext.get());
        System.out.print(ThreadContext.getSss());
        try {
            sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(ThreadContext.get());
        System.out.print(ThreadContext.getSss());
    }

}
