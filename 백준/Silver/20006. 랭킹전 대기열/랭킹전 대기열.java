import java.util.*;
import java.io.*;

public class Main {

    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken()); // 플레이어 수 300명
        m = Integer.parseInt(st.nextToken()); // 방의 정원 1~300명

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            Room r = null;
            for (Room room : rooms) {
                if (room.possible(l)) {
                    r = room;
                    break;
                }
            }
            if (r == null) {
                rooms.add(new Room(l, new String[]{Integer.toString(l), n}));
            } else {
                r.players.add(new String[]{Integer.toString(l), n});
            }
        }

        for (Room room : rooms) {
            if (room.players.size() == m) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }
            room.players.sort((a1, a2) -> a1[1].compareTo(a2[1]));
            for (String[] player : room.players) {
                System.out.println(player[0] + " " + player[1]);
            }
        }
    }

    static class Room {
        // {레벨, 닉네임}
        List<String[]> players = new ArrayList<>();

        int level;

        public Room(int level, String[] player) {
            this.level = level;
            this.players.add(player);
        }

        public boolean possible(int l) {
            return l >= level - 10 && l <= level + 10 && players.size() < m;
        }
    }
}
