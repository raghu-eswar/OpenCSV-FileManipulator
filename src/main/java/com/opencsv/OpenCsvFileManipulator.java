package com.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class OpenCsvFileManipulator {

    private static final String OBJECT_LIST_SAMPLE = "./object-list-sample.csv";

    private static ArrayList<MyUser> readBeans() throws IOException {
        ArrayList<MyUser> myUsers = new ArrayList<>();
        Reader reader = Files.newBufferedReader(Paths.get(OBJECT_LIST_SAMPLE));
        CsvToBean<MyUser> csvToBean = new CsvToBeanBuilder(reader).withType(MyUser.class).withIgnoreLeadingWhiteSpace(true).build();
        for (MyUser myUser : csvToBean) {
            myUsers.add(myUser);
        }
        return myUsers;
    }

    private static void reWriteBeans(MyUser ... myUsers) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Writer writer = Files.newBufferedWriter(Paths.get(OBJECT_LIST_SAMPLE));
        StatefulBeanToCsv<MyUser>beanToCsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
        beanToCsv.write(Arrays.asList(myUsers));
        writer.close();
    }

    private static void addBeans(MyUser ... myUsers) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        ArrayList<MyUser> newUserList = readBeans();
        newUserList.addAll(Arrays.asList(myUsers));
        reWriteBeans(newUserList.toArray(new MyUser[0]));
    }

   private static void removeBeans(MyUser ... myUsers) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        ArrayList<MyUser> newUserList = readBeans();
        newUserList.removeAll(Arrays.asList(myUsers));
       reWriteBeans(newUserList.toArray(new MyUser[0]));
    }

    private static void eraserData() throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        reWriteBeans();
    }

    public static void main(String[] args) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        ArrayList<MyUser> myUsers = new ArrayList<>();
        myUsers.add((new MyUser().setName("ghi").setPhone("9898989898").setMail("mail@mail.com")));
        myUsers.add((new MyUser().setName("jkl").setPhone("9898989898").setMail("mail@mail.com")));

//      Can do some operations With file
    }
}
