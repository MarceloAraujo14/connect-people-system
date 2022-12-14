package br.com.connectpeople.resume.usecase.executor;

public interface ExecutorChain<T> {

    T execute(T t);

}
