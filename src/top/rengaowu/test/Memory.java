package top.rengaowu.test;

import java.util.ArrayList;

public abstract class Memory {
	protected ArrayList<Integer> queue;
	protected int maxSize;

	public Memory(int memorySize) {
		maxSize = memorySize/Test.PAGE_SIZE ;
		queue = new ArrayList<>();
	}
	public abstract int insert(int index);
}
