import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Long, Long> rooms;

    public long[] solution(long k, long[] room_number) {
        this.rooms = new HashMap<>();
        var res = new long[room_number.length];

        for (var i = 0; i < room_number.length; i++) {
            res[i] = findRoom(room_number[i]);
        }
        return res;
    }

    private long findRoom(long roomNum) {
        if (!rooms.containsKey(roomNum)) {
            rooms.put(roomNum, roomNum + 1);
            return roomNum;
        }

        var findRoom = findRoom(rooms.get(roomNum));
        rooms.put(roomNum, findRoom);
        return findRoom;
    }
}