package com.anish.calabashbros;

public class BubbleSorter<T extends Comparable<T>> implements Sorter<T>,SomeConstants {

    private T[][] a;

    @Override
    public void load(T[][] a) {
        this.a = a;
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i/num][i%num];
        a[i/num][i%num] = a[j/num][j%num];
        a[j/num][j%num] = temp;
        plan += "" + a[i/num][i%num] + "<->" + a[j/num][j%num] + "\n";
        System.out.println(plan);
    }

    private String plan = "";

    @Override
    public void sort() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < a.length*a[0].length-1; i++) {
                if (a[i/num][i%num].compareTo(a[(i + 1)/num][(i+1)%num]) > 0) {
                    swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}