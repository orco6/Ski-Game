package utilities;



/**
 * Created by itzhak on 15-Mar-19.
 * Validate utils used to validate input across the program
 */
public class ValidationUtils {
    /**
     * Can not create an instance of a util class
     */
    private ValidationUtils(){}

    /**
     * if the object is null throws IllegalArgumentException
     * @param o inspected object
     */
    public static void assertNotNull(Object o){
        if(o == null){
            throw new IllegalArgumentException("Argument can not be null");
        }
    }

    /**
     * if the double is negative throws IllegalArgumentException
     * @param n inspected number
     */
    public static void assertNotNegative(double n){
        if(n < 0){
            throw new IllegalArgumentException("Argument can not be negative argument = " + n);
        }
    }

    /**
     * if the double is not positive throws IllegalArgumentException
     * @param n inspected number
     */
    public static void assertPositive(double n){
        if(n <= 0){
            throw new IllegalArgumentException("Argument can not be negative or zero argument = " + n);
        }
    }

    /**
     * if n is not in range [start,end] throws IllegalArgumentException
     * @param n inspected number
     * @param start lower end of the range
     * @param end upper end of the range
     */
    public static void assertInRange(double n, double start, double end){
        if(n > end || n < start){
            throw new IllegalArgumentException("Argument must be in range ["+ start + "," + end + "] argument=" + n);
        }
    }

    /**
     * if the string is null/empty string/only spaces throws IllegalArgumentException
     * @param text inspected String
     */
    public static void assertNotNullOrEmptyString(String text){
        if(text == null || text.trim().equals("")){
            throw new IllegalArgumentException("String can not be null or empty");
        }
    }

}
