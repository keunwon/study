class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        final var maxHealth = health;
        final var endTime = attacks[attacks.length - 1][0];
        var aIdx = 0;
        var duration = 0;

        for (var time = 1; time <= endTime; time++) {
            if (time == attacks[aIdx][0]) {
                duration = 0;
                health -= attacks[aIdx++][1];
                if (health <= 0) return -1;
            } else {
                ++duration;
                health += bandage[1];

                if (duration == bandage[0]) {
                    health += bandage[2];
                    duration = 0;
                }
                health = Math.min(health, maxHealth);
            }
        }
        return health;
    }
}