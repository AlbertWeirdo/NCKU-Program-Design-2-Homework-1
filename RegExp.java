import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.util.Arrays;

public class RegExp {

    public static boolean isSymmetric(String str) {
        int strLength = str.length();
        // System.out.println(strLength);
        char[] strtoArray = str.toCharArray();
        int symmetricCount = 0;
        for (int i = 0; i < (strLength / 2); i++) {
            if (strtoArray[i] == strtoArray[strLength - i - 1]) {
                symmetricCount++;
            }
        }
        // System.out.println(wordCount);
        if (strLength % 2 == 0) {
            if (symmetricCount == (strLength / 2)) {
                System.out.print("Y,");
            } else {
                System.out.print("N,");
            }
        } else {
            if (symmetricCount == (strLength - 1) / 2) {
                System.out.print("Y,");
            } else {
                System.out.print("N,");
            }
        }
        return true;
    }

    public static void checkString1(String str1, String str2) {
        int index = str1.indexOf(str2);
        if (index != -1) { // if the substring is not found in the string, it will return -1
            System.out.print("Y,");
        } else {
            System.out.print("N,");
        }
    }

    public static void string2RepeatedTimes(String str1, String str2, int repeatedTimes) {
        int timeCount = 0;
        int locFirst = str1.indexOf(str2);
        int locLast = str1.lastIndexOf(str2);
        int loc = 0;
        int tempLoc = 0;
        if (locFirst != locLast) {
            tempLoc = locFirst + 1;
            timeCount = 1;
            while (loc != str1.lastIndexOf(str2)) {
                loc = str1.indexOf(str2, tempLoc);
                tempLoc = loc + 1;
                timeCount++;
            }
        } else if ((locFirst == -1) && (locLast == -1)) {
            timeCount = 0;
        } else {
            timeCount = 1;
        }
        // System.out.println(timeCount);

        if (timeCount >= repeatedTimes) {
            System.out.print("Y,");
        } else {
            System.out.print("N,");
        }
    }

    public static boolean aMxb2M(String str) {
        int strLength = str.length();
        int[] aLoc = new int[strLength];
        int[] bLoc = new int[strLength];
        aLoc[0] = str.indexOf('a', 0);
        bLoc[0] = str.indexOf('b', 0);
        int[] aCount = new int[strLength];
        int[] bCount = new int[strLength];
        for (int i = 0; i < (strLength - 1); i++) { // Find out all the locations of a
            if (aLoc[0] == -1) {
                aCount[0] = 0;
                System.out.print("N\n");
                return true;
            }
            aLoc[i + 1] = str.indexOf('a', aLoc[i] + 1);
            if (aLoc[i + 1] == -1) {
                break;
            }
        }

        // System.out.println(Arrays.toString(aLoc));
        // System.out.println(aLoc.length);
        // System.out.println(aLoc[1]);

        for (int i = 0; i < (strLength - 1); i++) { // Find out the maximum time that a repeated sequentially
            int j = i;
            aCount[i] = 1;
            // System.out.println(aLoc[j]);
            if (aLoc[i] == -1) {
                break;
            }
            while ((aLoc[j + 1] - aLoc[j]) == 1) {
                aCount[i]++;
                if (j >= strLength - 2) {
                    break;
                }
                j++;
            }
            // System.out.println("aCount=" + aCount[i]);
        }

        for (int i = 0; i < (strLength - 1); i++) {
            if (bLoc[0] == -1) {
                bCount[0] = 0;
                break;
            }

            bLoc[i + 1] = str.indexOf('b', bLoc[i] + 1);
            if (bLoc[i + 1] == -1) {
                break;
            }
        }

        for (int i = 0; i < (strLength - 1); i++) { // Find out the maximum time that b repeated sequentially
            int j = i;
            bCount[i] = 1;
            // System.out.println(bLoc[j])
            if (bLoc[i] == -1) {
                break;
            }
            while ((bLoc[j + 1] - bLoc[j]) == 1) {
                bCount[i]++;
                if (j >= strLength - 2) {
                    break;
                }
                j++;
            }
            // System.out.println("bCount=" + bCount[i]);
        }

        // for (int i = 0; i < strLength; i++) {
        // if (aLoc[i] == -1) {
        // break;
        // }
        // System.out.println(aLoc[i]);
        // }

        // for (int i = 0; i < strLength; i++) {
        // if (bLoc[i] == -1) {
        // break;
        // }
        // System.out.println(bLoc[i]);
        // }

        int check = 0;
        for (int i = 0; i < (strLength - 1); i++) {
            if (aLoc[i] == -1) {
                break;
            }
            for (int j = 0; j < (strLength - 1); j++) {
                if (bCount[j] == -1) {
                    break;
                }
                if ((bCount[j] >= 2 * aCount[i]) && (bLoc[j] > aLoc[i])) {
                    check++;
                }
            }
        }
        if (check >= 1) {
            System.out.print("Y\n");
        } else {
            System.out.print("N\n");
        }

        return true;
    }

    public static void main(String[] args) {
        String str1 = args[1];
        str1 = str1.toLowerCase();
        String str2 = args[2];
        str2 = str2.toLowerCase();
        int s2Count = Integer.parseInt(args[3]);

        // For your testing of input correctness
        System.out.println("The input file:" + args[0]);
        System.out.println("str1=" + str1);
        System.out.println("str2=" + str2);
        System.out.println("num of repeated requests of str2 = " + s2Count);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            while ((line = reader.readLine()) != null) {
                // You main code should be invoked here
                line = line.toLowerCase();
                System.out.println(line);

                isSymmetric(line);
                checkString1(line, str1);
                string2RepeatedTimes(line, str2, s2Count);
                aMxb2M(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}