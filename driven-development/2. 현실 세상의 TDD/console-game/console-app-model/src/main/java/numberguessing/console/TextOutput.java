package numberguessing.console;

final public class TextOutput {

    private final StringBuffer outputBuffer;

    public TextOutput(String message) {
        outputBuffer = new StringBuffer(message);
    }

    public String flushOutput() {
        final var output = outputBuffer.toString();
        outputBuffer.setLength(0);
        return output;
    }

    public void printLines(String... lines) {
        outputBuffer.append(String.join(System.lineSeparator(), lines));
    }
}
