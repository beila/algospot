import java.util.Arrays;

public class Main {
    public int solve(int[] words, int[] positions, int[] targetFrequencies) {
        int[] frequencies = new int[targetFrequencies.length];
        int countValid = 0;
        int minRegion = positions[positions.length - 1];
        int start = 0, end = 0;
        int targetCount = Arrays.stream(targetFrequencies).sum();

        if (targetCount == 0) return 0;

        int word;
        while (end < words.length) {
            do {
                if (end >= words.length) return -1;
                word = words[end];
                ++frequencies[word];
                if (frequencies[word] <= targetFrequencies[word]) ++countValid;
                ++end;
            } while (countValid < targetCount);

            while (countValid >= targetCount) {
                word = words[start];
                --frequencies[word];
                if (frequencies[word] <= targetFrequencies[word]) --countValid;
                ++start;
            }
            int currentRegion = positions[end - 1] - positions[start - 1] + 1;
            if (currentRegion < minRegion) minRegion = currentRegion;
        }
        return minRegion;
    }
}
