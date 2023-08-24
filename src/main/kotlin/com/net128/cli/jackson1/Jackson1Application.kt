package com.net128.cli.jackson1

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Jackson1Application : CommandLineRunner {
    companion object {
        private val mapper: ObjectMapper = ObjectMapper().registerKotlinModule()
    }

    val dataContainer = DataContainer(mapOf(
        DataKey(KeyPart1.High, KeyPart2(1, "First"), KeyPart3(1234)) to 100,
        DataKey(KeyPart1.Low, KeyPart2(2, "Second"), KeyPart3(4567)) to 200
    ))

    override fun run(vararg args: String) {
        if(args.size==1 && args[0] == "1") {
            data class DataCell (val key: DataKey, val value: Int)
            val dataCells = dataContainer.dataMap.entries.map { e -> DataCell(e.key, e.value) }
            val jsonString = mapper.writeValueAsString(dataCells)
            println(jsonString)

            val dataCells2: List<DataCell> = mapper.readValue(jsonString)
            val map2 = dataCells2.map { it.key to it.value }.toMap()
            if (dataContainer.dataMap != map2) println("dataContainer.dataMap read value mismatch")
        } else {
            val jsonString = dataContainer.toJson()
            println(jsonString)

            val container = DataContainer(dataContainer.fromJson(jsonString))
            if (dataContainer != container) println("dataContainer read value mismatch")
        }
    }
}

fun main(args: Array<String>) {
	runApplication<Jackson1Application>(*args)
}

