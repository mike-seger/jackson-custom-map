package com.net128.cli.jackson1

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.fasterxml.jackson.module.kotlin.readValue

@SpringBootApplication
class Jackson1Application : CommandLineRunner {
    override fun run(vararg args: String) {
        val map = mapOf(
                DataKey(KeyPart1.high, KeyPart2(1, "First"), KeyPart3(1234)) to 100,
                DataKey(KeyPart1.low, KeyPart2(2, "Second"), KeyPart3(4567)) to 200
        )

        val jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map)
        println(jsonString)

        val map2: Map<DataKey, Int> = mapper.readValue(jsonString/*, mapType*/)
        if(map != map2) println("read value mismatch")
    }
}

fun main(args: Array<String>) {
	runApplication<Jackson1Application>(*args)
}

