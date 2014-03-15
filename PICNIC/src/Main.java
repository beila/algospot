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
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == i) return false;
            if (!areFriends[i][seats[i]]) return false;
        }
        return true;
    }

}
