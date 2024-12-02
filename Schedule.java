
/**
 * Write a description of interface Schedule here.
 *
 * @author kc
 * @version 1
 */

public interface Schedule {
    // Called each hour, allowing the object to perform an action.
    public void action();
    // Will store the object’s current information into a log file.
    public int log_status();
}

