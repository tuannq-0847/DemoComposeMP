package org.example.project

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainVM : ViewModel() {
    init {
        println("MainVM initialized")
    }

    val mutableState = mutableStateOf<List<Response>>(emptyList())

    fun fetchTodo() {
        println("fetchTodo called")
        viewModelScope.launch {
            try {
                println("Fetching todos...")
                val response = RetrofitInstance.api().getTodo()
                println("Received ${response.size} todos")
                mutableState.value = response.map { value ->
                    val random = Random.nextInt(0, response.size - 1)
                    if (random % 2 == 0) value.copy(type = 0)
                    else value
                }
            } catch (e: Exception) {
                println("Error fetching todos: ${e.message}")
                e.printStackTrace()
                mutableState.value = emptyList()
            }
        }
    }
}