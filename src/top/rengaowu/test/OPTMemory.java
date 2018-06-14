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
			// ����
			if (queue.get(i) == page) {
				return 1;
			}
		}
		// û������
		if (queue.size() == maxSize) {
			queue.remove(getMinPravity(pos));
		}
		queue.add(page);
		return 0;
	}

	public int getMinPravity(int pos) {
		int max = 0;
		ArrayList<Integer> pravity = new ArrayList<>();//�洢���ȼ�
		// �������ڴ���֡�����ȼ�
		// ���ȼ�Ϊ��һ�θ�Ԫ�س��ֵ�����
		// ��û�г���,����Ϊarr.length
		
		//�����ڴ�
		for (int i = 0; i < queue.size(); i++) {
			int page=queue.get(i);
			boolean flag = false;
			for (int j = pos+1; j < arr.length; j++) {
				if (page == arr[j] / Test.PAGE_ORDER_NUM) {
					pravity.add(j);//��һ�γ��ֵ��±�
					flag = true;
					break;
				}
			}
			//����´�û�г��֣���ΪINF
			if (flag == false)
				pravity.add(arr.length);
		}
		// ���ڴ���Ѱ��������ֵ�Ԫ��
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
