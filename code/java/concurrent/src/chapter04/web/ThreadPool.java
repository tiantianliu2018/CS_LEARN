package chapter04.web;

/**
 * @author Kelly
 * @create 2020-05-24 16:10
 */
public interface ThreadPool<Job> {
    void execute(Job job);
}
