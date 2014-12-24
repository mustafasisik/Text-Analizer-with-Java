import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TextAnalizer {

    public static void main(String[] args) throws FileNotFoundException {
        //Main method
        Scanner reader = new Scanner(new File("data.txt")); // new text
        int count = getTokenCount(reader);
        reader = new Scanner(new File("data.txt"));
        String[] tokenArray = makeTokenArray(reader, count);
        fiftyPercent(tokenArray, count);
        someWordsSpan(tokenArray, count);
        consecutiveVords(tokenArray, count);
        percent30(tokenArray, count);

    }
    /**
     * Calculates the count of token in txt file
     *
     * @param reader is file object
     * @return count of token in txt file
     */
    public static int getTokenCount(Scanner reader){
        int count = 0;
        while(reader.hasNext()){
            count++;
            reader.next();
        }
        return count;
    }
    /**
     *  Makes array from a text file's token
     *
     * @param reader is file object we are reading tokens
     * @param count is count of token in text file
     * @return array of all tokens
     */
    public static String[] makeTokenArray(Scanner reader, int count){
        String[] tokenArray = new String[count];
        String token;
        // reading and storing it in array
        for (int i=0;i<count ;i++ ) {
            token = reader.next();
            token = token.toLowerCase();
            tokenArray[i] = token;
        }
        return tokenArray;
    }

    /**
     * Determines the token array is span or not regarding a word is more than 50%
     *
     * @param tokenArray is token array read from txt file
     * @param count is the length of tokenArray
     */
    public static void fiftyPercent(String[] tokenArray, int count){
        // counting for each element's count
        for (int i=0; i<count; i++ ) {
            int wordCount = 0;
            for (int j=0;j<tokenArray.length; j++ ) {
                if(tokenArray[i].equals(tokenArray[j]))
                    wordCount++;
            }

            // if word's count is more than 50%
            if(wordCount>count/2){
                System.out.println("This text is span");
                System.out.println("\""+ tokenArray[i] + "\" appears more than 50% of the time\n");
                return;
            }
        }
        System.out.println("Text is not span for 50% word\n");
    }

    /**
     * Determines the token array is span or not regarding some word is more than one.
     *
     * @param tokenArray is token array read from txt file
     * @param count is the length of tokenArray
     */
    public static void someWordsSpan(String[] tokenArray, int count){
         // some determined word
        String[] someWords = {"apartment", "sale", "rent", "money", "bank"};

        // for each determined word control is it more than 1
        for (String s:someWords) {
            int wordCount=0;
            for (int i = 0; i < count; i++) {
                if(s.equals(tokenArray[i])){
                    wordCount++;
                }
            }
            if(wordCount > 1){
                System.out.println("This text is span");
                System.out.println("Because the word "+s+" is more than 1\n");
                return;
            }
        }
        System.out.println("Text is not span for determined word repeating one more\n");
    }

    /**
     * Determines the token array is span or not regarding 3 word is more than 30%
     *
     * @param percent is 30% of all tokens
     * @param tokenArray is token array read from txt file
     * @param count is the length of tokenArray
     */
    public static void percent30(String[] tokenArray, int count){
        // text token array is copied because array will be changed.
        String[] tokenArrayCopy = tokenArray;

        // word listing array which is used more than 30%
        String[] mostUsedWords = new String[3];

        //variable word is storing string before it deleted from array
        String word="";

        double percent = (double)(count*30)/100;

        // controlling the list size
        int wordExcessing30Percent=0;
        for (int i=0; i<count; i++ ) {
            int wordCount = 0;
            for (int j=0;j<count; j++ ) {
                if( tokenArrayCopy[i]!=null && tokenArrayCopy[i].equals(tokenArrayCopy[j])){
                    wordCount++;
                    word = tokenArrayCopy[j];
                    if(wordCount>1){
                        tokenArrayCopy[j]=null;
                    }
                }
            }

            // if wordCount is bigger than 30% then add word to list
            if(wordCount>percent){
                mostUsedWords[wordExcessing30Percent] = word;
                wordExcessing30Percent++;
            }

            // if 3 or more word excess 30% text is span
            if(wordExcessing30Percent>=3){
                System.out.println("Text is span");
                System.out.println("Because of words: \""+ mostUsedWords[0] + " "
                + mostUsedWords[1] + " " + mostUsedWords[2]+"\" are excessing %30 of time\n");
                return;
            }
        }
        System.out.println("Text is not span for 3 words excessing 30%\n");

    }

   /**
    * Determines the token array is span or not regarding a determined word is after determined word
    *
    * @param tokenArray is token array read from txt file
    * @param count is the length of tokenArray
    */
    public static void consecutiveVords(String[] tokenArray, int count){
        String[] consecutives = {"dear", "sir", "madam", "honorable"}; // determined words

        // controlling for all elements
        for (int i=0;i<count ;i++ ) {

            //controlling for determined words
            for(String s: consecutives){

                //
                if(tokenArray[i].equals(s)){
                    for (String t: consecutives) {
                        if(count>i+2 && tokenArray[i+1].equals(t)){
                            System.out.println("This text is span");
                            System.out.println("Because the words "+s+" and "+t+" are consecutives\n");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Text is not span for Consecutive Vords\n");
    }
}
