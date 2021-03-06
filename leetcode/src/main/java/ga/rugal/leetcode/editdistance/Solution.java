package ga.rugal.leetcode.editdistance;

/**
 * https://leetcode.com/problems/edit-distance/
 *
 * @author rugal
 */
public class Solution {

  public int minDistance(final String word1, final String word2) {

    final int[][] dp = new int[word1.length() + 1][word2.length() + 1];

    for (int i = 0; i <= word1.length(); i++) {
      dp[i][0] = i;
    }

    for (int j = 0; j <= word2.length(); j++) {
      dp[0][j] = j;
    }

    //iterate though, and check last char
    for (int i = 0; i < word1.length(); i++) {
      final char c1 = word1.charAt(i);
      for (int j = 0; j < word2.length(); j++) {
        final char c2 = word2.charAt(j);

        //if two chars equal
        if (c1 == c2) {
          //then the edit distance is the same as the one before
          dp[i + 1][j + 1] = dp[i][j];
          continue;
        }
        final int replace = dp[i][j] + 1;
        final int insert = dp[i][j + 1] + 1;
        final int delete = dp[i + 1][j] + 1;
        dp[i + 1][j + 1] = Integer.min(delete, Integer.min(insert, replace));
      }
    }
    return dp[word1.length()][word2.length()];
  }
}
