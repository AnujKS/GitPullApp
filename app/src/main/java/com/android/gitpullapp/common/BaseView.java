package com.android.gitpullapp.common;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void setError();
}