package gestiunebug.repository.bugs;

import gestiunebug.model.Bug;
import gestiunebug.model.User;
import gestiunebug.repository.IRepository;

public interface IBugRepository extends IRepository<Integer, Bug> {
    public Iterable<Bug> findAllActiveBugs();
    public Iterable<Bug> findAllBugsByVerificator(String verificator);
    public User findUserByUsername(String username);

}
