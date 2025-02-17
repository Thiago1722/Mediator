import java.util.ArrayList;
import java.util.List;

interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

class ChatMediatorImpl implements ChatMediator {
    private List<User> users;

    public ChatMediatorImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (!u.equals(user)) {
                u.receive(message);
            }
        }
    }
}

abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

class ConcreteUser extends User {

    public ConcreteUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " enviando mensagem: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " recebeu mensagem: " + message);
    }
}

    class ChatApplication {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediatorImpl();

        User user1 = new ConcreteUser(chatMediator, "RENATO");
        User user2 = new ConcreteUser(chatMediator, "MIGUEL");
        User user3 = new ConcreteUser(chatMediator, "COSME");

        chatMediator.addUser(user1);
        chatMediator.addUser(user2);
        chatMediator.addUser(user3);

        user1.send("Olá pessoal!");
        user2.send("Oi RENATO!");
    }
}
