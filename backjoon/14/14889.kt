//sliver2
//14-8
/*
    https://infodon.tistory.com/63
    참고도 이해못하네 ㅅㅂ

    내가 한 방식으로 정답은 나오는데 시간초과뜬다
*/
import java.io.*
import java.util.StringTokenizer
import kotlin.math.sign
var teamMin = Int.MAX_VALUE
fun main(args : Array<String>){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st : StringTokenizer
    
    val size = br.readLine().toInt()
    val status : Array<Array<Int>> = Array(size+1){Array(size){0}} 

    for(y in 0 until size){
        st = StringTokenizer(br.readLine())
        for(x in 0 until size){
            status[y][x] = st.nextToken().toInt()
        }
    }
    br.close()
    // team 에 가입하는 선수를 저장할 배열
    // val team : Array<Array<Int>> = Array(2) { Array(size/2){-1} }
    // dfs(0, 0, team, status, join, size)
    
    val join : Array<Boolean> = Array(size){false}
    dfs3(0, 0, size, join, status, bw)

    bw.write("$teamMin\n")

    bw.flush()
    bw.close()
    
}


private fun dfs3(depth : Int, count : Int, size : Int, join : Array<Boolean>, status : Array<Array<Int>>, bw : BufferedWriter){
    if(count == size/2){
        // print("finish : { ")
        // for((idx,i) in join.withIndex()){
        //     if(i){
        //         print("${idx+1} ")
        //     }
        // }
        // print("}\n")
        diff(size, join, status, bw)
        return
    }
    for(i in depth until size){
        if(join[i] == false){
            join[i] = true
            dfs3(depth + 1, count + 1, size, join, status, bw)
            join[i] = false
        }
    }
}
private fun diff(size : Int, join : Array<Boolean>, status : Array<Array<Int>>, bw : BufferedWriter){
    var start = 0
    var link = 0
    for(i in 0 until size-1){
        for(j in i+1 until size){
            //연달아 2개씩 있는거만 취급?
            // 12 / 23 / 34
            if(join[i]==true && join[j]==true){
                start += status[i][j]
                start += status[j][i]
            }else if(join[i]==false && join[j]==false){
                link += status[i][j]
                link += status[j][i]
            }
        }
    }
    val value = Math.abs(start - link)
    if(value == 0){ // 0이 최소값인지 어케알지
        bw.write("$value\n")
        bw.flush()
        bw.close()
        System.exit(0)
    }else if(value < teamMin){
        teamMin = value
    }
    
}

// 시간초과뜬다
var dfsCount : Int = 0
private fun dfs(
    team1Idx : Int,
    team2Idx : Int,
    team : Array<Array<Int>>,
    status : Array<Array<Int>>, 
    join : Array<Boolean>,
    size : Int
){
    if(team1Idx + team2Idx == size){ // 각 팀이 모두 채워지면 탐색 종료
        dfsCount++
        val status1 = getTeamStatus(team[0], status)
        val status2 = getTeamStatus(team[1], status)
        val value = Math.abs(status1-status2)
        if(value<teamMin){
            teamMin = value
        }
        // print("status($dfsCount) => $status1, $status2, $value, $teamMin\n")

        print("finish($dfsCount) =>\n    team1 : { ")
        for(i in team[0]){
            print("$i ")
        }
        print("}\n    team2 : { ")
        for(i in team[1]){
            print("$i ")
        }
        print("}\n")

        return 
    }
    // i 가 선수 번호가 될것이다
    for(i in 1 until size+1){
        if(join[i-1]){
            continue
        }
        if(team1Idx < size/2){ // 1팀에 선수가 부족하다면
            // 중복 제거를 위해 오름차순 정렬
            if(team1Idx != 0 && team[0][team1Idx-1] > i){
                continue
            }
            join[i-1] = true
            team[0][team1Idx] = i //1팀에 선수i 추가
                        
            dfs(team1Idx+1, team2Idx, team, status, join, size)
            
            join[i-1] = false
            team[0][team1Idx] = -1 //1팀에 선수i제거

        }
        else if(team2Idx < size/2){ // 2팀에 선수가 부족하다면
            // 중복 제거를 위해 오름차순 정렬
            if(team2Idx != 0 && team[1][team2Idx-1] > i){
                continue
            }
            join[i-1] = true
            team[1][team2Idx] = i //2팀에 선수i 추가
            
            dfs(team1Idx, team2Idx+1, team, status, join, size)
            
            join[i-1] = false
            team[1][team2Idx] = -1 //1팀에 선수i제거
            
        }
    }
}

 /*
    선수가 추가된다면?
    1. 0명 -> 1명 
    : 할거없다
    2. 1명 -> 2명
    : status(0) + [0][1] + [1][0]
    3. 2명 -> 3명
    : status + [0][2] + [1][2] + [2][0] + [2][1]

    기존인원 + 새로운인원 / 새로운인원 + 기존인원 의 가능한 조합을 다 더해줘야한다

    그냥 팀 다 정해지면 status 계산 한번에 하자
*/
private fun getTeamStatus(
    team : Array<Int>,
    status : Array<Array<Int>>
) : Int{
    var teamStatus = 0
    for(i in 0 until team.size){ 
        for(j in 0 until team.size){
            if(i == j){
                continue
            }
            // print("get [${team[i]-1}][${team[j]-1}] : ${status[team[i]-1][team[j]-1]}\n")
            teamStatus += status[team[i]-1][team[j]-1]
        }
    }
    return teamStatus
}
/*
     3 4 / 1 2
     [3][4] + [4][3] / [1][2] + [2][1]
     2 + 5 / 4 + 1
     7 / 6
*/
/*
    1. N을 입력받으면 N*N의 2차원 배열을 선언해서 능력치 표를 내려받는다
    2. 선수 목록은 0~N-1까지의 숫자다
    3. 한팀은 n/2명으로 구성

    dfs 구성
    1. 능력치 표
    2. 1팀 인원수, 2팀 인원수 가 아니라 포함된 선수 번호가 필요하겠는데
    3. x,y
    4. 각 팀

    x,y가 같을수는 없다 => 같은 사람이 팀을할수는 없으니
*/

