/*
    바킹독님 알고리즘 강의 중 다이나믹 프로그래밍 단원

    Dynamic porogramming: 다이나믹 프로그래밍, DP
    - 여러개의 하위 문제를 먼저 푼 후 그 결과를 쌓아 올려 주어진 문제를 해결하는 알고리즘
    - 앞 글자를 따서 dp라고 부른다
    - 문제를 해결하기 위한 점화식을 찾아낸 후 점화식의 항을 밑에서부터 차례로 구해나가서 답을 알아내는 형태

    예)
    - 파보나치 문제의 경우 재귀적으로 구하면 중복된 연산이 계속 발생하여 O(1.618^n)의 시간이 걸린다
    - dp로 해결하면, 미리 배열을 만들어 두고 0번째 파보나치 결과를 저장해두면 O(n)에 답을 알아낼 수 있다

    DP를 푸는 과정
    1. 테이블 정의하기
    2. 점화식 찾기
    3. 초기값 정하기

    연습문제
    1. 백준 1463(solve)
    - bfs로 풀수도 있다곤 한다(solve)
    
    2. 백준 9095(solve)

    3. 백준 2579(solve)

*/