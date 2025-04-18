package algorithm.programmers

/**
 * <p>
 * 이름: 유연근무제
 * 난이도: Level-1
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/388351">유연근무제 (Level-1)</a>
 **/
class Lesson388351 {
    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
        for (i in schedules.indices) {
            schedules[i] += 10
            if (schedules[i] % 100 >= 60) {
                schedules[i] += 100
                schedules[i] -= 60
            }
        }

        var result = 0
        for ((i, timelog) in timelogs.withIndex()) {
            var flag = true
            for (day in startday until startday + 7) {
                if (day == 6 || day == 7 || day == 13) continue
                if (schedules[i] < timelog[day - startday]) {
                    flag = false
                    break
                }
            }
            if (flag) ++result
        }
        return result
    }
}
