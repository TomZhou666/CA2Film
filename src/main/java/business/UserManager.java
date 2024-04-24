package business;

import java.util.HashMap;

public class UserManager {
    // DATA STORAGE
    // HashMap to store user objects
    private final HashMap<String, User> users = new HashMap<>();

    //CONSTRUCTOR
    public UserManager(){

    }

    /*Method to get username and add user to HashMap
       mapping username as key and user as value
      @Param: Object user
      @Return: FALSE if user or username is Null & user already Exist OR TRUE when user add to map
     */
    public boolean register(User user){
        synchronized (users){
            if(user == null){
                System.out.println("User OR username can not be NUll. ");
                return false;
            }
            if(users.get(user.getUsername() )!= null){
                System.out.println("User already exist!");
                return false;
            }
            users.put(user.getUsername(), user);
            return true;
        }
    }

    /*
        Method to return key username from hashmap
        @Param: A string username to search/get
        @Return: Returns Null if map is empty or username doesn't exist AND return key username from map if found
     */
    public User getUserByUsername(String username){
        synchronized (users){
            if(username == null){
                System.out.println("User OR username can not be NUll. ");
                return null;
            }
            return  users.get(username);
        }
    }
}
