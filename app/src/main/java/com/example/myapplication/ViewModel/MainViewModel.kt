package com.example.myapplication.ViewModel

import androidx.lifecycle.ViewModel
import com.example.myapplication.Repository.MainRepository

class MainViewModel(val repository:MainRepository): ViewModel() {
    constructor():this(MainRepository())

    fun loadData() = repository.items
}