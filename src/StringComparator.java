
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by andrey on 06.01.17.
 */
class StringComparator implements Comparator<String> {

    /*Здесь переопределяется метод compare. Может быть три случая:
    1) Одна из строк null, тогда отрабатывает метод nullComparator(string1, string2)
    2) Одна из строк пустая, тогда отработает метод  emptyStringComparator(string1, string2)
    3) Обе строки не null и не пустые, тогда отработатет метод splitStringComparator(string1, string2),
        в котором строки разобьются на подстроки, которые и будут сравниваться*/
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
        Iterator<String> iterator1 = splitter(string1).iterator();
        Iterator<String> iterator2 = splitter(string2).iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {

            String substring1 = iterator1.next();
            String substring2 = iterator2.next();

            /*Если обе подстроки являются числами, то преобразуем подстроки в числа
                    и переменной result присваиваем результат сравнения. Если числа
                    равны то сравниваем следующие подстроки, если нет, то прекращаем цикл
                    и возвращаем результат*/
            if (isNumber(substring1) && isNumber(substring2)){
                int result = (new Integer(substring1)).compareTo(new Integer(substring2));
                    if (result != 0) return result;
            }

            /*Здесь подстроки сравниваются как строки, если они раны, идём дальше,
                иначе - прекращаем сравнение и возвращаем результат*/
            int result = substring1.compareTo(substring2);
                if (result != 0) return result;

        }

        /*Если подстроки равны, то та строка меньше, у которой меньше длина*/
        return string1.length() - string2.length();


    }


    /*Метод проверяет, является ли строка числом*/
    private boolean isNumber(String string){

        return string.matches("[-+]?\\d+");
    }


}