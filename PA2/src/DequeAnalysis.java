import java.util.Random;

public class DequeAnalysis {
	public static void main(String[] args) {
		int maxOperations = 10000;
		int numTrials = 100;
		System.out.println("Deque1 (Doubling Capcity) vs. Deque2 (Fixed Capacity)");
		System.out.println("Experiment 1: Varying the number of enqueue operations");
		System.out.println("Operations, Deque1 (Doubling Capacity), Deque2 (Fixed Capacity)");
		for (int operations = 1; operations <= maxOperations; operations += 100) {
			long totalTime1 = 0;
			long totalTime2 = 0;
			for (int trial = 0; trial < numTrials; trial++) {
				Deque1<Integer> deque1 = new Deque1<>(1);
				Deque2<Integer> deque2 = new Deque2<>(1);
				Random random = new Random();
				long startTime1 = System.nanoTime();
				for (int i = 0; i < operations; i++) {
					deque1.enqueue(random.nextInt());
				}
				long endTime1 = System.nanoTime();
				totalTime1 += (endTime1 - startTime1);
				long startTime2 = System.nanoTime();
				for (int i = 0; i < operations; i++) {
					deque2.enqueue(random.nextInt());
				}
				long endTime2 = System.nanoTime();
				totalTime2 += (endTime2 - startTime2);
			}
			long averageTime1 = totalTime1 / numTrials;
			long averageTime2 = totalTime2 / numTrials;
			System.out.println(operations + "," + averageTime1 + "," + averageTime2);
		}
	}

}
