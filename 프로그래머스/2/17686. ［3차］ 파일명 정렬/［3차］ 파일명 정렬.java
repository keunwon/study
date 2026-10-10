import java.util.Arrays;
import java.util.regex.Pattern;

class Solution {
    public String[] solution(String[] files) {
        return Arrays.stream(files)
                .map(Filename::new)
                .sorted()
                .map(Filename::getOrigin)
                .toArray(String[]::new);
    }

    private static class Filename implements Comparable<Filename> {
        private static final Pattern NAME_PATTERN = Pattern.compile("(\\D+)(\\d+)");

        String origin;
        String head;
        Integer number;

        public Filename(String origin) {
            var matcher = NAME_PATTERN.matcher(origin.toLowerCase());
            if (matcher.find()) {
                this.origin = origin;
                this.head = matcher.group(1);
                this.number = Integer.parseInt(matcher.group(2));
            }
        }

        public String getOrigin() {
            return origin;
        }

        @Override
        public int compareTo(Filename o) {
            if (!head.equals(o.head)) {
                return head.compareTo(o.head);
            } else if (!number.equals(o.number)) {
                return number.compareTo(o.number);
            }
            return 0;
        }
    }
}