package com.practice.thread;

class NewThread1 extends Thread {
	Thread t1, t2;

	NewThread1() {
		t1 = new Thread(this, "Thread_1");
		t2 = new Thread(this, "Thread_2");
		t1.start();
		t2.start();
	}

	public void run() {

		t2.setPriority(Thread.MAX_PRIORITY);
		System.out.print("Inside run " + t1.equals(t2));
	}
}

public class NewThread {
	public static void main(String args[]) {
		new NewThread1();
		
		 overload obj = new overload();   
		 Integer  a = 2;
		 double b = 3;
         
         System.out.println("--------------");
         obj.add(a, b);
         //obj.add(b, b);
         System.out.println(obj.x + "      " + obj.y);    
         
         
	}
}

class overload {
	int x;
	double y;

	void add(int a, int b) {
		System.out.println("Inside Int");     
		x = a + b;
	}
	
	void add(Integer a, int b) {
		System.out.println("Inside Integer");     
		x = a + b;
	}

	void add(double c, double d) {
		System.out.println("Inside double");     
		y = c + d;
	}

	overload() {
		this.x = 0;
		this.y = 0;
	}
}
