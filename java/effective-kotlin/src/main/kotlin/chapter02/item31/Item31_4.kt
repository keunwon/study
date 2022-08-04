package chapter02.item31

interface Car {

    /**
     * 자동차의 방향을 변경합니다.
     *
     * @param angel 바퀴 측의 각도를 지정합니다.
     * 라디안 단위로 지정하며, 0은 직진을 의미합니다.
     * pi/2는 오른쪽으로 최대한 돌렸을 경우,
     * -pi/2는 왼쪽으로 최대한 돌렸을 경우를 의미합니다.
     * 값은 (-pi/2, pi/2) 범위로 지정해야 합니다.
     */
    fun setWheelPosition(angel: Float)

    /**
     * 자동차의 속도가 0이 될 때까지 감소합니다.
     *
     * @param pressure 브레이크 페달을 사용하는 비율을 지정합니다.
     * 0-1 사이의 숫자를 지정합니다. 0은 브레이크를 사용하지 않는 경우,
     * 1은 브레이크를 최대한 사용하는 경우를 의미합니다.
     */
    fun setBreakPedal(pressure: Double)

    /**
     * 최대 속도까지 차량을 가속하빈다.
     *
     * @param pressure 가스 페달(가속 페달)을 사용하는 비율을 지정합니다.
     * 0-1 사이의 숫자를 지정합니다. 0은 가스 페달을 사용하지 않는 경우,
     * 1은 가스 페달을 최대한 사용하는 경우를 의미합니다.
     */
    fun setGasPedal(pressure: Double)
}
