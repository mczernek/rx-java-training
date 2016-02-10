package pl.mczernek;

import rx.Observable;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        query().
                flatMap(obs -> Observable.from(obs))
                .flatMap(s -> Observable.from(s.split(" ")))
                .filter(s -> s.length() < 2)
                .doOnNext(s -> System.out.println("postPreparing"))
                .doOnNext(s -> System.out.println("Preparing"))
                .doOnCompleted(() -> System.out.println("Completed"))
                .take(5).subscribe(s->System.out.println("Hello "+s));
    }

    public static Observable<List<String>> query(){
        final int observablesCount = 10;
        List<String> observables = new ArrayList<>(observablesCount);
        for(int i=0; i<observablesCount; ++i){
            observables.add("Result " + i);
        }
        return Observable.just(observables);
    }

}
