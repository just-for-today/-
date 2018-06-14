package top.rengaowu.test;

public class FIFOMemory extends Memory{
	public FIFOMemory(int memorySize) {
		super(memorySize);
	}
	@Override
	public int insert(int index) {
		int page=index/Test.PAGE_ORDER_NUM;
		for (int j = 0; j < queue.size(); j++) {
			// 命中
			if (queue.get(j) == page) {
				return 1;
			}
		}
		//没有命中
		if(queue.size()==maxSize) {
			queue.remove(0);
		}
		queue.add(page);
		return 0;
	}

		
}
