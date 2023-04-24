# NyaEvent
A simple and easy-to-use open source event bus

# Example
```java
public class TestClass implements EventListenerOwner {

    private final NyaEventBus EVENT_BUS = new NyaEventBus();

    public static void main(String[] args) {
        new TestClass().init();
    }

    public void init() {
        EVENT_BUS.registerListener(this);
        EVENT_BUS.postEvent(new ExampleEvent(0));
    }

    @EventHandler
    public void onEvent(ExampleEvent event) {
        System.out.println(event);
    }

}

class ExampleEvent extends Event implements Cancellable {

    public ExampleEvent(int stage) {
        super("Example",stage);
    }

    boolean cancel = false;

    @Override
    public boolean isCanceled() {
        return cancel;
    }

    @Override
    public void cancel() {
        cancel = true;
    }
}
```
