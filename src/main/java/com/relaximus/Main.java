import reactor.core.publisher.Flux;

import java.util.List;

import static java.util.stream.Gatherers.scan;
import static java.util.stream.Gatherers.windowFixed;

void main(String[] args) {
    System.out.println("Hello world!");

    var values = List.of(1, 2, 3, 4);

    // Reactor scan function
    Flux.fromIterable(values)
            .scan(10, Integer::sum)
            .subscribe(System.out::println);

    // JDK 22 scan function
    values.stream()
            .gather(scan(() -> 10, Integer::sum))
            .forEach(System.out::println);

    // Reactor buffer function
    Flux.fromIterable(values)
            .buffer(2)
            .subscribe(System.out::println);

    // JDK 22 widowFixed aka buffer
    values.stream()
            .gather(windowFixed(2))
            .forEach(System.out::println);
}