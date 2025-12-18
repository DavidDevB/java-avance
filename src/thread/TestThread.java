package thread;

public class TestThread extends Thread {
    public TestThread(String name) {
        super(name);
    }

    public void run(){
        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print((i + 1) + new String(new char[i + 1]).replace("\0", "-"));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestThread t1 = new TestThread("Thread-1");

         t1.start();
        

        
    }
}
