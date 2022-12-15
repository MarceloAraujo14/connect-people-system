package br.com.connectpeople.resume.usecase.executor;

public class Executor<T> {

    private final T t;

    public Executor(T t) {
        this.t = t;
    }

    public Executor<T> chain(ExecutorChain<T> chain) {
        chain.execute(this.t);
        return this;
    }

}
