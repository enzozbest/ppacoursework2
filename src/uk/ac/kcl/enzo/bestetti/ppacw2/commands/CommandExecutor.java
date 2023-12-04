package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

/**
 * This class is part of the "White Souls" application.
 * <p>
 * This class is responsible for executing a command without having any knowledge of the command's actual implementation.
 * To achieve this, all commands are tagged with the Executable interface, and this class only calls the execute() method of the interface.
 * If something is Executable by this class, it will have an execute() method which implements the actual behaviour of the command.
 * <p>
 * This is the Invoker Class of the Command Pattern Design implemented in this project.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.01
 **/
public class CommandExecutor {

    /**
     * Call the execute() method of the Executable interface.
     **/
    public void perform(Executable executable) {
        executable.execute();
    }

}
