package sorting;

import heap.Heap;
import heap.PQException;

// 힙 정렬까지 구현
public class Sorting<E extends Comparable> {
	private E[] A;
	
	public Sorting(E[] B) {
		A = B;
	}
	
	public E[] selectionSort() {
		int k;
		E tmp;
		for (int last = A.length - 1; last >= 1; last--) {
			k = Largest(last);
			tmp = A[k]; A[k] = A[last]; A[last] = tmp;
		}
		return A;
	}
	
	private int Largest(int last) {
		int largest = 0;
		for (int i = 0; i <= last; i++)
			if (A[i].compareTo(A[largest]) > 0)
				largest = i;
		return largest;
	}
	
	public E[] bubbleSort() {
		boolean flag = false;
		E tmp;
		for (int last = A.length - 1; last >= 1; last--)
			for (int k = 0; k < last; k++) {
				if (A[k].compareTo(A[k + 1]) > 0 ) {
					tmp = A[k]; A[k] = A[k + 1]; A[k + 1] = tmp;
					flag = true;
				}
				if (flag == false)
					break;
			}
		return A;
	}

	public E[] insertionSort() {
		for (int i = 1; i < A.length; i++) {
			int loc = i - 1;
			E tmp = A[i];
			while (loc >= 0 && tmp.compareTo(A[loc]) < 0) {
				A[loc + 1] = A[loc];
				loc--;
			}
			A[loc + 1] = tmp;
		}
		return A;
	}
	
	public E[] mergeSort() {
		E[] B = (E[]) new Comparable[A.length];
		mSort(B, 0, A.length - 1);
		return A;
	}
	
	private void mSort(E[] B, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mSort(B, p, q);
			mSort(B, q + 1, r);
			merge(B, p, q, r);
		}
		
	}
	
	private void merge(E[] B, int p, int q, int r) {
		int i = p; int k = q + 1; int t = 0;
		while (i <= q && k <= r) {
			if (A[i].compareTo(A[k]) <= 0)
				B[t++] = A[i++];
			else
				B[t++] = A[k++];
		}
		while (i <= q)
			B[t++] = A[i++];
		while (k <= r)
			B[t++] = A[k++];
		i = p; t = 0;
		while (i <= r)
			A[i++] = B[t++];
	}
	
	public E[] quickSort() {
		qSort(A, 0, A.length - 1);
		return A;
	}
	
	private void qSort(E[] A, int p, int r){
		if (p < r) {
			int q = partition(A, p, r);
			qSort(A, p, q - 1);
			qSort(A, q + 1, r);
		}
	}
	
	private int partition(E[] A, int p, int r) {
		E x = A[r]; // 피봇값이 맨 뒤값
		int i = p;
		E tmp;
		for (int k = i; k < r; k++) 
			if (A[k].compareTo(x) < 0) {
				i++;
				tmp = A[i]; A[i] = A[k]; A[k] = tmp;
			}
		tmp = A[i]; A[i] = A[r]; A[r] = tmp;
		return i;
	}
	
	// Heap.java 인용
	public E[] heapSort() throws PQException {
		Heap<E> heap = new Heap<>(A, A.length);
		heap.buildHeap();
		for (int i = A.length - 1; i >= 1; i--)
			A[i] = heap.deleteMax();
		return A;
	}
	
	/*public E[] selectionSort() {
		for (int i = 0; i < A.length - 1; i++)
			for (int k = i + 1; k < A.length; k++)
				if (A[k].compareTo(A[i]) < 0) {
					E tmp = A[k];
					A[k] = A[i];
					A[i] = tmp;
				}
		return A;
	}*/
}
