public class Code {

    public Code(){



    }

    public long time(int N) {

        long startTime = System.currentTimeMillis();

        //code
        algorithm(getRandomArray(N));

        long endTime = System.currentTimeMillis();

        System.out.println("Programming running time with+"+N+"：" + (endTime - startTime) + "ms");
        return endTime - startTime;
    }

    public void transfer() {
        int N1 = 100000;
        int N2 = 200000;
        long time1 = time(N1);
        long time2 = time(N2);

        //int uncertainty = 3000;
        if (Math.abs(time2/time1) - (N2/N1)*(N2/N1)<2.0){
            System.out.println("The time complexity should be around O(N^2)");
            System.out.println("Is it selection sort?");
        } else System.out.println("....");


    }





    public int[] algorithm(int[] arr) {
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
    }
