
public class QuickSelect {

    public static int quickSelect(int[] arr, int low, int high, int k) {
        if (low == high) {
            return arr[low];
        }

        int pivotIndex = partition(arr, low, high);

        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return quickSelect(arr, low, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, high, k);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static int findKthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k - 1); 
    }

    public static void main(String[] args) {
        int[] arr1 = { 4, 2, 7, 10, 4, 17 };
        int k1 = 3;
        System.out.println(
                "El " + k1 + "-ésimo elemento más pequeño en el arreglo es " + findKthSmallest(arr1, k1) + ".");

        int[] arr2 = { 4, 2, 7, 10, 4, 1, 6 };
        int k2 = 5;
        System.out.println(
                "El " + k2 + "-ésimo elemento más pequeño en el arreglo es " + findKthSmallest(arr2, k2) + ".");

        int[] arr3 = { 4, 2, 7, 1, 4, 6 };
        int k3 = 1;
        System.out.println(
                "El " + k3 + "-ésimo elemento más pequeño en el arreglo es " + findKthSmallest(arr3, k3) + ".");

        int[] arr4 = { 9, 2, 7, 1, 7 };
        int k4 = 4;
        System.out.println(
                "El " + k4 + "-ésimo elemento más pequeño en el arreglo es " + findKthSmallest(arr4, k4) + ".");
    }
}
