
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class Solution {

    final double logOf5 = Math.log10(5);
    LinkedList<String> knownPowers;
    final String[] knownPowersArray = new String[]{"1", "101", "11001", "1111101", "1001110001", "110000110101",
        "11110100001001", "10011000100101101", "1011111010111100001", "111011100110101100101",
        "100101010000001011111001", "10111010010000111011011101", "1110100011010100101001010001",
        "1001000110000100111001110010101", "101101011110011000100000111101001", "11100011010111111010100100110001101",
        "10001110000110111100100110111111000001", "1011000110100010101111000010111011000101", "110111100000101101101011001110100111011001"};

    public Solution() {
        this.knownPowers = new LinkedList<>();
        knownPowers.addAll(Arrays.asList(knownPowersArray)); 
        
    }

    /**
     * Takes in an array of strings, and returns an array of answers
     *
     * @param s - string input array
     * @return - array of int answers of how many powers of five fit into each
     * string in the array
     */
    public int[] getMin(String[] s) {
        int[] results = findPower(s);

        return results;
    }

    /**
     *
     */
//  Uncomment for sequential execution    
//    public int[] getMin(String[] s) {
//        int[] results = new int[s.length];
//        int counter = 0;
//        for (String string : s) {
//            results[counter++]
//                    = isPoweredOfFive(string);
//        }
//        return results;
//    }
    /**
     * We will use Java 8's CompletableFutures to asynchronously execute the
     * computation for each binary string provided. The performance gains between the
     * multithreaded async and single threaded sequential are negligible - the setup overhead
     * of the multithreading might contribute to that. 
     *
     * @param values
     * @return
     */
    public int[] findPower(String[] values) {
//Uncomment to support custom executor       
//Executor executor = Executors.newFixedThreadPool(6, new ThreadFactory() {
//            public Thread newThread(Runnable r) {
//                Thread thread = new Thread(r);
//                thread.setDaemon(true);
//                return thread;
//            }
//        });

        List<CompletableFuture<Integer>> results = Stream.of(values)
                .map(value -> CompletableFuture.supplyAsync(() -> isPoweredOfFive(value.trim())))
                .collect(toList());

        List<Integer> exit = results.stream().map(CompletableFuture::join).collect(toList());

        int[] exitValues = exit.stream().mapToInt(x -> x).toArray();

        return exitValues;
    }

    
    /**
     * Steps:
     * 1. Check if supplied value is a valid binary (with 0s and 1s); exit if it is.
     * 2. Convert to decimal and use the change of base log formula to determine if supplied value is a whole power of five; exit if it is
     * 3. If not(3), look for known powers of five within the supplied string.
     * @param value
     * @return 
     */
    private int isPoweredOfFive(String value) {

        int powersOfFive = 0;
        if (!isValidBinary(value) || value.charAt(0) == '0') {

            return --powersOfFive; //not a valid binary, shortcircuit algo.

        }
        BigInteger decimalValue = convertToDecimal(value);
        float result = (float) (Math.log10(decimalValue.doubleValue()) / logOf5); // use change of base logarithm rule to determine if number is power of five

        if ((int) result == result) {

            return ++powersOfFive; //supplied binary is a whole power of 5, shortcircuit algo.
        } else {
            /**
             * Dynamic Programming: we will advance a pointer in increments of
             * the length known multiples of five that are found in the supplied
             * binary text until we run out of known multiples to search by. The
             * search will be conducted in reverse i.e. from the longest known
             * multiple to the shortest.
             */
            int currentIndex = 0;
            while (currentIndex < value.length()) {
                String currentToken = value.substring(currentIndex);
                char initChar = currentToken.charAt(0);
                if (initChar != '0') {
                    for (int i = knownPowers.size() - 1; i >= 0; i--) {
                        String nextKnownPower = knownPowers.get(i);
                        if (nextKnownPower.length() <= currentToken.length()) {
                            boolean foundIndex = currentToken.startsWith(nextKnownPower);

                            if (foundIndex) {
                                ++powersOfFive;
                                currentIndex += currentToken.indexOf(nextKnownPower) + nextKnownPower.length();
                                break;
                            }
                        }
                    }
                } else {
                    powersOfFive = -1;
                    break;
                }
             }
          }

        }

        return powersOfFive;
    }

    private BigInteger convertToDecimal(String binary) {
        BigInteger result = null;
        result = new BigInteger(binary, 2);
        return result;

    }

    private boolean isValidBinary(String value) {
        boolean valid = false;

        if (value.matches("[01]+")) {
            valid = true;
        }

        return valid;
    }

}
