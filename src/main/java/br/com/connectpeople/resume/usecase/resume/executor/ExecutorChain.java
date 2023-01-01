package br.com.connectpeople.resume.usecase.resume.executor;

public interface ExecutorChain<T> {

    T execute(T t);

}
