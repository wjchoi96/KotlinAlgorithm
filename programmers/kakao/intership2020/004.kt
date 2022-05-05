/*
    카카오 인턴 2020 기출문제 4번

    경주로 건설
    level 3

    n*n 의 경주로 부지 

    0 : 빈칸
    1 : 벽

    출발(0, 0)
    도착(n-1, n-1)

    출발지부터 도착점까지 경주로를 건설

    상하좌우 인접한 두 빈칸을 연결하여 건설 가능
    벽이 있는칸은 건설 불가

    직선도로 : 인접한 두 빈칸을 상하 또는 좌우로 연결한 경주로
    코너 : 직선도로가 직각으로 만나는 지점

    직선도로 : 100원
    코너 : 500원

    최소비용을 리턴

    3<= n <= 25

    bfs를 진행하면서
    직각으로 연결되는 node로 연결되는경우 코너의 값 +
    직선으로 연결되는 node로 연결하는경우 직선도로 값 +

    직선도로 : start to dest 까지의 disit

    dfs + 백트래킹이 아닌

    bfs + dp 인건같다
*/

import java.util.Queue
import java.util.LinkedList
fun main(args : Array<String>){
    // val board = arrayOf(
    //     intArrayOf(0,0,0),
    //     intArrayOf(0,0,0),
    //     intArrayOf(0,0,0),
    // )

    // 직선 18개, 코너4개 => 3800
    val board = arrayOf(
        intArrayOf(0,0,0,0,0,0,0,1),
        intArrayOf(0,0,0,0,0,0,0,0),
        intArrayOf(0,0,0,0,0,1,0,0),
        intArrayOf(0,0,0,0,1,0,0,0),
        intArrayOf(0,0,0,1,0,0,0,1),
        intArrayOf(0,0,1,0,0,0,1,0),
        intArrayOf(0,1,0,0,0,1,0,0),
        intArrayOf(1,0,0,0,0,0,0,0)
    )

    // 직선 6, 코너3 => 2100
    // val board = arrayOf(
    //     intArrayOf(0,0,1,0),
    //     intArrayOf(0,0,0,0),
    //     intArrayOf(0,1,0,1),
    //     intArrayOf(1,0,0,0)
    // )

    // 직선 12개 코너 4개 => 3200
    // 직선 10개 코너 5개 => 3500
    // val board = arrayOf(
    //     intArrayOf(0,0,0,0,0,0),
    //     intArrayOf(0,1,1,1,1,0),
    //     intArrayOf(0,0,1,0,0,0),
    //     intArrayOf(1,0,0,1,0,1),
    //     intArrayOf(0,1,0,0,0,1),
    //     intArrayOf(0,0,0,0,0,0)
    // )

    Kakao004().solution(board)
}



// https://github.com/devxb/JJUNalgo/blob/master/programmers%20kakao%20%EA%B2%BD%EC%A3%BC%EB%A1%9C%20%EA%B1%B4%EC%84%A4/Main.java 
// 참고해서 dp+bfs로 한번 해결해보자
// 진짜 깔끔하다.. 많이 배운거같다
// PriorityQueue 를 사용하기위해 compareBy를 구현했던건가?
/*
    visit 없이 moneyDp 로 방문사실 체크
    방문사실 체크라기보단, 현재 방문하는 루트의 값이 더 싸다면 갱신해주고
    더 비싸다면 방문을 막아준다
*/
private class Kakao004 {
    private lateinit var board : Array<IntArray>
    private lateinit var moneyDp : Array<Array<Array<Int>>>
    private var n = 0

    fun solution(board: Array<IntArray>): Int {
        n = board.size
        this.board = board
        initMoneyDp()
        val money = bfs(Node(0, 0))
        print("$money\n")
        return bfs(Node(0, 0))
    }
    private fun initMoneyDp(){
        moneyDp = Array(n){Array(n){Array(4){Int.MAX_VALUE}}} // 처음엔 방문할수 있어야하니 최대값을 넣어준다
    }

    private fun bfs(start : Node) : Int {
        val queue : Queue<Node> = LinkedList()
        queue.offer(start)
        //여기서 money 갱신을 안해주는것은 시작점은 0원이기때문
        //칸에 도착할때(도로를 그때 건설) x,y,dir에 따라 가격이 정해진다
        while(!queue.isEmpty()){
            val newNode = queue.poll()
            // 벽이거나, 지금 진행루트가 더 비싼값이라면 진행하지않는다
            if(newNode.isOnWall(board) || newNode.isExpensiveCase(moneyDp)) continue
            for(dir in 0 until 4){
                val nextNode = newNode.move(dir)
                if(nextNode.isOutOfBounds(n)) continue
                queue.offer(nextNode)
            }
        }
        val endMoneyDp = moneyDp[n-1][n-1]
        // 종료점에 dir에 따라 각각 다른 값 중 최소값
        return Math.min(
            Math.min(endMoneyDp[0], endMoneyDp[1]),
            Math.min(endMoneyDp[2], endMoneyDp[3])
        )
    }


    class Node(
        private var x : Int,
        private var y : Int,
        private var dir : Int,
        private var money : Int
    ){
        constructor(x : Int, y : Int) : this(x, y, -1, 0)
        companion object {
            private val dx = arrayOf(1, 0, -1, 0)
            private val dy = arrayOf(0, 1, 0, -1)
            private const val corner = 500
            private const val road = 100
        }

        /*
            visit 없이 moneyDp 로 방문사실 체크
            방문사실 체크라기보단, 현재 방문하는 루트의 값이 더 싸다면 갱신해주고
            더 비싸다면 방문을 막아준다
        */
        fun isExpensiveCase(moneyDp : Array<Array<Array<Int>>>) : Boolean{
            if(this.dir == -1) return false
            if(this.money > moneyDp[this.x][this.y][this.dir]) return true 
            moneyDp[this.x][this.y][this.dir] = this.money
            return false
        }

        fun isOnWall(board : Array<IntArray>) : Boolean = board[this.x][this.y] == 1
        fun isOutOfBounds(bound : Int) : Boolean = (this.x<0 || this.x>=bound || this.y<0 || this.y>=bound)

        fun move(dir : Int) : Node = Node(this.moveX(dir), this.moveY(dir), dir, this.addMoney(dir))
        private fun moveX(dir : Int) : Int = this.x + Node.dx[dir]
        private fun moveY(dir : Int) : Int = this.y + Node.dy[dir]
        private fun addMoney(dir : Int) : Int {
            return if(this.dir == -1 || this.dir == dir){ // 온방향은 돌아갈 수 없으니, 같은방향으로 나아가는것만 직진
                this.money + Node.road
            }else{
                this.money + Node.road + Node.corner // 이건 문제 제대로 안읽으면 놓치겠다
            }
        }

        fun getX() : Int = this.x
        fun getY() : Int = this.y
        fun getMoney() : Int = this.money

    }

}

// ============== try 1 => 하다가 길을 잃어서 다시 해보려 한다 
private class Kakao004Try1 {
    private val dx = arrayOf(1, 0, -1, 0)
    private val dy = arrayOf(0, 1, 0, -1)
    private lateinit var board : Array<IntArray>
    private lateinit var disit : Array<Array<Int>>
    private lateinit var visit : Array<Array<Boolean>>
    private var n = 0
    private var minCost : Int = Int.MAX_VALUE
    private val roadCost = 100
    private val cornerCost = 500
    fun solution(board: Array<IntArray>): Int {
        n = board.size
        this.board = board
        disit = Array(n){Array(n){-1}}
        visit = Array(n){Array(n){false}}
        
        // bfs(RoadNode(0 to 0, -1))
        visit[0][0] = true
        dfs(RoadNode(0 to 0, -1), 0, 0)
        print("$minCost\n")

        return minCost
    }

    // dfs 는 미로찾기 등 순환해가 발생할 수 있는 구조에서는 빠져나오지 못하는 경우가 있다
    // https://programmers.co.kr/questions/29679 근데 dfs로 풀었는데
    // 이건 순환해를 벗어나기위한 조건을 하나 더 추가한듯
    private fun dfs(start : RoadNode, road : Int, corner : Int){
        val node = start.node
        if(node == n-1 to n-1){
            val cost = road * roadCost + corner * cornerCost
            print("finish => road : $road, corner : $corner, cost : $cost\n")
            minCost = Math.min(cost, minCost)
            return 
        }
        for(i in 0 until 4){
            val nx = node.first + dx[i]
            val ny = node.second + dy[i]
            if(nx<0 || nx>=n || ny<0 || ny>=n){
                continue
            }
            if(board[nx][ny] == 1 || visit[nx][ny]){
                continue
            }
            val buildRoad = RoadNode(nx to ny, i)
            var cornerCount = corner
            if(buildRoad.isCorner(start)){
                cornerCount++
            }

            visit[nx][ny] = true
            dfs(buildRoad, road + 1, cornerCount)
            visit[nx][ny] = false
        }
    }

    private class RoadNode (
        val node : Pair<Int, Int>,
        val way : Int
    ){
        var cornerCount = 0
        fun isCorner(other : RoadNode) : Boolean {
            return when(other.way){
                0 -> way%2 != 0
                1 -> way%2 == 0
                2 -> way%2 != 0
                3 -> way%2 == 0
                else -> false // 초기값 
            }
        }
    }

    // https://programmers.co.kr/questions/30355 => 3차원dp까지 사용
    // 위의 코드 https://github.com/devxb/JJUNalgo/blob/master/programmers%20kakao%20%EA%B2%BD%EC%A3%BC%EB%A1%9C%20%EA%B1%B4%EC%84%A4/Main.java
    private fun bfs(start : RoadNode){
        val queue : Queue<RoadNode> = LinkedList()

        queue.offer(start)
        disit[start.node.first][start.node.second] = 0
        // print("node[${start.node.first}][${start.node.second}]\n")

        while(!queue.isEmpty()){
            val roadNode = queue.poll()
            val node = roadNode.node
            if(node == n-1 to n-1){
                val cost = disit[node.first][node.second] * roadCost + roadNode.cornerCount * cornerCost
                print("finish[$cost] => road : ${disit[node.first][node.second]}, corner : ${roadNode.cornerCount}\n")
                minCost = Math.min(cost, minCost)
            }
            for(i in 0 until 4){
                val nx = node.first + dx[i]
                val ny = node.second + dy[i]
                if(nx<0 || nx>=n || ny<0 || ny>=n){
                    continue
                }
                if(board[nx][ny] == 1 || disit[nx][ny] != -1){
                    continue
                }
                val buildRoad = RoadNode(nx to ny, i).apply { cornerCount = roadNode.cornerCount }
                queue.offer(buildRoad)
                disit[nx][ny] = disit[node.first][node.second] + 1
                if(buildRoad.isCorner(roadNode)){
                    buildRoad.cornerCount++
                }
            }
        }
    }

    /*
        0 1 2   
        0 1 2
        0 1 2
        
        이전 node 가 down 방향으로 왔다
        그런데 이전노드로 돌아갈수는 없으니
        이전 node에 대응되는 way(top:down/right:left) 가 아니면 코너링이다
    */

}





private class Kakako003Study1 {
    val dx = intArrayOf(0, 0, 1, -1)
    val dy = intArrayOf(1, -1, 0, 0)
    data class Temp(val x: Int, val y: Int, val dir: Int, val sum: Int)
    fun solution(board: Array<IntArray>): Int {
        val pq = PriorityQueue<Temp>(compareBy { it.sum })
        val visited = Array(board.size) { Array(board.size) { Array(4) { 999999999 } } }

        visited[0][0][0] = 0
        pq.add(Temp(0, 0, 0, 0))
        visited[0][0][2] = 0
        pq.add(Temp(0, 0, 2, 0))

        while (!pq.isEmpty()) {
            val now = pq.poll()!!
            if (now.x == board.size - 1 && now.y == board.size - 1)
                return now.sum
            for (i in 0 until 4) {
                if (now.dir == 0 && i == 1) continue
                if (now.dir == 1 && i == 0) continue
                if (now.dir == 3 && i == 2) continue
                if (now.dir == 2 && i == 3) continue
                val nx = dx[i] + now.x
                val ny = dy[i] + now.y
                if (nx < 0 || ny < 0 || nx >= board.size || ny >= board.size)
                    continue
                val nSum = if (now.dir / 2 == i / 2) now.sum + 100 else now.sum + 500 + 100
                if (visited[ny][nx][i] <= nSum || board[ny][nx] == 1)
                    continue
                visited[ny][nx][i] = nSum
                pq.add(Temp(nx, ny, i, nSum))
            }
        }
        return 0
    }
}