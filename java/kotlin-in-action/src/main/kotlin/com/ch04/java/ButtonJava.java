package com.ch04.java;

import com.ch04.State;
import com.ch04.View;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class ButtonJava implements View {

    @NotNull
    @Override
    public State getCurrentState() {
        return new ButtonState();
    }

    @Override
    public void restoreState(@NotNull State state) {}

    public class ButtonState implements State {}

    public String serialize() throws IOException {
        var button = new ButtonJava();
        byte[] byteArray;

        try (var byteOutput = new ByteArrayOutputStream();
            var objectOutput = new ObjectOutputStream(byteOutput)) {
            objectOutput.writeObject(button);
            byteArray = byteOutput.toByteArray();
        }
        return Base64.getEncoder().encodeToString(byteArray);
    }
}
