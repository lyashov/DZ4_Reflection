import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
/**
Необходимо реализовать метов void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput),
принимающий любой объект и две коллекции строк. В объекте, посредством reflection надо поля, перечисленные в fieldsToClenup
установить в значение null, или, если поля примитивных типов в их значение по умолчанию. Поля, перечисленные в fieldsToOutput
сконвертировать в строку (вызвав toString у объектов, или String.valueOf для примитивных типов) и вывести результат преобразования
в консоль. Если переданный первым параметром объект является реализацией интерфейса Map, то проделать аналогичные
операции - для списка fieldsToCleanup удалить ключи из мапы, для fieldsToOutput вывести в консоль значения, хранящиеся в мапе.
При отсутствии в объекте/мапе нузных полей/ключей - падать с IllegalArgumentException, оставив объект неизменным.
* */
public class DoCleanup {
    private static void setDefaultValue(Field field, Object object) throws IllegalAccessException {
        /*short = 0
        byte = 0
        int = 0
        double = .0d
        float = .0f
        long = 0L
        char = \u0000
        boolean = false */
        switch (field.getType().getName()) {
            case "short":
                field.set(object, 0);
                break;
            case "byte":
                field.set(object, 0);
                break;
            case "int":
                field.set(object, 0);
                break;
            case "double":
                field.set(object, 0);
                break;
            case "float":
                field.set(object, .0f);
                break;
            case "long":
                field.set(object, 0L);
                break;
            case "char":
                field.set(object, '\u0000');
                break;
            case "boolean":
                field.set(object, false);
                break;
            default:
                field.set(object, 0);
                break;
        }
    }

    /**
     * Delete map's element by key "fieldsToCleanup" 2. sout fieldsToOutput
     * @param object
     * @param fieldsToCleanup
     * @param fieldsToOutput
     */
    private static void cleanupMap(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) {
        for (String fieldForClean : fieldsToCleanup) {
            Map map = (Map) object;
            String st = (String) map.remove(fieldForClean);
            if (st == null) throw new IllegalArgumentException();
        }

        for (String fieldForOutput : fieldsToOutput) {
            Map map = (Map) object;
            try {
                String st = (String) map.get(fieldForOutput);
                System.out.println(st);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
    }
    /**
     * Clear fieldsToCleanup 2. sout fieldsToOutput
     * @param object
     * @param fieldsToCleanup
     * @param fieldsToOutput
     */
    private static void cleanupObject(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws IllegalAccessException, NoSuchFieldException {
        for (String fieldForClean : fieldsToCleanup) {
            Field field = object.getClass().getDeclaredField(fieldForClean);
            if (!field.isAccessible()) field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                setDefaultValue(field, object);
            } else field.set(object, null);
        }

        for (String fieldForOutput : fieldsToOutput) {
            Field field = object.getClass().getDeclaredField(fieldForOutput);
            if (!field.isAccessible()) field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                System.out.println(String.valueOf(field.get(object)));
            } else {
                // is not primitive and map's value
                System.out.println(field.get(object).toString());
            }
        }
    }

    /**
     * If parameter №1 is object, then 1. clear fieldsToCleanup 2. sout fieldsToOutput
     * If parameter №1 is map, then 1. delete map's element by key "fieldsToCleanup" 2. sout fieldsToOutput
     * @param object some class or hashmap
     * @param fieldsToCleanup fields for clean
     * @param fieldsToOutput fields for output
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws NoSuchFieldException, IllegalAccessException {
        if (object instanceof Map)
            cleanupMap(object, fieldsToCleanup, fieldsToOutput);
        else
            cleanupObject(object, fieldsToCleanup, fieldsToOutput);
    }

}
