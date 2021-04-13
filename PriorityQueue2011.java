import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityQueue2011<E extends Comparable<? super E>> extends AbstractQueue<E>{

	// completed in a group of 3 
	// Darshan Patel,Sagarkumar Patel,Jay Patel also attached group.txt

	private int initialCapacity;
	private E[] values;
	private int size = 1;


	@SuppressWarnings("unchecked")
	public PriorityQueue2011(){
		//Constructor
		this.initialCapacity = 11;
		this.values = (E[]) new Comparable[this.initialCapacity]; 
	}


	// the below helper method we check if the size of the is not full
	// if its full we double the size and copy the previous value.
	private void checkArraySize() {
		if(size == initialCapacity) {
			values = Arrays.copyOf(values, initialCapacity*2);
			initialCapacity *= 2;
		}
	}

	
	@Override
	public boolean offer(E e) {
		add(e);
		return true;
	}


	public boolean add(E e) {
		checkArraySize();

		if(e == null) {
			throw new NullPointerException();
		}

		// to implement Upheap algorithm to adjust the new val
		// that the user need to implement into the PriorityQueue.

		values[size] = e;
		size += 1;

		// implement the algo. of Upheap

		int tempIndex = size - 1;

		E parentValue = values[((tempIndex) / 2)];

		/*
		 * 	//DEBUG CODE
		 * 		System.out.println("Parent Value : "+parentValue);
		 *		System.out.println("lastSecond value : " + values[tempIndex]);
		 * 		System.out.println();
		 * 
		 * 		if(parentValue != null)
		 *		System.out.println(parentValue.compareTo(values[tempIndex]));
		 */

		while( parentValue != null &&  parentValue.compareTo(values[tempIndex]) > 0){
			// swap the value with parent as per upheap logic
			E temp = parentValue;
			// change the values of the parent
			values[((tempIndex) / 2)] = values[tempIndex];
			values[tempIndex] = temp;

			tempIndex = ((tempIndex) / 2);
			parentValue = values[((tempIndex) / 2)];
		}
		return true;
	}


	@Override
	public E poll() {
		// TODO Auto-generated method stub
		if(size == 1) {
			return null;
		}		
		return remove();
	}


	public E remove() {

		if(size == 1) {
			//we know that our Priority Queue is empty
			// So we just throw an exception
			throw new NoSuchElementException();
		}

		E rootVal = values[1];
		values[1] = values[size - 1];
		values[size-1] = null;
		//so as we are removing we need to decrement the size by one
		size -= 1;


		int tempIndex = 1;

		E leftChildVal = values[2*tempIndex];

		//---------- DEBUG CODE ---------//
		//		System.out.println("leftChildVal :" + leftChildVal);
		//		E rCv = values[2*tempIndex + 1];
		//		System.out.println("rightCHildVal :" + rCv);

		while(leftChildVal != null && tempIndex*2 < initialCapacity){

			E rightChildVal = values[2*tempIndex+1]; 
			int guessSmallerChildIndex = 2*tempIndex;

			if(rightChildVal != null && leftChildVal.compareTo(rightChildVal) > 0 ) {

				guessSmallerChildIndex = 2*tempIndex+1;

			}

			//---------- DEBUG CODE ---------//
			//			System.out.println(toTree());
			//			System.out.println(values[tempIndex] + "    " + tempIndex);
			//			System.out.println(values[guessSmallerChildIndex]);

			if(values[guessSmallerChildIndex] != null) {
				if(values[tempIndex].compareTo(values[guessSmallerChildIndex]) < 0 ) {
					break;
				} else {
					E temp = values[guessSmallerChildIndex];
					values[guessSmallerChildIndex] = values[tempIndex];
					values[tempIndex] = temp;
				}
				tempIndex = guessSmallerChildIndex;

				if(tempIndex*2 <= initialCapacity)
					leftChildVal = values[2*tempIndex];
			}

			//---------- DEBUG CODE ---------//
			//			System.out.println("remove tree status : -");
			//			System.out.println(toTree());
			//			System.out.print("left child val :- ");
			//			System.out.println(leftChildVal);
			//			System.out.print("initial Capacity :-");
			//			System.out.println(initialCapacity);
			//			System.out.print("Array :- ");
			//			System.out.println(Arrays.toString(values));
		}

		//		System.out.println(toTree());
		return rootVal;
	}


	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if(size == 1) {
			//we know that our Priority Queue is empty
			// So we just throw an exception
			return null;
		}
		// else there must be a value so we just return the value
		return values[1];
	}


	public E element() {

		// to create a helper method for isEmpty()
		// is empty true then throw an exception
		// else only send the value without removing it from the queue.
		if(this.size == 1) {
			// to check if it is indexout of bound error or not.
			throw new NoSuchElementException ();
		} else {
			return  values[1];
		}
	}


	@Override
	public int size() {
		// TODO Auto-generated method stub

		// to check whether null is to be counted in the size or not
		return size;
	}


	@Override
	public String toString() {

		/*
		 *  Output must be the below format 
		 *  [null, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
		 */

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for(int i = 0;i<size-1;i++) {

			sb.append(values[i] +", ");
		}

		sb.append(values[size-1] + "]");
		return sb.toString();
	}


	public String toTree() {
		/*
		 * Output must be in the below formal pattern
		 * 
		 *              1
		 *        2            3
		 *     4     5      6     7
		 *   8  9 10  11  12 13 14 15
		 * 
		 * 
		 */

		//---------- DEBUG CODE ---------//
		//		System.out.println("size :- "+ size);
		//		System.out.print("values.length :-");
		//		System.out.println(values.length-1);
		//		System.out.println(initialCapacity / 2);
		
		
		StringBuffer sb = new StringBuffer();
		int tempsize = 1;

		while(tempsize < size) {
			tempsize *= 2;
		}

		//*2 -1
		int noOfLeftSpace = ((tempsize - 1) / 2);
		//		System.out.println(noOfLeftSpace);
		int firstSpaceVal = 0;

		for(int i=1;i<size;i=i*2){
			firstSpaceVal = i;
			//	      System.out.println("noOfLeftSpace :- " + noOfLeftSpace);
			for(int j = i;j<i*2 && j < size;j++){
				if(firstSpaceVal == j){
					for(int k=0;k<noOfLeftSpace;k++){
						sb.append(String.format("%3s", " "));
//						sb.append("   ");
					}
				} else {
					for(int u=0;u<=noOfLeftSpace*2;u++){
						sb.append(String.format("%3s", " "));
//						sb.append("   ");
					}
				}
				//	        we are just assuming that largest number is of 3 digit so while using append we generate 3 spaces and format values with %3d.
				//	        we can represent a number with any number of digit just by replacing %3d with maximum no of digits in place of 3 in %3d and appending 
				//	        that number of spaces wherever we generate space in tree representation.
				sb.append(String.format("%3d", values[j]));  
			}
			for(int k=0;k<noOfLeftSpace;k++){
				sb.append(String.format("%3s", " "));
//				sb.append("   ");
			}
			noOfLeftSpace = noOfLeftSpace / 2;
			sb.append("\n");
		}
		return sb.toString();
	}


	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//---------- DEBUG CODE ---------//
//		PriorityQueue2011 <Integer> heap = new PriorityQueue2011<>();
//		System.out.println(heap.toArray());
		//heap.add(20);
		//heap.add(11);
		//heap.add(8);
		//heap.add(55);
		//heap.add(63);
		//heap.add(296);
		//heap.add(462);
		//heap.add(7);
		//heap.add(0);
		//heap.add(126);
		//heap.offer(111);
		//heap.add(362);
		//heap.add(123);
		//heap.add(524);
		//heap.add(71);
		//heap.add(6);
		//System.out.println(heap.toTree());
	}
}
