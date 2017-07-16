package com.marving.code.java.jvm;
/**
 * jvm para: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 
 * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 * Created by mercop on 2017/7/15.
 */
public class OldGene {
	
	private static final int _1MB = 1024*1024;

	public static void main(String[] args){
		byte[] allocation1,allocation2,allocation3;
		allocation1 = new byte[_1MB/8];
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
		System.gc();
	}
}
