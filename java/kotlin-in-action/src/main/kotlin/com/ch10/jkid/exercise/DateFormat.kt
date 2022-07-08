package com.ch10.jkid.exercise

@Target(AnnotationTarget.PROPERTY)
annotation class DateFormat(val format: String = "yyyy-MM-dd")
