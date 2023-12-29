import java.util.Scanner;

public class DiamondTreasure {

    private static char[][] maze = {

        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
        {'#', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#', '#', '£', 'C', '#', ' ', ' ', ' ', '#', '#'},
        {'#', ' ', '#', ' ', '£', '#', '#', ' ', '#', '#', ' ', ' ', '#', ' ', 'T', ' ', '#', '#'},
        {'#', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '£', '#', ' ', 'C', ' ', '#', '#'},
        {'#', ' ', '#', '#', ' ', '#', ' ', 'C', ' ', '#', ' ', ' ', '#', ' ', ' ', ' ', '#', '#'},
        {'#', ' ', ' ', ' ', ' ', '#', ' ', 'T', ' ', '#', '#', ' ', '#', ' ', '#', '#', '#', '#'},
        {'#', ' ', '#', '#', '#', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', '#', '#', ' ', '#'},
        {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
        {'#', ' ', '#', 'C', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
        {'#', ' ', '#', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', '#', ' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#'},
        {'#', ' ', '#', '#', '#', ' ', '#', 'T', ' ', ' ', '#', ' ', ' ', '#', '#', '#', ' ', '#'},
        {'#', ' ', '#', '#', '#', ' ', '#', ' ', 'C', ' ', '#', ' ', '#', '#', ' ', ' ', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', '#'},
        {'#', '#', ' ', '#', '#', '#', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#'},
        {'#', '#', ' ', '#', ' ', ' ', ' ', '£', ' ', ' ', '#', ' ', ' ', '#', ' ', '#', ' ', '#'},
        {'#', ' ', ' ', ' ', ' ', '£', ' ', ' ', ' ', '£', '#', ' ', '#', '#', ' ', '#', '£', '#'},
        {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', '£', ' ', '#', '#', '#'},
        {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', ' ', ' ', '#', '#'},
        {'#', ' ', '#', '#', '#', '#', '#', '#', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', '#'},
        {'#', ' ', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
        {'#', ' ', '#', '#', ' ', '#', '#', 'C', ' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', '#'},
        {'#', ' ', '#', '#', ' ', '#', '#', '#', ' ', '#', '#', '#', '#', ' ', '#', ' ', '#', '#'},
        {'#', 'C', '#', '#', ' ', '#', '#', '#', ' ', '#', ' ', ' ', ' ', ' ', '#', ' ', '#', '#'},
        {'#', '#', '#', '#', ' ', '#', ' ', ' ', ' ', '#', '#', '#', '#', '#', '#', ' ', '#', '#'},
        {'#', '#', ' ', ' ', ' ', '#', ' ', '£', '#', '#', ' ', ' ', ' ', ' ', ' ', ' ', '#', '#'},
        {'#', '#', ' ', '#', '#', '#', ' ', ' ', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', ' ', '#', '#', '#', '£', 'T', '#', '#', ' ', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', ' ', '#', '#', '#', '#', '#', '#', '#', ' ', ' ', ' ', ' ', '£', ' ', 'T', '#'},
        {'#', '#', ' ', ' ', ' ', '£', ' ', ' ', ' ', ' ', ' ', ' ', '£', ' ', ' ', ' ', 'C', '#'},
        {'#', '#', ' ', '£', ' ', ' ', ' ', '£', 'C', ' ', '£', '#', '#', '#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},

    };

    private static int playerX;
    private static int playerY;
    private static int treasures = 0; // Jumlah harta karun yang telah dikumpulkan
    private static int totalTreasures = 5; // Total harta karun yang harus dikumpulkan
    private static int energy = 250;

    // method untuk memindahkan pemain
    private static void movePlayer(int moveX, int moveY) {
        int newX = playerX + moveX;
        int newY = playerY + moveY;

        if (isValidMove(newX, newY)) {
            char newPosition = maze[newX][newY];

            // Karakter mengambil koin dan energi bertambah
            if (newPosition == 'C') {
                maze[newX][newY] = ' ';
                energy += 8;
                System.out.println("Anda mendapatkan koin! Energi +8");
            }
            // Karakter mengambil dan mengumpulkan harta karun
            else if (newPosition == 'T') {
                treasures++;
                System.out.println("Anda telah mengumpulkan harta karun sebanyak : " + treasures);

                if (treasures == totalTreasures) {
                    System.out.println("Selamat kamu menang dan berhasil keluar!");
                    System.exit(0); // Pemain keluar dan memenangkan permainan
                }
            }
            // Energi berkurang jika karakter menginjak ular
            else if (newPosition == '£') {
                energy -= 5;
                System.out.println("Anda terkena ular! Energi -5");
            }
            // Hapus dan update posisi karakter
            maze[playerX][playerY] = ' ';
            playerX = newX;
            playerY = newY;
            maze[playerX][playerY] = 'M';

            energy--; // Setiap gerakan mengurangi energi
            if (energy <= 0) {
                System.out.println("Energi habis. Permainan berakhir.");
                System.exit(0);
            }
        } else {
            System.out.println("Tidak bisa bergerak ke sana!");
        }
    }

    // Agar karakter tidak bisa melewati batas labirin
    private static boolean isValidMove(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != '#';
    }

    // Metode untuk mengatur posisi awal karakter manusia
    private static void setPlayerPosition(int x, int y) {
        playerX = x;
        playerY = y;
        maze[playerX][playerY] = 'M'; // Inisialisasi posisi karakter manusia di labirin
    }

    // Metode untuk menampilkan labirin
    private static void printMaze() {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inisialisasi posisi karakter manusia dan labirin lainnya
        setPlayerPosition(1, 1); // Contoh: karakter manusia ditempatkan di koordinat (1, 1)

        // Memulai permainan dan loop utama
        boolean program = true;
        char input;
        do {
            printMaze();

            System.out.println("Energi: " + energy);
            System.out.println("Total treasure : " + treasures);
            System.out.print("Masukkan perintah (w/a/s/d untuk gerak, q untuk keluar): ");
            input = scanner.next().charAt(0);

            switch (input) {
                case 'w':
                    movePlayer(-1, 0); // Bergerak ke atas
                    break;
                case 'a':
                    movePlayer(0, -1); // Bergerak ke kiri
                    break;
                case 's':
                    movePlayer(1, 0); // Bergerak ke bawah
                    break;
                case 'd':
                    movePlayer(0, 1); // Bergerak ke kanan
                    break;
                case 'q':
                    if (treasures == totalTreasures) {
                        System.out.println("Keluar permainan.");
                        program = false;
                    } else {
                        System.out.println("Harta karun (T) belum terkumpul, anda tidak bisa keluar permainan!");
                    }
                    break;
                default:
                    System.out.println("Perintah tidak valid!");
                    break;
            }

        } while (program);

        scanner.close();
    }
}
