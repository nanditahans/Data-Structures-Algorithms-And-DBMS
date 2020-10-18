package Problems;

public class DeadLock {
    public static Object LockA ="LockA";
    public static Object LockB = "LockB";
    public static Object LockC = "LockC";

    public static void main(String args[]) {
        A a = new A();
        B b= new B();
        C c= new C();
        a.start();
        b.start();
        c.start();
    }

    static class A extends Thread {
        public void run() {
            synchronized (LockA) {
                System.out.println("Thread A: locked A");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread A: Waiting for lock B");

                synchronized (LockB) {
                    System.out.println("Thread A: Holding lock A & B");
                }
            }
        }
    }
    static class B extends Thread {
        public void run() {
            synchronized (LockB) {
                System.out.println("Thread B: locked B");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread B: Waiting for lock C");

                synchronized (LockC) {
                    System.out.println("Thread B: Holding lock B & C");
                }
            }
        }
    }
    static class C extends Thread {
        public void run() {
            synchronized (LockC) {
                System.out.println("Thread C: locked C");

                try { Thread.sleep(10); }
                catch (InterruptedException e) {}
                System.out.println("Thread C: Waiting for lock A");

                synchronized (LockA) {
                    System.out.println("Thread C: Holding lock A & C");
                }
            }
        }
    }
}
