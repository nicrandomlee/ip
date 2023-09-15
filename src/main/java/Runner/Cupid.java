package Runner;

import functions.Parser;
import functions.Storage;
import functions.TaskList;
import functions.Ui;

import java.io.IOException;

public class Cupid {

    private String saveFilePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Cupid(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        this.ui = new Ui();
        this.taskList = null;
        this.storage = null;

        try {
            this.storage = new Storage(this.saveFilePath);
            this.taskList = this.storage.load();
        } catch (IOException e) {
            this.taskList = new TaskList();
            this.storage.save(this.taskList);
        }

    }

    public String getResponse(String input) {

        Parser parser = new Parser(input, this.taskList);
        String result = parser.parse();

        this.storage.save(this.taskList);
        return result;
    }

}
