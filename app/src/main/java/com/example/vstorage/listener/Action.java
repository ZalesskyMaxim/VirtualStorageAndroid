package com.example.vstorage.listener;

public interface Action<M> {
    default void onSuccess(M model){}
    default void onError(String error){}
}
