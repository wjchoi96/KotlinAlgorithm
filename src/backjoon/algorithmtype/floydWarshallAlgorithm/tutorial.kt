/*
    바킹독님 알고리즘 강의 중 플로이드-워셜 알고리즘 단원
    https://blog.encrypted.gg/1035

    최단경로 알고리즘

    #플로이드 알고리즘이란
    그래프에서 모든 정점 쌍 사이의 최단거리를 구해주는 알고리즘
    - 그래프가 방향이건 무방향이건 상관없음
    - 간선의 값이 음수여도 동작하지만, 음수인 사이클이 있으면 문제가 발생(흔한 상황은 아님)

    #플로이드 알고리즘의 구현
    일반적인 인접행렬 방식으로 다른 정점을 거쳐가지 않을때의 최단거리 테이블을 작성 가능

    해당 테이블에서 s에서 t정점으로 갈때, 1번 정점을 거쳐간다면
    - D[s][1] + D[1][t]가 최단거리
    - s에서 1까지 최단경로로 가고, 1에서 t까지 최단경로로 진행했을때의 값
    - D[s][t]보다 D[s][1] + D[1][t]가 작을 경우(=직접가는것보다, 1번정점을 거쳐가는게 효율적인 경우)
    에 D[s][t]를 갱신해주면 됨

    해당 테이블에서 s에서 t정점으로 갈때, 2번 정점을 거친다면?
    - 위와 비슷

    본래 테이블에서 각 정점을 거쳐서 갈때의 최단거리로 차츰차츰 업데이트 해주면 됨
    => 정점이 V개라면 총 V단계에 거쳐서 갱신이 이루어짐
    => 각 단계마다 총 V^2개의 모든 D[s][t]값을 D[s][k] + D[k][t]값과 비교
    => 플로이드 알고리즘의 총 시간복잡도는 O(V^3)
    => 삼중 for문

    #연습문제
    1. 백준 11404(solve)

*/