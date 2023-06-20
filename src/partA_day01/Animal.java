package partA_day01;

public /*문제1) 클래스의 타입 선언해주세요*/abstract class Animal {
	
		
	  protected String hello;   
	  
	  
	   /*문제 2) 추상 메소드 생성해주세요*/ void sound();
	  
	   public Animal(String hello) {
	      this.hello = hello;
	   }
	}
}
