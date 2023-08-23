package com.net128.cli.jackson1

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Jackson1Application : CommandLineRunner {
    override fun run(vararg args: String) {
        val dataContainer = DataContainer(mapOf(
            DataKey(KeyPart1.High, KeyPart2(1, "First"), KeyPart3(1234)) to 100,
            DataKey(KeyPart1.Low, KeyPart2(2, "Second"), KeyPart3(4567)) to 200
        ))

        val jsonString = dataContainer.toJson()
        println(jsonString)

        val conainer2 = DataContainer(dataContainer.fromJson(jsonString))
        if(dataContainer != conainer2) println("read value mismatch")
    }
}

fun main(args: Array<String>) {
	runApplication<Jackson1Application>(*args)
}

