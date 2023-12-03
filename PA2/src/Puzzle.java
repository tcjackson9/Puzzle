public class Puzzle {
    private Grid grid;

    public Puzzle(Grid grid) {
    	this.grid = grid;
    }
    
    public String find(String word, int r, int c) {
    	if (word == null || word.isEmpty()) {
    		return "";
    	}
		word = word.toUpperCase();
		Deque<Loc> queue = new Deque<>();
		boolean[][] visited = new boolean[grid.size()][grid.size()];
		queue.addToBack(new Loc(r, c, grid.getVal(r,  c)));
		visited[r][c] = true;
		while (!queue.isEmpty()) {

			Loc currentLoc = null;
			try {
				currentLoc = queue.getFirst();
			}
			catch (EmptyDequeException e) {
				e.printStackTrace();
			}
			if (currentLoc.getVal().equals(word.substring(0, 1))) {
				String path = depthFirstSearch(word, currentLoc, visited);
				if (!path.isEmpty()) {
					return path;
				}
			}
			for (int[] direction: new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}) {
				int newRow = currentLoc.row + direction[0];
				int newCol = currentLoc.col + direction[1];
				if (isValidLocation(newRow, newCol) && !(visited[newRow][newCol] == true)) {
					String val = grid.getVal(newRow, newCol);
					if (!val.isEmpty()) {
						queue.addToBack(new Loc(newRow, newCol, val));
					}
				}
			}
		}
		return "";
    }
    private String depthFirstSearch(String word, Loc currentLoc, boolean[][] visited) {
        if (word.isEmpty()) {
            return "";
        }
        if (word.length() == 1 && word.equals(currentLoc.getVal())) {
            return currentLoc.toString();
        }
        
        visited[currentLoc.row][currentLoc.col] = true;

        for (int[] direction : new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}) {
            int newRow = currentLoc.row + direction[0];
            int newCol = currentLoc.col + direction[1];
            if (isValidLocation(newRow, newCol) && !visited[newRow][newCol]) {
                String nextChar = word.substring(1);
                String value = grid.getVal(newRow, newCol);
                if (value.equals(nextChar.substring(0, 1))) {
                    String path = depthFirstSearch(nextChar, new Loc(newRow, newCol, value), visited);
                    if (!path.isEmpty()) {
                        return currentLoc.toString() + path;
                    }
                }
            }
        }

        visited[currentLoc.row][currentLoc.col] = false;
        return "";
    }
    private boolean isValidLocation(int row, int col) {
    	int gridSize = grid.size();
    	return row >= 0 && row < gridSize && col >= 0 && col < gridSize;
    }
}
    
