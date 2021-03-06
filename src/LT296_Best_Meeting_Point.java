import java.util.*;

/*
A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?

Math, Sort
Follow up: If there are obstacles ?
 */
public class LT296_Best_Meeting_Point {
    public int minTotalDistance(int[][] grid) {
	List<Integer> row = new ArrayList<Integer>();
	List<Integer> col = new ArrayList<Integer>();

	for (int i = 0; i < grid.length; i++)
	    for (int j = 0; j < grid[0].length; j++) {
		if (grid[i][j] == 1) {
		    row.add(i); // add location
		    col.add(j);
		}
	    }

	Collections.sort(row);
	Collections.sort(col);

	int sum = 0;
	for (Integer pos : row) {
	    sum += Math.abs(pos - row.get(row.size() / 2));
	}

	for (Integer pos : col) {
	    sum += Math.abs(pos - col.get(col.size() / 2));
	}

	return sum;
    }

    // Optimize to O(mn) without sorting.
    public int minTotalDistance1(int[][] grid) {
	int m = grid.length, n = grid[0].length;

	List<Integer> I = new ArrayList<Integer>();
	List<Integer> J = new ArrayList<Integer>();

	for (int i = 0; i < m; i++) {
	    for (int j = 0; j < n; j++) {
		if (grid[i][j] == 1) {
		    I.add(i);
		}
	    }
	}
	for (int j = 0; j < n; j++) {
	    for (int i = 0; i < m; i++) {
		if (grid[i][j] == 1) {
		    J.add(j);
		}
	    }
	}
	return minTotalDistance(I) + minTotalDistance(J);
    }

    // As long as you have different numbers of people on your left and on your right, moving a little to the side with more people decreases the sum of distances.
    // So to minimize it, you must have equally many people on your left and on your right.
    public int minTotalDistance(List<Integer> grid) {
	int i = 0, j = grid.size() - 1, sum = 0;

	while (i < j) {
	    sum += grid.get(j--) - grid.get(i++); // If odd, then distance = grid.get(right)-grid.get(left) stop until i==j. but no distance add. if off. the same.
	}
	return sum;
    }

}
