// Factory Pattern is part of creational pattern.It is used to create object without giving detail of instantiation logic.
// Use case: Create different type of user account and show their job role.
// Language used: Java

interface User {
    void displayRole();
}

class Admin implements User {
    @Override
    public void displayRole() {
        System.out.println("I am an Admin..");
    }
}

class Guest implements User {
    @Override
    public void displayRole() {
        System.out.println("I am a Guest..");
    }
}

class Member implements User {
    @Override
    public void displayRole() {
        System.out.println("I am a Member..");
    }
}

class UserFactory {
    public static User createUser(String userType) {
        switch (userType) {
            case "Admin":
                return new Admin();
            case "Guest":
                return new Guest();
            case "Member":
                return new Member();
            default:
                throw new IllegalArgumentException("Unknown user type!");
        }
    }
}

// Test Factory pattern
public class creational_1 {
    public static void main(String[] args) {
        User admin = UserFactory.createUser("Admin");
        User guest = UserFactory.createUser("Guest");
        User member = UserFactory.createUser("Member");
        // User manager = UserFactory.createUser("Manager"); //This will generate exception

        admin.displayRole();
        guest.displayRole();
        member.displayRole();
        //manager.displayRole();
    }
}

