// Observer pattern is an example of behavioral design pattern. It is used to notify multiple object about state change
// Use case considered: When we send update about new blog release to user
// Language used: Java

import java.util.ArrayList;
import java.util.List;

class Blog {
    private List<Observer> observers = new ArrayList<>();
    private String blogPost;

    public void subscribe(Observer observer) {
        observers.add(observer);
        System.out.println(observer+ " Subscribed..!");
    }

    public void unsubscribe(Observer observer) {
        observers.remove(observer);
        System.out.println(observer+ " Unsubscribed..!");
    }

    public void publishPost(String post) {
        this.blogPost = post;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(blogPost);
        }
    }
}

interface Observer {
    void update(String blogPost);
}

class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String blogPost) {
        System.out.println(name + " notified of new post: " + blogPost);
    }
}

// Test Observer pattern
public class behavioral_1 {
    public static void main(String[] args) {
        Blog blog = new Blog();
        User user1 = new User("Param");
        User user2 = new User("Kinjal");

        blog.subscribe(user1);
        blog.subscribe(user2);

        blog.publishPost("New Blog Post on Chess olympiad!");

        blog.unsubscribe(user2);

        blog.publishPost("New Blog Post regarding current market trends..");

        User user3 = new User("Rahul");

        blog.subscribe(user3);

        blog.publishPost("Blog regarding upcoming technologies..");
    }
}
