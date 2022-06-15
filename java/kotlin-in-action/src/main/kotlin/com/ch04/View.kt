package com.ch04

import java.io.Serializable

interface State : Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

class ButtonKt : View {
    override fun getCurrentState(): State = ButtonState()

    override fun restoreState(state: State) {}

    class ButtonState : State {}
}