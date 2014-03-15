import java.util.HashSet;
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
    }

    public int countPairingCases() {
        Set<Integer> people = new HashSet<>(numPeople);
        int[] seats = new int[numPeople];
        for (int i = 0; i < numPeople; i++) {
            people.add(i);
            seats[i] = -1;
        }
        return countPairingCases(people, seats);
    }

    private int countPairingCases(Set<Integer> people, int[] seats) {
        if (people.size() <= 1) {
            seats[0] = people.iterator().next();
            return isValidPairs(seats)? 1: 0;
        }

        int sum = 0;
        for (Integer p: people) {
            seats[people.size() - 1] = p;
            Set<Integer> lessPeople = new HashSet<>(people);
            lessPeople.remove(p);
            sum += countPairingCases(lessPeople, seats);
        }
        return sum;
    }

    private boolean isValidPairs(int[] seats) {
        return checkPairedToMyself(seats)
                && checkPairedToMany(seats)
                && checkPairOfNonFriends(seats);
    }

    private boolean checkPairedToMany(int[] seats) {
        for (int i = 0; i < seats.length; i++) {
            if (i != seats[seats[i]]) return false;
        }
        return true;
    }

    private boolean checkPairOfNonFriends(int[] seats) {
        for (int i = 0; i < seats.length; i++) {
            if (!areFriends[i][seats[i]]) return false;
        }
        return true;
    }

    private boolean checkPairedToMyself(int[] seats) {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == i) return false;
        }
        return true;
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

}
