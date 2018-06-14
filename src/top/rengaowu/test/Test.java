package top.rengaowu.test;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

public class Test extends JFrame {
	/**
	 * 内存调度算法实现
	 */
	private static final long serialVersionUID = 1L;
	public static final int VIRTUAL_SIZE = 32;// 虚拟内存
	public static final int PAGE_SIZE = 1;// 页大小
	public static final int PAGE_ORDER_NUM = 10;
	public static final int ORDER_NUM = 320;

	public static double[] plotFIFO;
	public static double[] plotLRU;
	public static double[] plotOPT;

	public static void main(String[] args) {
		// 设页面大小为 1K，用户虚存容量 32K，
		// 物理内存大小从 4K 到 32K 分别进行算法测试
		int MemorySize = 4;
		int arr[];
		arr = randArray();

		// 绘图
		plotFIFO = new double[1 + VIRTUAL_SIZE - MemorySize];
		plotLRU = new double[1 + VIRTUAL_SIZE - MemorySize];
		plotOPT = new double[1 + VIRTUAL_SIZE - MemorySize];

		for (int i = 0; MemorySize <= VIRTUAL_SIZE; MemorySize++) {
			plotFIFO[i] = FIFO(arr, MemorySize); // 计算并输出 FIFO 算法命中率
			plotLRU[i] = LRU(arr, MemorySize);
			plotOPT[i] = OPT(arr, MemorySize);
			System.out.println();
			i++;
		}

		Test test = new Test();
		Test.VisualAccumulator visualAccumulator = test.new VisualAccumulator();// 内部镶嵌类
		visualAccumulator.addDateValue(plotFIFO, StdDraw.RED);
		visualAccumulator.addDateValue(plotLRU, StdDraw.BLUE);
		visualAccumulator.addDateValue(plotOPT, StdDraw.GREEN);

	}

	public class VisualAccumulator {

		public VisualAccumulator() {
			StdDraw.setXscale(-5, VIRTUAL_SIZE + 5);
			StdDraw.setYscale(-0.1, 1.1);
			StdDraw.setPenRadius(0.01);
			plot();
		}

		public void plot() {
			StdDraw.setPenRadius(0.003);
			StdDraw.line(0, 0, VIRTUAL_SIZE, 0);// x
			StdDraw.line(0, 0, 0, 1);// y
			for (int i = 0; i < VIRTUAL_SIZE; i += 2) {
				StdDraw.text(i, 0, i + "");
			}
			for (int i = 1; i < 10; i++) {
				StdDraw.text(0, i / 10.0, i / 10.0 + "");
			}
			StdDraw.text(VIRTUAL_SIZE / 2, -0.05, "命中率变化曲线");
			StdDraw.text(0, 1.05, "命中率");
		}

		public void addDateValue(double[] value, Color color) {
			StdDraw.setPenColor(color);
			for (int i = 0; i < value.length; i++) {
				StdDraw.point(i, value[i]);
				if (i != 0) {
					StdDraw.line(i - 1, value[i - 1], i, value[i]);
				}
			}

		}
	}

	public static double OPT(int[] arr, int MemorySize) {
		int hitSum = 0;// 命中次数
		OPTMemory memory = new OPTMemory(arr, MemorySize);
		for (int i = 0; i < arr.length; i++) {
			hitSum += memory.insert(arr[i], i);
		}
		double rate = 1.0 * hitSum / arr.length;
		System.out.println("OPT 命中率为：" + rate);
		return rate;
	}

	public static double LRU(int[] arr, int MemorySize) {
		int hitSum = 0;// 命中次数
		LRUMemory memory = new LRUMemory(MemorySize);
		for (int i = 0; i < arr.length; i++) {
			hitSum += memory.insert(arr[i]);
		}
		double rate = 1.0 * hitSum / arr.length;
		System.out.println("LRU 命中率为：" + rate);
		return rate;
	}

	public static double FIFO(int[] arr, int MemorySize) {
		int hitSum = 0;// 命中次数
		FIFOMemory memory = new FIFOMemory(MemorySize);
		for (int i = 0; i < arr.length; i++) {
			hitSum += memory.insert(arr[i]);
		}
		double rate = 1.0 * hitSum / arr.length;
		System.out.println("FIFO 命中率为：" + rate);
		return rate;
	}

	public static int[] randArray() {
		int arr[] = new int[ORDER_NUM];
		Random random = new Random();
		arr[0] = random.nextInt(ORDER_NUM-1);

		for (int i = 0; i < arr.length - 1; i++) {
			float p = random.nextFloat();
			// 越界处理
			while ((arr[i] > ORDER_NUM-2 && p < 0.5) || (arr[i] > 317 && p >= 0.75) || (arr[i] < 2 && p >= 0.5 && p < 0.75)) {
				p = random.nextFloat();
			}

			// 生成随机数
			if (p < 0.5)
				arr[i + 1] = arr[i] + 1;
			else if (p < 0.75)
				arr[i + 1] = random.nextInt(arr[i] - 1);
			else
				arr[i + 1] = arr[i] + 2 + random.nextInt(ORDER_NUM-2 - arr[i]);
		}
		return arr;
	}
}
