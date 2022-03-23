package sort;

public class test {
    public static void main(String[] args) {
        Selection<Integer> tSelection = new Selection<>();
        Integer[] integers = {1, 3, 4, 5, 6, 4, 2, 453, 4, 34, 53, 45, 43, 5, 34, 5};
        for (Integer integer : integers) {
            System.out.print(integer+",");
        }
        System.out.println();
        tSelection.sort(integers);
        for (Integer integer : integers) {
            System.out.print(integer+",");
        }
        System.out.println();

    }
}
