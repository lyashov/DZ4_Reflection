import java.util.HashMap;

/**
 * Class Student contains "ForClear" fields. They fields need's clear.
 * Fields old, name, rating, groupName need's
 */
public class Student{
    int oldForClear;
    String nameForClear;
    HashMap ratingForClear;
    String groupNameForClear;

    int old;
    String name;
    HashMap rating;
    String groupName;

    public Student(int oldForClear, String nameForClear, HashMap ratingForClear, String groupNameForClear,
                   int old, String name, HashMap rating, String groupName) {
        this.oldForClear = oldForClear;
        this.nameForClear = nameForClear;
        this.ratingForClear = ratingForClear;
        this.groupNameForClear = groupNameForClear;

        this.old = old;
        this.name = name;
        this.rating = rating;
        this.groupName = groupName;
    }
}