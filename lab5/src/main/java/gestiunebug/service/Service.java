package gestiunebug.service;

import gestiunebug.model.Bug;
import gestiunebug.model.User;
import gestiunebug.repository.bugs.BugRepository;
import gestiunebug.repository.user.UserRepository;

import java.util.List;
import java.util.Objects;

public class Service {
    private final UserRepository userRepository;
    private final BugRepository bugRepository;

    public Service(UserRepository userRepository, BugRepository bugRepository) {
        this.userRepository = userRepository;
        this.bugRepository = bugRepository;
    }

    public String findExistenceOfUser(String username, String password) throws Exception{
        User found = userRepository.getOne(username);
        if(found == null)
            throw new Exception("Incorrect Username");

        if(!Objects.equals(found.getPassword(), password))
            throw new Exception("Incorrect password!");

        return found.getRole();
    }

    public List<Bug> findAllActiveBugs(){
        return (List<Bug>) bugRepository.findAllActiveBugs();
    }
    public List<Bug> findAllBugsByVerificator(String username){return (List<Bug>) bugRepository.findAllBugsByVerificator(username);}


}
