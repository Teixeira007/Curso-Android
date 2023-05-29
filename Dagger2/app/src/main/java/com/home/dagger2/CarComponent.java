package com.home.dagger2;

import dagger.Component;

@Component
public interface CarComponent {

//    Car getCar();

    void inject(MainActivity mainActivity);
}
