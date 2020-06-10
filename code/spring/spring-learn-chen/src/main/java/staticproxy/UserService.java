package staticproxy;

/**
 * @author Kelly
 * @create 2020-06-03 14:24
 */
public interface UserService {
    void save(String name);
    void delete(String id);
    void update();
    String findAll(String name);
    String findOne(String id);
}
