package top.rengaowu.test;

import java.util.ArrayList;

public class OPTMemory extends Memory{
	private int[] arr;

	public OPTMemory(int[] arr, int memorySize) {
		super(memorySize);
		this.arr = arr;
	}

	public int insert(int index,int pos) {
		int page = index / Test.PAGE_ORDER_NUM;
		for (int i = 0; i < queue.size(); i++) {
			// 命中
			if (queue.get(i) == page) {
				return 1;
			}
		}
		// 没有命中
		if (queue.size() == maxSize) {
			queue.remove(getMinPravity(pos));
		}
		queue.add(page);
		return 0;
	}

	public int getMinPravity(int pos) {
		int max = 0;
		ArrayList<Integer> pravity = new ArrayList<>();//存储优先级
		// 逐个检查内存中帧的优先级
		// 优先级为下一次该元素出现的索引
		// 若没有出现,设置为arr.length
		
		//遍历内存
		for (int i = 0; i < queue.size(); i++) {
			int page=queue.get(i);
			boolean flag = false;
			for (int j = pos+1; j < arr.length; j++) {
				if (page == arr[j] / Test.PAGE_ORDER_NUM) {
					pravity.add(j);//下一次出现的下表
					flag = true;
					break;
				}
			}
			//如果下次没有出现，则为INF
			if (flag == false)
				pravity.add(arr.length);
		}
		// 再内存中寻找最晚出现的元素
		for (int i = 1; i < pravity.size(); i++) {
			if (pravity.get(max) < pravity.get(i)) {
				max = i;
			}
		}
		return max;
	}

	@Override
	public int insert(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

}
