import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * http://algospot.com/judge/problem/read/PICNIC
 * Created by hojin.ghim on 3/9/14.
 */
public class Main {
    boolean[][] areFriends;
    int numPeople;
    public Main(boolean[][] areFriends) {
        this.areFriends = areFriends;
        this.numPeople = areFriends.length;
        for (int i = 0; i < areFriends.length; i++) {
            areFriends[i][i] = false;
        }
    }

    public int countPairingCases() {
        Set<Integer> people = new HashSet<>(numPeople);
        Set<Integer> freeSeats = new HashSet<>(numPeople);
        int[] seats = new int[numPeople];
        for (int i = 0; i < numPeople; i++) {
            people.add(i);
            freeSeats.add(i);
            seats[i] = -1;
        }
        return countPairingCases(people, freeSeats, seats);
    }

    private int countPairingCases(Set<Integer> people, Set<Integer> freeSeats, int[] seats) {
        if (people.size() <= 0) {
            return 1;
        }

        int sum = 0;
        int seat = freeSeats.iterator().next();
        for (Integer person: people) {
            if (!areFriends[person][seat]) continue;

            seats[seat] = person;
            seats[person] = seat;

            Set<Integer> lessPeople = new HashSet<>(people);
            lessPeople.remove(person);
            lessPeople.remove(seat);

            Set<Integer> lessFreeSeats = new HashSet<>(freeSeats);
            lessFreeSeats.remove(person);
            lessFreeSeats.remove(seat);

            sum += countPairingCases(lessPeople, lessFreeSeats, seats);
        }
        return sum;
    }

    @SuppressWarnings("UnusedDeclaration")
    private void printHighlighted(int[] seats, int highlight) {
        StringBuilder sb = new StringBuilder();
        String pad;
        for (int i = 0; i < seats.length; ++i) {
            sb.delete(0, sb.length());
            for (int j = 0; j < seats.length; ++j) {
                pad = highlight == i && j == seats[i] ? "*" : " ";
                sb.append(pad).append(j == seats[i]? "P": ".").append(pad);
            }
            System.out.println(sb.toString());
        }
        for (int i = 0; i < areFriends.length; ++i) {
            sb.delete(0, sb.length());
            for (int j = 0; j < areFriends[i].length; ++j) {
                pad = highlight == i && j == seats[i] ? "*" : " ";
                sb.append(pad).append(areFriends[i][j]? "T": "F").append(pad);
            }
            System.out.println(sb.toString());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        main(System.in, System.out);
    }

    static void main(InputStream inputStream, PrintStream printStream) {
        Scanner scanner = new Scanner(inputStream);
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            int students = scanner.nextInt();
            boolean[][] friends = new boolean[students][students];
            for (boolean[] ba: friends) {
                Arrays.fill(ba, false);
            }

            int pairs = scanner.nextInt();
            for (int j = 0; j < pairs; j++) {
                int friend1 = scanner.nextInt();
                int friend2 = scanner.nextInt();
                friends[friend1][friend2] = true;
                friends[friend2][friend1] = true;
            }

            Main main = new Main(friends);
            printStream.println(main.countPairingCases());
        }
    }

}
