import java.util.Arrays;

public class Code {

    public Code(){



    }

    public long time(int N) {

        long startTime = System.currentTimeMillis();

        //code
        algorithm(getRandomArray(N));

        long endTime = System.currentTimeMillis();


        System.out.println("Program's running time with "+N+"：" + (endTime - startTime) + "ms");
        return endTime - startTime;
    }



    public int[] algorithm(int[] arr) {

        /*
        * Selection sort
        * */
        for (int i = 0; i < arr.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;

            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }

        return arr;

        /*sort(arr,0,arr.length-1);
        return arr;*/

        /*for (int i = 0; i < arr.length; i++) {

        }
        return arr;*/

        

    }

    public int[] getRandomArray(int log){
        int[] result = new int[log];
            for (int i = 0; i < log; i++) {
                result[i] = i;
            }
            for (int i = 0; i < log; i++) {
                int random = (int) (log * Math.random());
                int temp = result[i];
                result[random] = temp;
            }
                return result;
    }


    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];

        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
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

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }


    }
