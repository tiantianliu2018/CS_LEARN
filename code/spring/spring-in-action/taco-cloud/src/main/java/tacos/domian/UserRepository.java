package tacos.domian;

import org.springframework.data.repository.CrudRepository;
import tacos.domian.entity.User;

/**
 * @author liutiantian
 * @create 2020-04-08 19:51
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
