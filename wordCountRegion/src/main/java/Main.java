import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private int[] mWords;
    private int[] mPositions;
    private int[] mTargetFrequencies;

    public int solve(int[] words, int[] positions, int[] targetFrequencies) {
        int[] frequencies = new int[targetFrequencies.length];
        int countValid = 0;
        int minRegion = -1;
        int start = 0, end = 0;
        int targetCount = Arrays.stream(targetFrequencies).sum();

        if (targetCount == 0) return 0;

        int word;
        int currentRegion;
        while (end < words.length) {
            do {
                if (end >= words.length) {
                    return minRegion;
                }
                word = words[end];
                ++frequencies[word];
                if (frequencies[word] <= targetFrequencies[word]) ++countValid;
                ++end;
            } while (countValid < targetCount);

            while (countValid >= targetCount) {
                word = words[start];
                --frequencies[word];
                if (frequencies[word] < targetFrequencies[word]) --countValid;
                ++start;
            }
            currentRegion = positions[end - 1] - positions[start - 1] + 1;
            if (minRegion < 0 || currentRegion < minRegion) minRegion = currentRegion;
        }
        return minRegion;
    }

    public void inputOneCase(Scanner scanner) {
        int length = scanner.nextInt();
        mWords = new int[length];
        mPositions = new int[length];
        int wordCount = scanner.nextInt();
        mTargetFrequencies = new int[wordCount + 1];
        for (int i = 0; i < wordCount; i++) {
            mTargetFrequencies[i + 1] = scanner.nextInt();
        }
        for (int i = 0; i < length; i++) {
            mWords[i] = scanner.nextInt();
            mPositions[i] = scanner.nextInt();
        }
    }

    public int solve() {
        return solve(mWords, mPositions, mTargetFrequencies);
    }
}
