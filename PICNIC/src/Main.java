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
            return checkValidPairs(seats)? 1: 0;
        }

        int sum = 0;
        for (Integer person: people) {
            int seat = freeSeats.iterator().next();
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

    private boolean checkValidPairs(int[] seats) {
        return checkPairedToMany(seats)
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
