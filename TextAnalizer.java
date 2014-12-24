import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class TextAnalizer {

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Auto-generated method stub
        Scanner reader = new Scanner(new File("deneme1.txt"));
        int count = getTokenCount(reader);
        reader = new Scanner(new File("deneme1.txt"));
        String[] text = makeTokenArray(reader, count);
        fiftyPercent(text, count);
        someWordsSpan(text, count);
        consecutiveVords(text, count);
        percent30(text, count);

    }
    public static int getTokenCount(Scanner reader){
        int count = 0;
        String token;
        while(reader.hasNext()){
            count++;
            token = reader.next();
        }
        return count;
    }
    public static String[] makeTokenArray(Scanner reader, int count){
        String[] text = new String[count];
        String token;
        for (int i=0;i<count ;i++ ) {
            token = reader.next();
            token = token.toLowerCase();
            text[i] = token;
        }
        return text;
    }

    public static void fiftyPercent(String[] text, int count){
        for (int i=0; i<count; i++ ) {
            int wordCount = 0;
            for (int j=0;j<text.length; j++ ) {
                if(text[i].equals(text[j]))
                    wordCount++;
            }
            if(wordCount>count/2){
                System.out.println("This text is span");
                System.out.println(text[i] + " appears more than 50% of the time”.");
                return;
            }
        }
        System.out.println("Text is not span for 50% word");
    }

    public static void someWordsSpan(String[] text, int count){
        String[] someWords = {"apartment", "sale", "rent", "money", "bank"};
        for (String s:someWords) {
            int wordCount=0;
            for (int i = 0; i < count; i++) {
                if(s.equals(text[i])){
                    wordCount++;
                }
            }
            if(wordCount > 1){
                System.out.println("This text is span");
                System.out.println("Because the word "+s+" is more than 1");
                return;
            }
        }
        System.out.println("There is no span for someWord");
    }

    public static void percent30(String[] text, int count){
        String[] textCopy = text;
        String[] mostUsedWords = new String[3];
        String word="";
        double percent = (double)(count*30)/100;
        int wordExcessing30Percent=0;
        for (int i=0; i<count; i++ ) {
            int wordCount = 0;
            for (int j=0;j<count; j++ ) {
                if( textCopy[i]!=null && textCopy[i].equals(textCopy[j])){
                    wordCount++;
                    word = textCopy[j];
                    if(wordCount>1){
                        textCopy[j]=null;
                    }
                }
            }
            if(wordCount>percent){
                mostUsedWords[wordExcessing30Percent] = word;
                wordExcessing30Percent++;
            }
            if(wordExcessing30Percent>=3){
                System.out.println("Text is span");
                System.out.println("Because of words: "+ mostUsedWords[0] + " " + mostUsedWords[1] + " " + mostUsedWords[2]);
                return;
            }
        }
        System.out.println("Text is not span for percent30");

    }

    public static void consecutiveVords(String[] text, int count){
        String[] consecutives = {"dear", "sir", "madam", "honorable"};
        for (int i=0;i<count ;i++ ) {
            for(String s: consecutives){
                if(text[i].equals(s)){
                    for (String t: consecutives) {
                        if(text[i+1].equals(t)){
                            System.out.println("This text is span");
                            System.out.println("Because the words "+s+" and "+t+" are consecutives");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Text is not span for consecutiveVords");
    }
}
