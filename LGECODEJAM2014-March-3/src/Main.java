import com.sun.istack.internal.NotNull;

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
    private final Set<SubMessage> subStrings = new HashSet<>();

    public void addMessages(String... messages) {
        Arrays.stream(messages).forEachOrdered(this::addMessage);
    }

    public void addMessage(String message) {
        inputMessages.add(message);
        SubMessage.createSubMessage(message).allSuffices()
                .forEach(subStrings::add);
    }

    public int[] essentialLength() {
        return inputMessages.stream().mapToInt(this::essentialLength).toArray();
    }

    public int essentialLength(String message) {
        return SubMessage.createSubMessage(message).allSuffices()
                .mapToInt(suffix ->
                                suffix.allPrefixes()
                                        .filter(ss -> !subStrings.contains(ss))
                                        .findFirst()
                                        .map(SubMessage::size).orElse(Integer.MAX_VALUE)
                )
                .min().orElse(Integer.MAX_VALUE);
    }

    interface SubMessage extends List<Character>{
        default Stream<SubMessage> allSuffices() {
            return IntStream.range(0, size())
                    .mapToObj(i -> subList(i, size()))
                    .map(subList -> (SubMessage) subList);
        }

        default Stream<SubMessage> allPrefixes() {
            return IntStream.range(1, size())
                    .mapToObj(i -> subList(0, i))
                    .map(subList -> (SubMessage) subList);
        }

        static SubMessage createSubMessage(String message) {
            return new LinkedSubMessage(message);
        }
    }

    static private class LinkedSubMessage extends AbstractSequentialList<Character> implements SubMessage {
        private final List<Character> characters;

        public LinkedSubMessage(List<Character> characters) {
            this.characters = characters;
        }

        public LinkedSubMessage(String message) {
            this(new LinkedList<Character>() {{
                for (Character c: message.toCharArray()) add(c);
            }});
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            LinkedSubMessage that = (LinkedSubMessage) o;

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

        @Override @NotNull
        public SubMessage subList(int fromIndex, int toIndex) {
            return new LinkedSubMessage(super.subList(fromIndex, toIndex));
        }

        @Override @NotNull
        public ListIterator<Character> listIterator(int index) {
            return characters.listIterator(index);
        }

        @Override
        public int size() {
            return characters.size();
        }

    }

}
