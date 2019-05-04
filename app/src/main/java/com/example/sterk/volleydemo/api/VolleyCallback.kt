package com.example.sterk.volleydemo.api

import com.example.sterk.volleydemo.model.Pokemon

/**
 * Created by sterk on 5/5/2019.
 */
public interface VolleyCallback<T>{
     fun onSuccess(list:T)


}