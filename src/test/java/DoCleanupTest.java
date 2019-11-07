import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DoCleanupTest {

    public void doTestWithHash() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String, String> studentFields = new HashMap<String, String>();
        studentFields.put("oldForClear", "20");
        studentFields.put("nameForClear", "Vasiliy");
        studentFields.put("ratingForClear", "65");
        studentFields.put("groupNameForClear", "AB-22");

        studentFields.put("old", "21");
        studentFields.put("name", "Vasiliy");
        studentFields.put("rating", "95");
        studentFields.put("groupName", "AB-21");

        Set<String> fieldsToCleanup = new HashSet<String>();
        fieldsToCleanup.add("oldForClear");
        fieldsToCleanup.add("nameForClear");
        fieldsToCleanup.add("ratingForClear");
        fieldsToCleanup.add("groupNameForClear");
        fieldsToCleanup.add("IllegalArgument");

        Set<String> fieldsToOutput = new HashSet<String>();
        fieldsToOutput.add("old");
        fieldsToOutput.add("name");
        fieldsToOutput.add("rating");
        fieldsToOutput.add("groupName");

        DoCleanup.cleanup((Object) studentFields , fieldsToCleanup, fieldsToOutput);
    }

    public void doTestWithObject() throws NoSuchFieldException, IllegalAccessException {
        HashMap<String, String> raitingForClear = new HashMap<String, String>();
        raitingForClear.put("Math", "2");
        raitingForClear.put("English", "3");

        HashMap<String, String> newRaiting = new HashMap<String, String>();
        newRaiting.put("Math", "5");
        newRaiting.put("English", "5");


        Student student = new Student(20, "Vasiliy", raitingForClear,"AB-22",
                21, "Vasiliy", newRaiting, "AB-21");

        Set<String> fieldsToCleanup = new HashSet<String>();
        fieldsToCleanup.add("oldForClear");
        fieldsToCleanup.add("nameForClear");
        fieldsToCleanup.add("ratingForClear");
        fieldsToCleanup.add("groupNameForClear");

        Set<String> fieldsToOutput = new HashSet<String>();
        fieldsToOutput.add("old");
        fieldsToOutput.add("name");
        fieldsToOutput.add("rating");
        fieldsToOutput.add("groupName");

        DoCleanup.cleanup((Object) student , fieldsToCleanup, fieldsToOutput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cleanupMap() throws NoSuchFieldException, IllegalAccessException {
        doTestWithHash();
    }

    @Test
    public void cleanupObject() throws NoSuchFieldException, IllegalAccessException {
        doTestWithObject();
    }
}