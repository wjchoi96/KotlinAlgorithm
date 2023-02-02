/*
    바킹독님 알고리즘 강의 중 다익스트라 알고리즘 단원
    https://blog.encrypted.gg/1037
    
    하나의 시작점으로부터, 다른 모든 정점까지의 최단거리 산출
    모든 정점 쌍에 대해 최단거리를 구할 수 있는 플로이드와는 달리, 하나의 정점으로부터 다른 모든 정점까지의 최단거리를 산출
    - 음수 가중치를 가지는 간선이 있으면 사용할 수 없음
    - 음수 가중치의 경우, 밸만포드 알고리즘이 존재하지만, 대회를 염두에 두지 않는다면 필요없음
    - A*알고리즘이라는, 근사 알고리즘이 존재
        - 정점의 개수가 너무 많아 다익스트라 알고리즘을 사용하기 힘들 경우 유용하게 사용 가능한 근사 알고리즘
    - 거리가 확정되지 않은 정점들 중에서 가장 가까운 정점을 찾아 확정하는 그리디 알고리즘
        - 떄문에 음수 간선을 처리하지 못함
        - 음수간선이 존재하면, 현재 갈 수 있는 정점 중에서 가장 가까운 정점까지의 거리를 확정할 수 없음


    #구현
    - 매 단계마다 도달할 수 있는 정점 중에서, 시작점으로부터의 거리가 가장 가까운 정점을 구해서
    그 거리를 확정하는 방식으로 동작

    1. 무작위 정점을 선택
    2. 해당 정점과 연결된 정점중, 비용이 가장 적은 정점과의 거리를 table에 적용

    3. 거리가 확정된 정점과 연결된 정점 중, 비용이 가장 적은 정점과의 거리를 table에 적용
    - 이때, 거쳐가는 정점이 있다면 해당 정점까지의 비용을 더한 값을 비교
    - 이때, 거쳐가는 정점의 비용을 포함해 비용이 가장 적은 정점을 구할때
    연결된 모든 간선들을 확인한다면 O(VE)가 될것
    - 새 정점을 추가할때마다 미리 테이블에 거리를 계산해두고, 거기서 최솟값을 찾는 방식이라면
    O(V^2 + E)에 수행 가능
    - 일반적으로 E가 V보다 크니, 더 효율적

    4. 모든 정점을 확정할때까지 3번을 반복

    #거리가 가장 작은 정점을 찾는 방식
    
    1. 정점을 확정한 후, 테이블에 연결된 정점들의 거리를 미리 기입해 놓음
    - 확정은 아님

    2. 그 후, 최단거리 정점을 탐색할때 table을 순회하여 O(V)에 최단거리 정점을 확인 가능

    3. 새로운 최단거리 정점을 확정 한 후, 기존 테이블에 기입된 미확정 거리들과 새로 추가된 정점에
    연결된 간선들의 비용을 비교하여 최소값을 테이블에 기입
    예) 1번으로 확정되어있던 상황에서, 새로 3번이 확정된 상황
    d[1] 보다 d[3] + [3->4의 비용] 이 더 적다면, 테이블을 갱신

    4. 모든 정점을 확정할때까지 3번을 반복

    #O(V^2+ E)가 빠른걸까?
    - 현재는 O(V^2 + E)보다 개선된 방식이 나옴
    - 다익스트라 문제중에 정점을 2만개를 둬서 O(V^2 + E)로는 통과할 수 없는 문제들도 존재
    - 위의 구현은 동작 원리를 이해하는 차원에서의 학습
    - 실제 사용은 개선된 구현 방식을 사용

    #개선된 구현 방식
    - 우선순위큐를 사용
    - 간선(정점, 비용)을 우선순위 큐에 넣음
    1. queue에 Edge(시작점, 0)을 추가

    2. queue에서 거리가 가장 작은 원소를 선택
    해당 거리가 최단거리 테이블에 있는 값과 다를경우 3번을 수행하지 않음

    3. Edge가 가리키는 정점을 V라고 할때
    V와 이웃한 정점들에 대해 최단거리 테이블 값보다 V를 거쳐가는 것이 더 작은 값을 가질 경우
    최단거리 테이블의 값을 갱신하고 Queue에 Edge를 추가
    - poll한 Edge의 간선과 board의 간선을 합쳐서 값을 산출
    
    4. queue가 빌 때 까지 2, 3번을 반복

    - 우선순위 큐에 간선 1개당 최대 1개의 원소가 추가될 수 있음
    - 시간복잡도는 O(E log E)
    - O(E log V)라는 정보도 많을텐데, 둘은 Big-O 관점에서 동일
    - E가 V^2에 가깝다면 우선순위 큐를 사용하지 않는 구현이 더 낫지만
    보통 V를 많이 크게 두고, E는 V^2까진 안가게 하는 문제가 많음


    #경로 복원
    - 플로이드 알고리즘과 크게 다르지 않음
    - 최단거리 테이블이 갱신될 때, 어디를 거쳐가면 되는지 path table을 함꼐 작성
    - 시작점에서 나에게로 올 때, 직전에 어디를 방문했는지 기록하는 table


    #연습문제
    1. 백준 1753(solve)
    2. 백준 11779(solve)

    #네이버 카페 문제 50선 중 다익스트라 유형 문제
    1. 백준 1753(solve)
    - 바킹독님 연습문제랑 겹침
    2. 백준 1504(solve)
    3. 백준 16118(fail)
    - 풀이는 맞은 듯 하나, 언어별 추가시간이 없어서 시간초과가 해결이 안됨..
    - Kotlin으로 맞춘 사람이 없는 상태..


*/