import java.util.ArrayList;
import java.util.List;

// problem statement: Create a simple implementation of the Observer pattern where
//  a YouTube channel notifies its subscribers whenever a new video is uploaded.
// subsribers can be notified using different methods like Email,youtube bell icon and sms and so on.

interface YoutubeChannel{
    void addSubscriber(Subscriber subscriber);
    void removeSubscriber(Subscriber subscriber);
    void notifySubscriber(String videoTitle);
}
interface Subscriber{
    void update(String videoTitle);
}

class YoutubeChannelImpl implements YoutubeChannel{
    private  List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void addSubscriber(Subscriber sub){
        subscribers.add(sub);
    }
    @Override
    public void removeSubscriber(Subscriber sub){
        subscribers.remove(sub);
    }
    @Override
    public void notifySubscriber(String videoTitle){
        for(Subscriber sub: subscribers){
            sub.update(videoTitle);
        }
    }
    public void uploadVideo(String videoTitle){
        System.out.println("New video uploaded :: Notifying all users");
        notifySubscriber(videoTitle);
    }
}

class Youtube_Subscribers implements Subscriber{
    String userName;
    public Youtube_Subscribers(String name){ 
        this.userName=name;
      }
    @Override
    public void update(String videoTitle){
        System.out.println("Notifying user "+userName +" using bell icon youtube");
    }
}
class Email_Subscribers implements Subscriber{
    String userName;
    public Email_Subscribers(String name){  
        this.userName=name;
     }
    @Override
    public void update(String videoTitle){
        System.out.println("Notifying user "+userName+" using email");
    }
}
public class Youtube_Subscribers_Notification {
    public static void main(String[] args){
        YoutubeChannelImpl youtubeChannel = new YoutubeChannelImpl();
        Subscriber sub1= new Youtube_Subscribers("Nicky");
        Subscriber sub2=new Email_Subscribers("Ashish");
       youtubeChannel.addSubscriber(sub1);
       youtubeChannel.addSubscriber(sub2);
       youtubeChannel.uploadVideo("First Vlog");
       youtubeChannel.removeSubscriber(sub2);
       youtubeChannel.uploadVideo("Second Vlog");
    }
}
