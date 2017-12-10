package com.sorting;

public class MergeStandard {
	private static void merge(String arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
 
        String L[] = new String [n1];
        String R[] = new String [n2];
 
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
        int i = 0, j = 0;
        int k = l;
        
        while (i < n1 && j < n2)
        {
            if (L[i].compareTo(R[j]) <= 0)
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
	
	private static void beginSort(String arr[], int l, int r) {
		if (l < r)
        {
            int m = (l+r)/2;

            beginSort(arr, l, m);
            beginSort(arr , m+1, r);
 
            merge(arr, l, m, r);
        }
	}
 
    public static void sort(String arr[]) {
        beginSort(arr, 0, arr.length-1);
    }
}
