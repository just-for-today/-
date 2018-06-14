package top.rengaowu.test;

public class LRUMemory extends Memory {
	public LRUMemory(int memorySize) {
		super(memorySize);
	}
	@Override
	public int insert(int index) {
		int page=index/Test.PAGE_ORDER_NUM;
		for(int i=0;i<queue.size();i++) {
			//ÃüÖÐ
			if(queue.get(i)==page) {
				queue.remove(i);
				queue.add(page);
				return 1;
			}
		}
		//Ìæ»»
		if(queue.size()==maxSize) {
			queue.remove(0);
		}
		queue.add(page);
		return 0;
	}
}
