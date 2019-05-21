#include <iostream>
#include <vector>
#include <set>
#include <cstdint>
#include <stack>
#include <queue>
#include <algorithm>
#include <set>
#include <string>

struct Edge
{
    int dest;
    int weight;
};

typedef std::vector<std::vector<Edge>> graph_t;
const int infinity = INT32_MAX;

struct EdgeQueue
{
    Edge e;
    int prev;
};

struct Supermarket
{
    int city;
    int t;
};

void dijkstra(const int source, const graph_t &graph, const int n, std::vector<int> &prev, std::vector<int> &dist)
{
    std::vector<bool> checked(n+1);

    for (int i=1; i<=n; ++i)
    {
        checked[i] = false;
        dist[i] = infinity;
    }

    checked[source] = true;
    dist[source] = 0;
    prev[source] = 0;

    auto cmp = [](EdgeQueue left, EdgeQueue right) {
      return (left.e.weight > right.e.weight);
    };

    std::priority_queue<EdgeQueue, std::vector<EdgeQueue>, decltype(cmp)> pq(cmp);

    for (const auto &edge : graph[source])
    {
        pq.push({edge, source});
    };

    while (!pq.empty())
    {
        EdgeQueue x = pq.top();
        pq.pop();

        if (!checked[x.e.dest])
        {
            checked[x.e.dest] = true;
            dist[x.e.dest] = x.e.weight;
            prev[x.e.dest] = x.prev;
            for (const auto &edge : graph[x.e.dest])
                pq.push({{edge.dest, dist[x.e.dest] + edge.weight}, x.e.dest});

        }
    }
}

void tToString(int mins)
{
  int minutes = mins / 60;
  int seconds = mins % 60;
  std::cout << minutes;
  std::cout << ":";
  if (seconds < 10)
      std::cout << "0";
  std::cout << seconds;
  std::cout << "\n";
}

int main()
{
    std::ios_base::sync_with_stdio(false);

    int t;
    std::cin >> t;

    for (int testCase=1; testCase <= t; ++testCase)
    {
        std::cout << "Case #" << testCase << ": ";

        int numOfCities, numOfPaths, numOfSupermarkets, posOfLea, posOfPeter;

        std::cin >> numOfCities;
        std::cin >> numOfPaths;
        std::cin >> numOfSupermarkets;
        std::cin >> posOfLea;
        std::cin >> posOfPeter;

        graph_t graph (numOfCities+1);

        for (int i=1; i<=numOfPaths; ++i)
        {
            int x_i, y_i, z_i;
            std::cin >> x_i;
            std::cin >> y_i;
            std::cin >> z_i;
            graph[x_i].push_back({ y_i, z_i });
            graph[y_i].push_back({ x_i, z_i });
        }

        std::vector<Supermarket> supermarkets;
        for (int i=1; i<=numOfSupermarkets; ++i)
        {
            int c_j, w_j;
            std::cin >> c_j;
            std::cin >> w_j;
            supermarkets.push_back({ c_j, w_j });
        }

        if (numOfSupermarkets == 0)
        {
            std::cout << "impossible\n";
            continue;
        }

        std::vector<int> predLea (numOfCities+1);
        std::vector<int> disLea (numOfCities+1);
        std::vector<int> predPeter (numOfCities+1);
        std::vector<int> disPeter (numOfCities+1);

        dijkstra(posOfLea, graph, numOfCities, predLea, disLea);
        dijkstra(posOfPeter, graph, numOfCities, predPeter, disPeter);

        int min = infinity;
        int res = infinity;

        for (const auto &supermarket : supermarkets)
        {
            int t1 = disLea[supermarket.city];
            if (t1 == infinity)
            {
                continue;
            }

            int currentdist = 0;
            int currentNode = supermarket.city;

            while (predPeter[currentNode] != 0)
            {
                currentdist += std::abs(disPeter[currentNode] - disPeter[predPeter[currentNode]]);
                currentNode = predPeter[currentNode];
            }

            if (currentNode != posOfPeter){
                continue;
              }

            int t2 = currentdist;

            res = t1 + t2 + supermarket.t;
            if (res < min)
            {
                min = res;
            }

        }

        if (min == infinity)
        {
            std::cout << "impossible\n";
        } else {
            tToString(min);
        }

    }

    return 0;
}
