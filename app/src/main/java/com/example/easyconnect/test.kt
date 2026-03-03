package com.example.easyconnect

fun main() {
     val Test = "Tarun Sharma"
    val test1 =Test.split(" ", ignoreCase = false)
        test1.forEach{
         print(it.first())
    }
}