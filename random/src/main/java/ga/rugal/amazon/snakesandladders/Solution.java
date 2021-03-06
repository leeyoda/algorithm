package ga.rugal.amazon.snakesandladders;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 *
 * @author rugalbernstein
 */
public class Solution {

  private int[][] board;

  public int snakesAndLadders(final int[][] board) {
    final int LENGTH = this.board.length * this.board.length;
    this.board = board;

    // both visited map and result cache
    // otherwise we have to have a SET to store the visited element
    // and use Pair object to store the known length
    final Map<Integer, Integer> result = new HashMap();
    result.put(1, 0);

    final Queue<Integer> queue = new LinkedList();
    queue.add(1);

    while (!queue.isEmpty()) {
      final int current = queue.poll();
      // because we use BFS, the the first occurence is the shortest
      if (current == LENGTH) {
        return result.get(current);
      }

      for (int i = current + 1; i <= Math.min(current + 6, LENGTH); ++i) {
        final int rc = get(i);
        final int r = rc / this.board.length;
        final int c = rc % this.board.length;
        // if -1, just move, otherwise move to the end of ladder or snake
        final int next = board[r][c] == -1 ? i : board[r][c];
        //if never visited before
        if (!result.containsKey(next)) {
          //cache the result till now, the number of steps to position "next"
          result.put(next, result.get(current) + 1);
          queue.add(next);
        }
      }
    }
    return -1;
  }

  /**
   * Given a square num s, return board coordinates (r, c) as r*N + c
   *
   * @param square
   *
   * @return
   */
  public int get(final int square) {
    final int quot = (square - 1) / this.board.length;
    final int rem = (square - 1) % this.board.length;
    final int row = this.board.length - 1 - quot;
    final int col = row % 2 != this.board.length % 2
                    ? rem // if
                    : this.board.length - 1 - rem;
    return row * this.board.length + col;
  }
}
