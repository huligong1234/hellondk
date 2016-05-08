package com.example.hellondk;

public class NDKUtils {
	public static native String  stringFromJNI();
	static {
	      System.loadLibrary("hellondk");
	}
}
