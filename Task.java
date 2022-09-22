import java.util.LinkedList;
import java.util.Queue;

public class Task {

    static void print_map(int[][] map) {
        System.out.println();
        System.out.printf("    ");
        for (int k = 0; k < map.length; k++) {
            System.out.printf(" %2d ", k);
        }
        System.out.println();
        System.out.println("    _______________________________________");

        for (int i = 0; i < map.length; i++) {
            System.out.printf(" %2d|", i);

            for (int j = 0; j < map[0].length; j++) {
                System.out.printf(" %2d ", map[i][j]);
            }
            System.out.println();
        }
    }

    static int[][] created_map(int start_row, int start_col, int end_row, int end_col) {
        int[][] map = new int[10][10];
        map[1][4] = -1;
        map[1][5] = -1;
        map[1][6] = -1;
        map[3][5] = -1;
        map[4][5] = -1;
        map[5][5] = -1;
        map[6][3] = -1;
        map[3][3] = -1;
        map[4][3] = -1;
        map[5][3] = -1;
        map[2][5] = -1;
        map[start_row][start_col] = -2;
        map[end_row][end_col] = -3;
        return map;
    }

    static void cell_filling(Queue<String> queue, int[][] map) {
        var cell = queue.remove().split(",");
        int cell_row = Integer.parseInt(cell[0]);
        int cell_col = Integer.parseInt(cell[1]);
        int cell_number = Integer.parseInt(cell[2]);

        if (cell_row > 0) {
            if (map[cell_row - 1][cell_col] == 0 | map[cell_row - 1][cell_col] == -3) {
                map[cell_row - 1][cell_col] = cell_number + 1;
                queue.add(String.format("%d,%d,%d", cell_row - 1, cell_col, cell_number + 1));
            }
        }
        if (cell_col < 9) {
            if (map[cell_row][cell_col + 1] == 0 | map[cell_row][cell_col + 1] == -3) {
                map[cell_row][cell_col + 1] = cell_number + 1;
                queue.add(String.format("%d,%d,%d", cell_row, cell_col + 1, cell_number + 1));
            }
        }
        if (cell_row < 9) {
            if (map[cell_row + 1][cell_col] == 0 | map[cell_row + 1][cell_col] == -3) {
                map[cell_row + 1][cell_col] = cell_number + 1;
                queue.add(String.format("%d,%d,%d", cell_row + 1, cell_col, cell_number + 1));
            }
        }
        if (cell_col > 0) {
            if (map[cell_row][cell_col - 1] == 0 | map[cell_row][cell_col - 1] == -3) {
                map[cell_row][cell_col - 1] = cell_number + 1;
                queue.add(String.format("%d,%d,%d", cell_row, cell_col - 1, cell_number + 1));
            }
        }
    }

    static void search_way(Queue<String> queue, int[][] map, int end_row, int end_col) {
        var cell = queue.remove().split(",");
        int cell_row = Integer.parseInt(cell[0]);
        int cell_col = Integer.parseInt(cell[1]);
        int cell_number = Integer.parseInt(cell[2]);
        if (cell_number == -2) {
            map[cell_row][cell_col] = map[end_row][end_col];
            return;
        }
        if (cell_row > 0) {
            if (map[cell_row - 1][cell_col] == cell_number - 1 | map[cell_row - 1][cell_col] == -2) {
                map[cell_row][cell_col] = map[end_row][end_col];
                queue.add(String.format("%d,%d,%d", cell_row - 1, cell_col, map[cell_row - 1][cell_col]));
                return;
            }
        }
        if (cell_col < 9) {
            if (map[cell_row][cell_col + 1] == cell_number - 1 | map[cell_row][cell_col + 1] == -2) {
                map[cell_row][cell_col] = map[end_row][end_col];
                queue.add(String.format("%d,%d,%d", cell_row, cell_col + 1, map[cell_row][cell_col + 1]));
                return;
            }
        }
        if (cell_row < 9) {
            if (map[cell_row + 1][cell_col] == cell_number - 1 | map[cell_row + 1][cell_col] == -2) {
                map[cell_row][cell_col] = map[end_row][end_col];
                queue.add(String.format("%d,%d,%d", cell_row + 1, cell_col, map[cell_row + 1][cell_col]));
                return;
            }
        }
        if (cell_col > 0) {
            if (map[cell_row][cell_col - 1] == cell_number - 1 | map[cell_row][cell_col - 1] == -2) {
                map[cell_row][cell_col] = map[end_row][end_col];
                queue.add(String.format("%d,%d,%d", cell_row, cell_col - 1, map[cell_row][cell_col - 1]));
                return;
            }
        }
    }

    public static void main(String[] args) {
        int start_row = 2;
        int start_col = 1;
        int end_row = 8;
        int end_col = 8;
        int[][] map = created_map(start_row, start_col, end_row, end_col);
        print_map(map);
        System.out.println();

        Queue<String> queue = new LinkedList<String>();
        queue.add(String.format("%d,%d,%d", start_row, start_col, 0));

        while (map[end_row][end_col] == -3) {
            cell_filling(queue, map);
        }
        print_map(map);
        queue.clear();
        queue.add(String.format("%d,%d,%d", end_row, end_col, map[end_row][end_col]));

        while (map[start_row][start_col] == -2) {
            search_way(queue, map, end_row, end_col);
        }
        print_map(map);

    }

}