package com.supplement.v1

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface ConfigService {
    fun getConfig(section: String): String
}

class RealConfigService(val addr: String): ConfigService {
    override fun getConfig(section: String): String = "${addr}에서 읽은 JSON"
}

class MockConfigService(val addr: String) : ConfigService {
    override fun getConfig(section: String): String = "테스트 JSON"
}

class ConfigServiceDelegateProvider(server: String, port: Int = 8080, debug: Boolean = false) {
    private val service: ConfigService =
        if (debug) MockConfigService("$server:$port")
        else RealConfigService("$server:$port")

    operator fun provideDelegate(remoteConfig: RemoteConfig, property: KProperty<*>): ReadOnlyProperty<RemoteConfig, String> {
        if (property.name == "tomcat") {
            return ReadOnlyProperty { _, prop -> service.getConfig(prop.name) }
        }
        else if (property.name == "httpd" || property.name == "apache") {
            return ReadOnlyProperty { _, prop -> service.getConfig(prop.name) }
        }
        throw IllegalArgumentException("prop name should be tomcat or httpd or apache")
    }
}

class RemoteConfig(private val debug: Boolean) {
    val tomcat by ConfigServiceDelegateProvider("111.111.11.1", debug = this.debug)
    val httpd by ConfigServiceDelegateProvider("111.111.11.1", debug = this.debug)
}

fun main() {
    val debugRemoteConfig = RemoteConfig(true)
    println(debugRemoteConfig.tomcat)
    println(debugRemoteConfig.httpd)

    val realRemoteConfig = RemoteConfig(false)
    println(realRemoteConfig.tomcat)
    println(realRemoteConfig.httpd)
}
