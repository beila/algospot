import java.util.*;
import java.util.function.*;
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
                                suffix.allPrefices()
                                        .filter(new Predicate<SubMessage>() {
                                            @Override
                                            public boolean test(SubMessage subMessage) {

                                            }
                                        })
                                        .findFirst()
                                        .map(SubMessage::size).orElse(Integer.MAX_VALUE)
                )
                .min(Integer::compareTo).orElse(Integer.MAX_VALUE);
    }

    static Stream<Character> toCharacterStream(String string) {
        List<Character> characterList = new ArrayList<>(string.length());
        for (int i = 0; i < string.length(); i++) {
            characterList.add(string.charAt(i));
        }
        return characterList.stream();
    }

    interface SubMessage extends List<Character>{
        static Stream<SubMessage> allSuffices(String message) {
            SubMessage sm = new SubMessageImpl(message);
            List<SubMessage> list = new ArrayList<>(sm.size());
            for (int i = 0; i < sm.size(); i++) {
                list.add((SubMessage) sm.subList(i, sm.size()));
            }
            return list.stream();
        }

        default Stream<SubMessage> allPrefices() {
            List<SubMessage> list = new ArrayList<>(size());
            for (int i = 1; i <= size(); i++) {
                list.add((SubMessage) subList(0, i));
            }
            return list.stream();
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
                if (get(i).equals(that.get(i))) return false;
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
