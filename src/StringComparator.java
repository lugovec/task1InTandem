
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by andrey on 06.01.17.
 */
class StringComparator implements Comparator<String> {

    @Override
    public int compare(final String string1, final String string2) {

        if (string1 == null || string2 == null)
            return nullComparator(string1, string2);

        if (string1.equals("") || string2.equals(""))
            return emptyStringComparator(string1, string2);


        return splitStringComparator(string1, string2);
    }


    /*В этом методе входная строка разбивается на подстроки
            по указанным в задании правилам*/
    private static ArrayList<String> splitter(String inputString) {

        ArrayList<String> outputString = new ArrayList<String>();

        Pattern pattern = Pattern.compile("\\d+|\\D+");
        Matcher matcher = pattern.matcher(inputString);

        while (matcher.find()) {
            outputString.add(matcher.group());
        }
        return outputString;
    }


    /*Этот метод работает тогда, когда в сравнении учувствует null */
    private int nullComparator(final String string1, final String string2){

        if (string1 == null) {
            return string2 == null ? 0 : -1;
        }

        if (string2 == null) {
            return 1;
        }
        return 0;
    }


    /*Этот метод работает тогда, когда в сравнении учувствует пустая строка*/
    private int emptyStringComparator(final String string1, final String string2){

        if (string1.equals("")) {
            return string2.equals("") ? 0 : -1;
        }

        if (string2.equals("")) {
            return 1;
        }
        return 0;
    }


    /*Этот метод сравнивает две ненулевые и непустые строки*/
    private int splitStringComparator(final String string1, final String string2){

        /*Разбиваем строки на подстроки с помощью метода splitter*/
        ListIterator<String> iterator1 = splitter(string1).listIterator();
        ListIterator<String> iterator2 = splitter(string2).listIterator();

        if (isNumber(string1))


    }


    /*Метод проверяет, является ли строка числом*/
    private boolean isNumber(String string){

        return string.matches("[-+]?\\d+");
    }


}