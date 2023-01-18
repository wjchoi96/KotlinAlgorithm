/*
    백준 16434
    백준 이분탐색 유형 문제
    https://www.acmicpc.net/problem/16434

    gold 4

    용사의 능력
    - H_MaxHP: 최대 생명력. 1 이상이며 던전에 들어오 ㄴ이후 변하지 않음
    - H_CurHP: 현재 생명력. 던전에 들어가기 전에는 H_MaxHP와 같음
               이 값은 H_MaxHP보다 커질 수 없음
    - H_ATK: 공격력

    던전
    - N개의 방으로 이루어짐
    - i번째 방을 통해서만 i+1번째 방으로 이동 가능
    - 방에는 포션 혹은 몬스터가 존재. 
        - 몬스터가 있는 경우, 몬스터를 쓰러트려야 다음방으로 이동 가능
    - N번째 방에는 공주와 용이 있음
        - 용을 무찌르면 공주를 구할 수 이씅ㅁ

    전투
    1. 용사는 공격력 H_ATK만큼 몬스터의 생명력을 깎음
    2. 몬스터의 생명력이 0이하면, 전투가 종료되고 용사는 다음 방으로 이동
    3. 몬스터의 공격력만큼 용사의 H_CurHP를 깎음
    4. 용사의 생명력이 0이하이면 전투는 종료되고 용사는 사망
    5. 다시 1부터 진행

    포션
    - 포션을 마셔서 H_CurHP가 일정량 회복되고, H_ATK도 일정량 증가
    - 회복된 생명력이 H_MaxHP보다 큰 경우, 용사의 H_CurHP가 H_MaxHP와 같아짐
        - 최대생명력을 초과해서 회복될 수 없음
    
    N번방에 있는 용을 쓰러트리기 위한 최소의 H_MaxHP를 구하시오

    1 <= N <= 123,456
    1 <= H_ATK <= 1,000,000
*/
/*
    필요한 객체
    용사
    - hpMax
    - hpCurrent
    - atk

    필요한 함수
    - 전투
    - 포션
*/
/*
    제출
    1. 틀렸습니다(2%)
    - 범위 산정이 까다로운 상태

    2. 틀렸습니다(2%)
    - battle 코드 수정

    3. 틀렸습니다(2%)
    
*/

fun main(){
    Solution16434().solve()
}
class Solution16434 {
    private val BATTLE = 1
    private val PORTION = 2
    fun solve(){
        val bw = System.out.bufferedWriter()
        val br = System.`in`.bufferedReader()

        val (n, atk) = br.readLine().split(' ').map{ it.toInt() }
        val dungeon = Array(n){ Array(3) { 0 } }
        repeat(n){
            dungeon[it] = br.readLine().split(' ').map{ it.toInt() }.toTypedArray()
        }

        var start: Long = 0
        var end: Long = Math.pow(10.0, 12.0).toLong()

        while(start < end){
            val mid: Long = ((start + end) / 2.toLong())
            val user = User(mid, mid, atk)
            val result = startDungeon(dungeon, user)
            println("start[$start], mid[$mid], end[$end] => result[$result]")
            when(result) {
                true -> end = mid // 공략에 성공했다면, mid값을 낮춤
                else -> start = mid + 1 // 공략에 실패했다면 mid값을 올림
            }
        }
        println("finish => start[$start], end[$end]")
        bw.write("$start\n")
    
        bw.flush()
        bw.close()
        br.close()
    }

    private fun startDungeon(dungeon: Array<Array<Int>>, user: User): Boolean{
        var dungeonResult = true
        for(room in dungeon){
            when(room[0]) {
                BATTLE -> {
                    dungeonResult = user.battleMonster(room[1], room[2])
                    if(!dungeonResult) break
                }
                PORTION -> user.drinkPortion(room[1], room[2])
            }
        }
        return dungeonResult
    }

    data class User (
        val hpMax: Long,
        var hpCur: Long,
        var atk: Int
    ) {
        fun drinkPortion(portionAtk: Int, portionHp: Int) {
            val hp = hpCur + portionHp.toLong()
            hpCur = if(hp > hpMax) hpMax else hp
            atk = atk + portionAtk
        }

        fun battleMonster(monsterAtk: Int, monsterHp: Int): Boolean {
            val userAtkCount = if(monsterHp % atk == 0) monsterHp / atk else monsterHp / atk + 1
            val monsterAtkCount = if(hpCur % monsterAtk.toLong() == 0.toLong()) hpCur / monsterAtk.toLong() else hpCur / monsterAtk.toLong() + 1

            return when {
                userAtkCount > monsterAtkCount -> false 
                else -> {
                    hpCur -= monsterAtk * (userAtkCount-1)
                    true
                }
            }
        }
    }
}