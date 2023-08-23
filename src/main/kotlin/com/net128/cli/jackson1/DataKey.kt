package com.net128.cli.jackson1

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

data class DataKey (
        val keyPart1: KeyPart1,
        val keyPart2: KeyPart2,
        val keyPart3: KeyPart3
)

enum class KeyPart1 { high, low  }

data class KeyPart2 (
        val id: Int,
        val name: String
)

data class KeyPart3 (
        val value: Int
)

val module = SimpleModule()
        .addKeySerializer(DataKey::class.java, DataKeySerializer())
        .addKeyDeserializer(DataKey::class.java, DataKeyDeserializer())
val mapper = ObjectMapper().registerKotlinModule().registerModule(module)

class DataKeySerializer: JsonSerializer<DataKey>() {
    override fun serialize(value: DataKey?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen?.let { jGen ->
            value?.let {jGen.writeFieldName(mapper.writeValueAsString(it))
            } ?: jGen.writeNull()
        }
    }
}

class DataKeyDeserializer: KeyDeserializer() {
    override fun deserializeKey(key: String?, ctxt: DeserializationContext?): DataKey? {
        return key?.let { mapper.readValue(key) }
    }
}
