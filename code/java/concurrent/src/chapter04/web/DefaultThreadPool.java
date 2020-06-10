package chapter04.web;

/**
 * @author Kelly
 * @create 2020-05-24 16:09
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    public DefaultThreadPool(int num) {
    }

    @Override
    public void execute(Job job) {

    }
}
