package functions;

import commands.*;

/**
 * A utility class for parsing user input and generating appropriate commands.
 */
public class Parser {

    private String input;
    private TaskList taskList;

    private enum ChatFunction {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        HELP
    }

    /**
     * A public constructor to initialize a new Parser instance
     *
     * @param input file path to load
     * @param taskList a task list containing all the tasks stored
     */
    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Parses the input specified by the Parser object and executes specified command.
     *
     * @return A String message generated by the execution of specified command.
     */
    public String parse() {
        String[] inputArray = input.split(" ");

        try {
            ChatFunction function = ChatFunction.valueOf(inputArray[0].toUpperCase());

            int firstSpaceIndex = input.indexOf(" ");
            String functionDescription = input.substring(firstSpaceIndex + 1);

            Command command = null;

            switch (function) {
            case LIST:
                command = new ListCommand(this.taskList);
                break;

            case MARK:
                command = new MarkCommand(this.taskList, inputArray);
                break;

            case UNMARK:
                command = new UnmarkCommand(this.taskList, inputArray);
                break;

            case DELETE:
                command = new DeleteCommand(this.taskList, inputArray);
                break;

            case TODO:
                command = new ToDoCommand(this.taskList, functionDescription);
                break;

            case DEADLINE:
                command = new DeadlineCommand(this.taskList, functionDescription);
                break;

            case EVENT:
                command = new EventCommand(this.taskList, functionDescription);
                break;

            case FIND:
                command = new FindCommand(this.taskList, functionDescription);
                break;

            case HELP:
                command = new HelpCommand();
                break;

            default:
                break;
            }

            assert command != null : "Command should not be null or empty";

            String result = command.execute();
            assert result != null : "Result should not be null or empty String";

            return result;
        } catch (IllegalArgumentException e) {
            // If task inserted not an ENUM
            String illegalArgumentResult = "";
            illegalArgumentResult += "Oops!!! I'm sorry but I don't know what that means :-( \n";
            illegalArgumentResult += "Run help to get a list of available commands.";
            return illegalArgumentResult;
        } catch (NullPointerException e) {
            String nullPointerResult = "";
            nullPointerResult += "Oh no, you have entered an invalid statement. \n";
            nullPointerResult += "Run help to get a list of available commands.";
            return nullPointerResult;
        } catch (Exception e) {
            return "I did not understand that! Run help to get a list of available commands.";
        }
    }

}
