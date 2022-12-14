package br.com.connectpeople.resume.usecase.chain;

public interface ExecutorChain<T> {

    T execute(T t);

}
