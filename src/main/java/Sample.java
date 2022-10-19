
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public class Sample {
    public static void main(String[] args) {
        Mono.justOrEmpty(Optional.empty())
                .defaultIfEmpty(2)
                .switchIfEmpty(Mono.defer(()-> Mono.error(new Exception())))
                .doOnSuccess(System.out::println)
                .subscribe();

        Mono.justOrEmpty(null)
                .defaultIfEmpty(2)
                .map( n->(Integer) n * (Integer) n)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new Exception())))
                .doOnSuccess(System.out::println)
                .subscribe();


        Mono.justOrEmpty(Optional.of(3))
                .filter( a -> (Integer) a % 2 == 0)
                .defaultIfEmpty(6)
                .doOnSuccess((n)-> System.out.println("Saida 3: "+n))
                .subscribe();

        Mono.justOrEmpty(Optional.of(2))
                .defaultIfEmpty(1)
                .filter( a -> (Integer) a % 2 == 0)
                .defaultIfEmpty(6)
                .doOnSuccess((n)-> System.out.println("Saida 4: "+n))
                .subscribe();

        Flux.fromIterable(List.of(1,2,3,4,8,9))
                .doOnNext(System.out::println)
                .subscribe();

        Flux.fromIterable(List.of(1,2,3,4,8,9))
                .map(n -> n * 3)
                .doOnNext(System.out::println)
                .subscribe();

        Flux.fromIterable(List.of(1,2,3,4,8,9))
                .map(n -> n * 3)
                .collectList()
                .doOnNext(System.out::println)
                .subscribe();

        Flux.fromIterable(List.of(1,2,3,4,8,9))
                .map(n -> n * 3)
                .filter( a -> (Integer) a % 2 == 0)
                .collectList()
                .doOnNext(System.out::println)
                .subscribe();

        Flux.fromIterable(List.of(1,2,3,4,8,9))
                .map(n -> n * 3)
                .doOnNext(x -> System.out.println( "vezes 3: " + x ))
                .filter( a -> (Integer) a % 2 == 0)
//                .collectList()
                .doOnNext(System.out::println)
                .subscribe();

        Flux.fromIterable(List.of(1,2,3,4,8,9))
                .map(n -> n * 3)
                .doOnNext(x -> System.out.println( "vezes 3: " + x ))
                .filter( a -> (Integer) a % 2 == 0)
                .collectList()
                .doOnNext(System.out::println)
                .subscribe();
    }
}
