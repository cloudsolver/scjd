package com.rohitsood.urlybird.test;

import java.rmi.dgc.VMID;

import suncertify.db.DBMain;
import suncertify.db.Data;
import suncertify.db.RecordNotFoundException;

/**
 * 
 * @author Rohit
 */
public class DataThread implements Runnable {

	 
	int rec;
	VMID vmid;
	DBMain db;
	
	DataThread(VMID vmid){
		
		this.vmid=vmid;
		db=new Data(vmid);
	}
	
	
	/**
	 * Run the test on its own thread
	 */
	public void run() {
		
		doTest();
		long duration=1L;
		
		if(rec==1){
		duration=4000;
		}
		
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		unlock();
		
	}
	
	public void doTest(){
		try {
			db.lock(rec);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unlock(){
		try {
			db.unlock(rec);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args){
		DataThread dt1=new DataThread(new VMID());
		dt1.rec=1;
		DataThread dt2=new DataThread(new VMID());
		dt2.rec=1;
		DataThread dt3=new DataThread(new VMID());
		dt3.rec=3;
		
		
		Thread t1=new Thread(dt1);
		Thread t2=new Thread(dt2);
		Thread t3=new Thread(dt3);
		t1.start();
		t2.start();
		t3.start();
		
		
		
		
	}

}
