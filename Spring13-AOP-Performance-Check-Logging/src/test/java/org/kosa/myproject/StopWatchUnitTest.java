package org.kosa.myproject;

import org.springframework.util.StopWatch;

public class StopWatchUnitTest {
	public String findAllList() throws InterruptedException {
		StopWatch watch = new StopWatch();
		watch.start();
		///////////////////////////////////////////////////
		Thread.sleep(700);//0.7
		System.out.println("db에서 리스트를 조회");
		///////////////////////////////////////////////////
		watch.stop();
		System.out.println(watch.getTotalTimeMillis());
		return "all list";
	}
	public static void main(String[] args) {
		//StopWatch test 용
		System.out.println("StopWatch 테스트");
		
		try {
			new StopWatchUnitTest().findAllList();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
