package com.example.myapplication.data.model.ui.carrito;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CarritoViewModel {
    private MutableLiveData<String> mText;

    public CarritoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Mostrar carrito");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
