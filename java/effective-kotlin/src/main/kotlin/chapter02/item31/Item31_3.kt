package chapter02.item31

/**
 * 트리 자료 구조 (immutable)
 *
 * 1개 이상의 요소를 갖는 트리를 나타내는 클래스입니다 (immutable).
 * 트리의 노드는 요소를 가질 수 있으며,
 * 또한 왼쪽과 오른쪽 서브 트리를 가질 수 있습니다.
 *
 * @param T 트리가 갖는 요소의 타입을 지정합니다.
 * @property value 트리의 현재 노드에 할당할 값을 의미합니다.
 * @property left 왼쪽 서브 트리를 의미합니다.
 * @property right 오른쪽 서브 트리를 의미합니다.
 */
class Tree<T>(
    val value: T,
    val left: Tree<T>? = null,
    val right: Tree<T>? = null
) {
    /**
     * 현재 트리를 기반으로 [element]를 추가한
     * 새로운 트리를 만듭니다.
     *
     * @return 새로운 요소를 추가해서 새로 만든 트리를 의미합니다.
     */
    operator fun plus(element: T): Tree<T> {
        return Tree(element, null, null)
    }
}

fun main() {
    val tree = Tree("", null, null)
    tree.plus("")
}
