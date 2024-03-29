package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {

        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            JCommander.newBuilder()
                    .addObject(generator)
                    .build()
                    .parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("csv")) {
            saveAsCsv(users, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(users, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<UserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsCsv(List<UserData> users, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (UserData user : users) {
            writer.write(String.format("%s; %s; %s; %s; %s\n", user.getFirstName(), user.getLastName(),
                    user.getAddress(), user.getHomePhone()));
        }
        writer.close();
    }

    private List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData()
                    .withFirstName(String.format("firstName %s", i))
                    .withLastName(String.format("lastName %s", i))
                    .withAddress(String.format("address %s", i))
                    .withHomePhone(String.format("%s%s%s%s", i,i,i,i)));
                   // .withGroup(String.format("test %s", i)));
        }
        return users;
    }
}
