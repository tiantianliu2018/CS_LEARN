package chapter04.connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author Kelly
 * @create 2020-05-24 15:06
 *
 * 一个简单的数据库连接池示例
 *
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0){
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放一个连接
     * @param connection
     */
    public void releaseConnection(Connection connection){
        if (connection != null){
            synchronized (pool){
                // 连接释放后需要进行通知
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 在 mills 内获取连接
     * @param mills
     * @return
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            // 完全超时
            if (mills <= 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
