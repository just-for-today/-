package top.rengaowu.test;

public class FIFOMemory extends Memory{
	public FIFOMemory(int memorySize) {
		super(memorySize);
	}
	@Override
	public int insert(int index) {
		int page=index/Test.PAGE_ORDER_NUM;
		for (int j = 0; j < queue.size(); j++) {
			// ����
			if (queue.get(j) == page) {
				return 1;
			}
		}
		//û������
		if(queue.size()==maxSize) {
			queue.remove(0);
		}
		queue.add(page);
		return 0;
	}

		
}
