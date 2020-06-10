package staticproxy;

/**
 * @author Kelly
 * @create 2020-06-03 14:27
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save(String name) {
        try {
            System.out.println("开启事务");
            System.out.println("处理业务逻辑，调用 DAO~");
            System.out.println("提交事务");
        } catch (Exception e){
            System.out.println("回滚事务");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            System.out.println("开启事务");
            System.out.println("处理业务逻辑，调用 DAO~");
            System.out.println("提交事务");
        } catch (Exception e){
            System.out.println("回滚事务");
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try {
            System.out.println("开启事务");
            System.out.println("处理业务逻辑，调用 DAO~");
            System.out.println("提交事务");
        } catch (Exception e){
            System.out.println("回滚事务");
            e.printStackTrace();
        }
    }

    @Override
    public String findAll(String name) {
        try {
            System.out.println("开启事务");
            System.out.println("处理业务逻辑，调用 DAO~");
            System.out.println("提交事务");
        } catch (Exception e){
            System.out.println("回滚事务");
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public String findOne(String id) {
        try {
            System.out.println("开启事务");
            System.out.println("处理业务逻辑，调用 DAO~");
            System.out.println("提交事务");
        } catch (Exception e){
            System.out.println("回滚事务");
            e.printStackTrace();
        }
        return id;
    }
}
