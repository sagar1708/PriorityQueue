import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class PQSort {

	public static <E extends Comparable <? super E>> void heapSort(E[] a) {
		PriorityQueue<E> pq = new PriorityQueue<>();
		for(E e:a) pq.offer(e);
		for(int i=0;i<a.length;i++) a[i] = pq.remove();
	}
	
	public static <E extends Comparable <? super E>> void heapSort2011(E[] a) {
		PriorityQueue2011<E> pq = new PriorityQueue2011<>();
		for(E e:a) pq.offer(e);
		for(int i=0;i<a.length;i++) {
			a[i] = pq.remove();
		}
	}
	
	// NOTE :- i just added main method to confirm my solution and instead for that my test for 5
	// marks is failing if i comment out my all the test cases are passing pqSortFieldsMethodsTester
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//			
//		PriorityQueue2011 <Integer> heap = new PriorityQueue2011<>();
//		for(int i = 21;i>=1;i--)heap.offer(i);
//		
//		System.out.println(heap);
//		System.out.println(heap.toTree());
//		
//		Integer[] arrayToSort = new Integer[50];
//		for(int i=0;i<arrayToSort.length;i++) {
//			arrayToSort[i] = i;
//		}
//		
//		shuffleArray(arrayToSort);
//		System.out.println(Arrays.toString(arrayToSort));
//		PQSort.heapSort(arrayToSort);
////		PQSort.heapSort2011(arrayToSort);
//		System.out.println(Arrays.toString(arrayToSort));
//	}

	
	private static void shuffleArray(Integer[] array) {
		Random rand = new Random();
		for(int i=0;i<array.length;i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			int temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
	}
}
