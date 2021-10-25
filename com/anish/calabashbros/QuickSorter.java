package com.anish.calabashbros;

import com.anish.screen.WorldScreen;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T>,SomeConstants {
    private T[][] a;

    @Override
    public void load(T[][] a) {
        this.a = a;
    }

    private void swap(T[][] a, int i, int j) {
        T temp;
        temp = a[i/num][i%num];
        a[i/num][i%num] = a[j/num][j%num];
        a[j/num][j%num] = temp;
        plan += "" + a[i/num][i%num] + "<->" + a[j/num][j%num] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        quickSort(a, 0, a.length*a[0].length-1);
    }

    public void quickSort(T[][] a, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(a, lo, hi);
            quickSort(a, lo, pivot - 1);
            quickSort(a, pivot + 1, hi);
        }
    }

    public int partition(T[][] a, int lo, int hi) {
        T x = a[lo/num][lo%num];
        int j = hi + 1;

        for (int i = hi; i > lo; i--) {
            if (a[i/num][i%num].compareTo(x) > 0) {
                j--;
                swap(a, i, j);
            }
        }
        swap(a, lo, j - 1);
        return j - 1;
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}