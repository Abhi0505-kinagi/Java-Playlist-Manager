import java.io.File;
import java.util.Scanner;
import javax.sound.sampled.*;

class Songs {
    String filepath;
    Songs next;
    Songs prev;

    Songs(String filepath) {
        this.filepath = filepath;
        this.next = null;
        this.prev = null;
    }
}

public class songplayer {

    // Add song to end of list
    public static Songs addSongs(Songs root, String filepath) {
        Songs newsong = new Songs(filepath);
        if (root == null) {
            return newsong;
        }
        Songs temp = root;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newsong;
        newsong.prev = temp;
        return root;
    }

    // Delete first song
    public static Songs deleteFirstSong(Songs root) {
        if (root == null) {
            System.out.println("No Songs to delete‼️🆘");
            return null;
        }
        root = root.next;
        if (root != null) {
            root.prev = null;
        }
        System.out.println("First song deleted successfully ✅");
        return root;
    }

    // Delete last song
    public static Songs deleteLastSong(Songs root) {
        if (root == null) {
            System.out.println("No Songs to delete‼️🆘");
            return null;
        }
        Songs temp = root;
        if (temp.next == null) {
            return null; // only one song
        }
        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        System.out.println("Last song deleted successfully ✅");
        return root;
    }

    // Play all songs in the playlist
    public static void startPlayer(Songs root) {
        Songs temp = root;
        while (temp != null) {
            try {
                System.out.println("🎶Now Playing: " + temp.filepath);

                File file = new File(temp.filepath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                // wait until song finishes
                Thread.sleep(clip.getMicrosecondLength() / 1000);

                temp = temp.next; // go to next song
            } catch (Exception e) {
                e.printStackTrace();
                temp = temp.next; // skip to next if error
            }
        }
        System.out.println("🎶 Playlist finished!");
    }
    public static void displayPlaylist(Songs root){
        if(root==null){
            System.out.println("playlist is empty");
            return;
        }
        Songs temp=root;
        int i=0;
        while(temp!=null){
            i++;
            System.out.println("Song"+i+"-->"+root.filepath);
            temp=temp.next;
        }
    }

    public static void main(String[] args) {
        Songs Root = null;
        Scanner sr = new Scanner(System.in);
        String filepath;
        int choice;

        System.out.println("==== 🎵 Playlist Menu 🎵 ====");
        do {
            System.out.println("\nEnter choice:");
            System.out.println("1) Add Song to playlist");
            System.out.println("2) Start Player");
            System.out.println("3) Display Playlist");
            System.out.println("4) Delete First Song");
            System.out.println("5) Delete Last Song");
            System.out.println("6) Exit");

            choice = sr.nextInt();
            sr.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the song file path:");
                    filepath = sr.nextLine();
                    Root = addSongs(Root, filepath);
                }
                case 2 -> startPlayer(Root);
                case 3 -> displayPlaylist(Root);
                case 4 -> Root = deleteFirstSong(Root);
                case 5 -> Root = deleteLastSong(Root);
                case 6 -> System.out.println("👋 Exiting playlist...");
                default -> System.out.println("⚠️ Invalid choice, try again!");
            }
        } while (choice != 6);

        sr.close();
 
    
    }
}
