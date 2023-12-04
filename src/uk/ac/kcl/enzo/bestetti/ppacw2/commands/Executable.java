package uk.ac.kcl.enzo.bestetti.ppacw2.commands;

/**
 * This interface is used to tag commands that can be performed by the CommandExecutor class.
 * It only declares the execute() method to be implemented by all commands.
 *
 * @author Enzo Bestetti(K23011872)
 * @version 2023.12.04
 **/
public interface Executable {

    /**
     * Normally used to implement some behaviour related to a command.
     **/
    void execute();
}