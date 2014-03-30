import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * http://www.lgecodejam.com/codejam/dashboard
 * http://www.lgecodejam.com/codejam/problem/download/pdf/5/kr
 * Created by hojin.ghim on 3/29/14.
 */
public class Main {
    private final List<String> inputMessages = new ArrayList<>(1000);
    private final List<Set<SubMessage>> subMessages = new ArrayList<Set<SubMessage>>(26) {{
        for (int i = 0; i < 26; i++) {
            add(new HashSet<>());
        }
    }};

    public void addMessages(String... messages) {
        Arrays.stream(messages).forEachOrdered(this::addMessage);
    }

    public void addMessage(String message) {
        inputMessages.add(message);
        SubMessage.allSuffices(message)
                .forEach(l -> subMessages.get(l.get(0) - 'a').add(l));
    }

    public int[] essentialLength() {
        return inputMessages.stream().mapToInt(this::essentialLength).toArray();
    }

    public int essentialLength(String message) {
        return SubMessage.allSuffices(message)
                .map(suffix ->
                                suffix.allPrefixes()
                                        .filter(subMessage -> !subMessages.get(subMessage.get(0) - 'a').contains(subMessage))
                                        .findFirst()
                                        .map(SubMessage::size).orElse(Integer.MAX_VALUE)
                )
                .min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }

    interface SubMessage extends List<Character>{
        static Stream<SubMessage> allSuffices(String message) {
            SubMessage sm = new SubMessageImpl(message);
            return IntStream.range(0, message.length())
                    .mapToObj(i -> sm.subList(i, sm.size()))
                    .map(subList -> (SubMessage) subList);
        }

        default Stream<SubMessage> allPrefixes() {
            return IntStream.range(0, size())
                    .mapToObj(i -> subList(0, i))
                    .map(subList -> (SubMessage) subList);
        }
    }

    static private class SubMessageImpl extends LinkedList<Character> implements SubMessage {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            SubMessageImpl that = (SubMessageImpl) o;

            if (size() != that.size()) return false;

            for (int i = 0; i < size(); i++) {
                if (!get(i).equals(that.get(i))) return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            return stream()
                    .mapToInt(value -> (int) value)
                    .reduce(super.hashCode(), (left, right) -> 31 * left + right);
        }

        public SubMessageImpl(String message) {
            for(Character c: message.toCharArray()) add(c);
        }

    }

}
