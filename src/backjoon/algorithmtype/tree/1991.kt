/*
    백준 1991
    바킹독 트리단원 연습문제
    https://www.acmicpc.net/problem/1991

    silver 1

    이진 트리를 입력받아, 전위 순회, 중위 순회, 후위 순회한 결과를 출력
    
    1 ≤ N ≤ 26 => 노드의 개수

*/
/*  
    전위 순회 => dfs
    중위 순회
    후위 순회 

    left, right 자식을 구분하여 저장해야함
    1. Heap처럼 배열에 저장
    - 0은 편의상 비워둠
    - Node*2 => 왼쪽 자식
    - Node*2 + 1 => 오른 자식

    2. Node(left, right)클래스를 생성하여 저장

    3. left, right를 따로 저장

    트리 저장 코드는 블로그를 조금씩 참고해가며 작성
    https://velog.io/@gandi0330/Java-%EB%B0%B1%EC%A4%80-%ED%8A%B8%EB%A6%AC-%EC%88%9C%ED%9A%8C-1991%EB%B2%88

    전위, 중위, 후위 코드는 혼자 작성

    #제출
    1. 성공
*/

import java.util.Stack
fun main(){
    Solution1991().solve()
}
class Solution1991 {
    data class Node(
        val data: String,
        var left: Node?,
        var right: Node?
    )

    private var head = Node("A", null, null)

    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val n = br.readLine().toInt()

        repeat(n) {
            br.readLine().split(' ').let {
                insertNode(
                    head, 
                    it[0],
                    if(it[1] == ".") null else it[1],
                    if(it[2] == ".") null else it[2]
                )
            }
        }
        val temp = Stack<String>()
        preOrder(head, temp)
        bw.write("${stackToString(temp)}\n")

        temp.clear()
        inOrder(head, temp)
        bw.write("${stackToString(temp)}\n")

        temp.clear()
        postOrder(head, temp)
        bw.write("${stackToString(temp)}\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun stackToString(stack: Stack<String>): String {
        val sb = StringBuilder()
        for(i in stack.toList()){
            sb.append(i)
        }
        return sb.toString()
    }

    //전위 순회, dfs
    private fun preOrder(head: Node, temp: Stack<String>) {
        temp.push(head.data)
        head.left?.let { preOrder(it, temp) } 
        head.right?.let { preOrder(it, temp) } 
    }

    //중위 순회
    private fun inOrder(head: Node, temp: Stack<String>) {
        head.left?.let { inOrder(it, temp) } 
        temp.push(head.data)
        head.right?.let { inOrder(it, temp) } 
    }

    //후위 순회
    private fun postOrder(head: Node, temp: Stack<String>) {
        head.left?.let { postOrder(it, temp) } 
        head.right?.let { postOrder(it, temp) } 
        temp.push(head.data)
    }

    private fun insertNode(node: Node, root: String, left: String?, right: String?) {
        when(node.data) {
            root -> {
                node.left = left?.let { Node(it, null, null) } ?: run { null }
                node.right = right?.let { Node(it, null, null) } ?: run { null }
            }

            else -> {
                if(node.left != null) insertNode(node.left!!, root, left, right)
                if(node.right != null) insertNode(node.right!!, root, left, right)
            }
        }
    }
}