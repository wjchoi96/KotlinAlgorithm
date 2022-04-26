//gold5
/*
    네이버 카페 문제50선 중 백트래킹, 완전탐색 유형

    치킨 배달

    N * M 도시
    빈칸, 치킨집, 집 중 하나
    r행 c열, 1부터 시작

    치킨거리 : 집과 가장 가까운 치킨집 사이의 거리
    치킨거리는 집을 기준으로 정해진다
    각각의 집은 치킨거리를 가지고 있다

    도시의 치킨거리 : 모든집의 치킨거리의 합

    (r1, c1) <-> (r2, c2) 의 거리 => |r1-r2| + |c1-c2|

    0 : 빈칸
    1 : 집
    2 : 치킨집

    해당 도시에서 치킨집 M개를 제외하고 모두 없앤다
    해당 작업을 실시했을때, 도시의 치킨거리의 최소값을 구하라
    => 도시의 치킨거리가 최소가 되도록 M개의 치킨집을 제외하고 모두 없애라

    2 <= N <= 50
    1 <= M <= 13
    
    1 <= 집 <= 2N
    M <= 치킨집 <= 13
    
    1초
*/

/*
    본래 치킨집 크기 c
    크기가c인 배열에서 크기가c-m 인 수열을 뽑고, 해당 수열의 치킨거리가 최소가 되는경우를 구하는것?
    - 같은 조합 + 다른 순서의 수열의 경우의 수는 뺄 수 있게 설정

    1. m개 제거
    2. 현 상태에서 치킨거리 구하기
    3. 반복
*/  

private lateinit var board : Array<Array<Int>>
private lateinit var chickens : Array<Pair<Int, Int>>
private var chickensCount = 0
private var m = 0 // 페업시키지 않고 살려둘 치킨집
private var n = 0
private fun dfs(x : Int = 0, y : Int = 0, depth : Int){
    if(depth == chickensCount - m){ 
        val value = getChickenRoad() // 치킨거리 구하기
        // 최소값 갱신
        return
    }
    for(nx in x until n){
        for(ny in y until n){
            if(board[nx][ny] == 2){ // 치킨집이라면
                board[nx][ny] = 0 // 폐업 시킨다
                chickensCount--
                dfs(nx, ny+1, depth + 1) 
                board[nx][ny] = 2 // 다시 살려줌
                chickensCount++
            }
        }
    }
}
// 거리를 구할떄는 x,y 에 +1씩 해줘야하나?
// 어차피 차이를 구하니까 필요없을듯

private fun getChickenRoad(){
    var road = 0
    for(x in 0 until n){
        for(y in 0 until n){
            // 이거 아니다. home 을 찾은다음, home 에서 치킨거리를 구해야한다(집에서 가장 가까운 거리의 치킨집과의 거리 => 치킨거리)
            if(board[x][y] == 1){ // 집 발견
                
            }
        }
    }
}
private fun getLength(p1 : Pair<Int, Int>, p2 : Pair<Int, Int>) : Int {
    return Math.abs(p1.first - p2.first) + Math.abs(p1.second - p2.second)
}