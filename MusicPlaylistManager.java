import java.util.Scanner;

public class MusicPlaylistManager {

    static final int MAX = 50;

    static String[] songs = new String[MAX];
    static String[] artists = new String[MAX];

    // 2-D array: duration and number of plays
    static int[][] stats = new int[MAX][2];

    static int count = 0;

    // Method to add a song
    static void addSong(Scanner sc) {
        if (count >= MAX) {
            System.out.println("Playlist is full!");
            return;
        }

        System.out.print("Enter song name: ");
        songs[count] = sc.nextLine();

        System.out.print("Enter artist name: ");
        artists[count] = sc.nextLine();

        System.out.print("Enter duration in seconds: ");
        stats[count][0] = sc.nextInt();

        System.out.print("Enter number of plays: ");
        stats[count][1] = sc.nextInt();
        sc.nextLine();

        count++;

        System.out.println("Song added successfully!");
    }

    // Method to display all songs
    static void displayPlaylist() {
        if (count == 0) {
            System.out.println("Playlist is empty.");
            return;
        }

        System.out.println("\n--- Music Playlist ---");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + songs[i]
                    + " | Artist: " + artists[i]
                    + " | Duration: " + stats[i][0] + " sec"
                    + " | Plays: " + stats[i][1]);
        }
    }

    // Method to search for a song
    static void searchSong(Scanner sc) {
        System.out.print("Enter song name to search: ");
        String search = sc.nextLine();

        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (songs[i].equalsIgnoreCase(search)) {
                System.out.println("Song found: " + songs[i]);
                System.out.println("Artist: " + artists[i]);
                System.out.println("Duration: "
                        + stats[i][0] + " seconds");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Song not found.");
        }
    }

    // Recursive method to calculate total plays
    static int totalPlays(int index) {
        if (index == count) {
            return 0;
        }

        return stats[index][1] + totalPlays(index + 1);
    }

    // Method to calculate total playlist duration
    static int totalDuration() {
        int total = 0;

        for (int i = 0; i < count; i++) {
            total += stats[i][0];
        }

        return total;
    }

    // Method to record a new play
    static void playSong(Scanner sc) {
        System.out.print("Enter song name to play: ");
        String search = sc.nextLine();

        for (int i = 0; i < count; i++) {
            if (songs[i].equalsIgnoreCase(search)) {
                stats[i][1]++;

                System.out.println("Now playing: " + songs[i]);
                System.out.println("Total plays: "
                        + stats[i][1]);
                return;
            }
        }

        System.out.println("Song not found.");
    }

    // Method to display listening statistics
    static void showStatistics() {
        System.out.println("\n--- Listening Statistics ---");
        System.out.println("Total songs: " + count);
        System.out.println("Total playlist duration: "
                + totalDuration() + " seconds");
        System.out.println("Total number of plays: "
                + totalPlays(0));

        if (count > 0) {
            int mostPlayed = 0;

            for (int i = 1; i < count; i++) {
                if (stats[i][1] > stats[mostPlayed][1]) {
                    mostPlayed = i;
                }
            }

            System.out.println("Most played song: "
                    + songs[mostPlayed]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MUSIC PLAYLIST MANAGER =====");
            System.out.println("1. Add Song");
            System.out.println("2. Display Playlist");
            System.out.println("3. Search Song");
            System.out.println("4. Play Song");
            System.out.println("5. Listening Statistics");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addSong(sc);
                    break;

                case 2:
                    displayPlaylist();
                    break;

                case 3:
                    searchSong(sc);
                    break;

                case 4:
                    playSong(sc);
                    break;

                case 5:
                    showStatistics();
                    break;

                case 6:
                    System.out.println("Thank you for using "
                            + "Music Playlist Manager!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
