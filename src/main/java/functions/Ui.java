package functions;

public class Ui {

    /**
     * A public constructor to initialize a new Ui instance
     *
     */
    public Ui() {
        System.out.println("____________________________________________________________");
        System.out.println("Initializing...");
    }
    public void fileNotFound() {
        System.out.println("File not found. Creating new .txt save file");
    }

    public void hello() {
        System.out.println("Hello, I'm Runner.Cupid.");
        System.out.println("What can I do for you?");
    }
    public String goodbye() {
        String message = "";
        message += "____________________________________________________________\n";
        message += "Bye. Hope to see you again soon!\n";
        message += "____________________________________________________________";
        return message;
    }
}
