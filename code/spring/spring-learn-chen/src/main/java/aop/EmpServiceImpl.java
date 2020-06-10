package aop;

/**
 * @author Kelly
 * @create 2020-06-03 16:07
 */
public class EmpServiceImpl implements EmpService {
    @Override
    public void save(String name) {
        System.out.println("处理业务逻辑，调用 save DAO~ " + name);
    }

    @Override
    public String find(String name) {
        System.out.println("处理业务逻辑，调用 find DAO~ " + name);
        return name;
    }
}
